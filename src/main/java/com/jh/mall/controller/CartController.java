package com.jh.mall.controller;

import com.jh.mall.consts.MallConsts;
import com.jh.mall.dto.CartAddDto;
import com.jh.mall.dto.CartUpdateDto;
import com.jh.mall.pojo.User;
import com.jh.mall.service.CartService;
import com.jh.mall.vo.CartVo;
import com.jh.mall.vo.ResponseVo;
import com.jh.mall.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * @Author JH
 * @Date 21/1/14 21:56
 * @Description TODO
 */
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping("")
    public ResponseVo<CartVo> list(HttpSession session) {
        //从session从获取uid
        UserVo user = (UserVo) session.getAttribute(MallConsts.CURRENT_USER);
        return cartService.list(user.getId());
    }

    @PostMapping("")
    public ResponseVo<CartVo> add(@Valid @RequestBody CartAddDto cartAddForm,
                                  HttpSession session) {
        UserVo user = (UserVo) session.getAttribute(MallConsts.CURRENT_USER);
        return cartService.addProductById(user.getId(), cartAddForm);
    }

    @PutMapping("/{productId}")
    public ResponseVo<CartVo> update(@PathVariable Integer productId,
                                     @Valid @RequestBody CartUpdateDto form,
                                     HttpSession session) {
        UserVo user = (UserVo) session.getAttribute(MallConsts.CURRENT_USER);
        return cartService.update(user.getId(), productId, form);
    }

    @DeleteMapping("/{productId}")
    public ResponseVo<CartVo> delete(@PathVariable Integer productId,
                                     HttpSession session) {
        UserVo user = (UserVo) session.getAttribute(MallConsts.CURRENT_USER);
        return cartService.delete(user.getId(), productId);
    }

    @PutMapping("/selectAll")
    public ResponseVo<CartVo> selectAll(HttpSession session) {
        UserVo user = (UserVo) session.getAttribute(MallConsts.CURRENT_USER);
        return cartService.selectAll(user.getId());
    }

    @PutMapping("/unSelectAll")
    public ResponseVo<CartVo> unSelectAll(HttpSession session) {
        UserVo user = (UserVo) session.getAttribute(MallConsts.CURRENT_USER);
        return cartService.unSelectAll(user.getId());
    }

    @GetMapping("/products/sum")
    public ResponseVo<Integer> sum(HttpSession session) {
        UserVo user = (UserVo) session.getAttribute(MallConsts.CURRENT_USER);
        return cartService.sum(user.getId());
    }

}