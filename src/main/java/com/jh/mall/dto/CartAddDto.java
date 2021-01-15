package com.jh.mall.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 添加商品
 *
 */
@Data
public class CartAddDto {

	@NotNull
	private Integer productId;

	private Boolean selected = true;
}
