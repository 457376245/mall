package com.jh.mall.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 购物车
 * 包含商品集合，是否全选，总价，总数量
 */
@Data
public class CartVo {

	private List<CartProductVo> cartProductVoList;

	private Boolean selectedAll;

	private BigDecimal cartTotalPrice;

	private Integer cartTotalQuantity;
}
