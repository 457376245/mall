package com.jh.mall.controller;

import com.jh.mall.consts.MallConsts;
import com.jh.mall.dto.ShippingDto;
import com.jh.mall.pojo.User;
import com.jh.mall.service.ShipService;
import com.jh.mall.vo.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * Created by 廖师兄
 */
@RestController
@RequestMapping("/shippings")
public class ShippingController {

	@Autowired
	private ShipService shippingService;

	@PostMapping("")
	public ResponseVo add(@Valid @RequestBody ShippingDto form,
						  HttpSession session) {
		User user =(User) session.getAttribute(MallConsts.CURRENT_USER);
		return shippingService.add(user.getId(), form);
	}

	@DeleteMapping("/{shippingId}")
	public ResponseVo delete(@PathVariable Integer shippingId,
							 HttpSession session) {
		User user = (User) session.getAttribute(MallConsts.CURRENT_USER);
		return shippingService.delete(user.getId(), shippingId);
	}

	@PutMapping("/{shippingId}")
	public ResponseVo update(@PathVariable Integer shippingId,
							 @Valid @RequestBody ShippingDto form,
							 HttpSession session) {
		User user = (User) session.getAttribute(MallConsts.CURRENT_USER);
		return shippingService.update(user.getId(), shippingId, form);
	}

	@GetMapping("")
	public ResponseVo list(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
						   @RequestParam(required = false, defaultValue = "10") Integer pageSize,
						   HttpSession session) {
		User user = (User) session.getAttribute(MallConsts.CURRENT_USER);
		return shippingService.get(user.getId(), pageNum, pageSize);
	}
}
