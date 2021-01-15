package com.jh.mall.service.impl;

import com.google.gson.Gson;
import com.jh.mall.dao.ProductMapper;
import com.jh.mall.dto.CartAddDto;
import com.jh.mall.dto.CartUpdateDto;
import com.jh.mall.enums.ProductStatusEnum;
import com.jh.mall.enums.ResponseEnum;
import com.jh.mall.pojo.Cart;
import com.jh.mall.pojo.Product;
import com.jh.mall.service.CartService;
import com.jh.mall.vo.CartProductVo;
import com.jh.mall.vo.CartVo;
import com.jh.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author JH
 * @Date 21/1/13 16:02
 * @Description TODO
 */
@Service
public class CartServiceImpl implements CartService {
    private final static String CART_REDIS_KEY_TEMPLATE = "cart_%d";
    private Gson gson=new Gson();
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private StringRedisTemplate redisTemplate;


    @Override
    public ResponseVo<CartVo> addProductById(Integer uid, CartAddDto cartDto) {
        //产品为空return
        Product product = productMapper.selectByPrimaryKey(cartDto.getProductId());
        if (product==null) return ResponseVo.error(ResponseEnum.PRODUCT_NOT_EXIST);

        //非售卖状态return
        if (product.getStatus().equals(ProductStatusEnum.OFF_SALE)||product.getStatus().equals(ProductStatusEnum.DELETE))
            return ResponseVo.error(ResponseEnum.PRODUCT_ERROR);


        Cart cart = new Cart(cartDto.getProductId(),1,cartDto.getSelected());
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey  = String.format(CART_REDIS_KEY_TEMPLATE, uid);

        String cartString = opsForHash.get(redisKey, String.valueOf(cartDto.getProductId()));
        if (!StringUtils.isEmpty(cartString)){
            cart = gson.fromJson(cartString, cart.getClass());
            cart.setQuantity(cart.getQuantity()+1);
        }
        opsForHash.put(redisKey,cart.getProductId().toString(),gson.toJson(cart));

        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> list(Integer uid) {
        List<CartProductVo> cartProductVoList = new ArrayList<>();
        boolean selectAll = true;
        Integer cartTotalQuantity = 0;
        BigDecimal cartTotalPrice = BigDecimal.ZERO;
        CartVo cartVo = new CartVo();
        //获得所有product列表
        List<Product> allProduct = productMapper.getAllProduct();
        //转为map集合
        Map<Integer, Product> productMap = allProduct.stream().collect(Collectors.toMap(Product::getId, product -> product));
        //获取redis对象
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey  = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        //从redis取出uid购物车
        Map<String, String> entries = opsForHash.entries(redisKey);
        //遍历uid购物车每个产品
        for (Map.Entry<String,String> entry:entries.entrySet()){
            String value = entry.getValue();
            Cart cart = gson.fromJson(value, Cart.class);
            Integer productId = Integer.valueOf(entry.getKey());
            // 判断物品是否在map中并是否可用
            Product product = productMap.get(productId);
            if (productMap.containsKey(productId)&&product.getStatus().equals(1)){

                CartProductVo cartProductVo = new CartProductVo(productId,
                        //判断数量是否大于库存
                        cart.getQuantity()<product.getStock()?cart.getQuantity():product.getStock(),
                        product.getName(),
                        product.getSubtitle(),
                        product.getMainImage(),
                        product.getPrice(),
                        product.getStatus(),
                        product.getPrice().multiply(BigDecimal.valueOf(cart.getQuantity())),
                        product.getStock(),
                        cart.getProductSelected());
                cartProductVoList.add(cartProductVo);
                if (!cart.getProductSelected()) {
                    selectAll = false;
                }
                if (cart.getProductSelected()) {
                    cartTotalPrice = cartTotalPrice.add(cartProductVo.getProductTotalPrice());
                }
            }
            cartTotalQuantity += cart.getQuantity();
        }
        //有一个没有选中，就不叫全选
        cartVo.setSelectedAll(selectAll);
        cartVo.setCartTotalQuantity(cartTotalQuantity);
        cartVo.setCartTotalPrice(cartTotalPrice);
        cartVo.setCartProductVoList(cartProductVoList);
        return ResponseVo.success(cartVo);
    }

    @Override
    public ResponseVo<CartVo> update(Integer uid, Integer productId, CartUpdateDto updateDto) {
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        String value = opsForHash.get(redisKey, String.valueOf(productId));
        if (StringUtils.isEmpty(value)) return ResponseVo.error(ResponseEnum.CART_PRODUCT_NOT_EXIST);
        Cart cart = gson.fromJson(value, Cart.class);
        if (updateDto.getQuantity() != null
                && updateDto.getQuantity() >= 0) {
            cart.setQuantity(updateDto.getQuantity());
        }
        if (updateDto.getSelected() != null) {
            cart.setProductSelected(updateDto.getSelected());
        }
        opsForHash.put(redisKey, String.valueOf(productId), gson.toJson(cart));
        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> delete(Integer uid,Integer pid) {
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        String value = opsForHash.get(redisKey, String.valueOf(pid));
        if (StringUtils.isEmpty(value)) return ResponseVo.error(ResponseEnum.CART_PRODUCT_NOT_EXIST);
        opsForHash.delete(redisKey,String.valueOf(pid));
        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> selectAll(Integer uid) {
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        List<Cart> carts = listForCart(uid);
        for (Cart cart : carts) {
            cart.setProductSelected(true);
            opsForHash.put(redisKey,String.valueOf(cart.getProductId()),gson.toJson(cart));
        }
        return list(uid);
    }

    @Override
    public ResponseVo<CartVo> unSelectAll(Integer uid) {
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        List<Cart> carts = listForCart(uid);
        for (Cart cart : carts) {
            cart.setProductSelected(false);
            opsForHash.put(redisKey,String.valueOf(cart.getProductId()),gson.toJson(cart));
        }
        return list(uid);
    }

    @Override
    public ResponseVo<Integer> sum(Integer uid) {
        List<Cart> carts = listForCart(uid);
        Integer sum = carts.stream().map(Cart::getQuantity).reduce(0, Integer::sum);
        return ResponseVo.success(sum);
    }

    private List<Cart> listForCart(Integer uid){
        List<Cart> carts = new ArrayList<>();
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String redisKey = String.format(CART_REDIS_KEY_TEMPLATE, uid);
        Map<String, String> entries = opsForHash.entries(redisKey);
        for (Map.Entry<String, String>  entry:entries.entrySet()){
            carts.add(gson.fromJson(entry.getValue(),Cart.class));
        }
        return carts;
    }
}