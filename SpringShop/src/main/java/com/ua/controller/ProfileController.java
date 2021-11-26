package com.ua.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ua.model.Product;
import com.ua.model.User;
import com.ua.service.ProductService;
import com.ua.service.UserService;

@Controller
@RequestMapping("user")
public class ProfileController {

	@Autowired
	private ProductService productService;

	@Autowired
	private UserService userService;

	private static User user;

	@GetMapping("cartUser")
	public ModelAndView cartProduct(Principal principal) {
		ModelAndView mv = new ModelAndView("user/cartUser");
		user = userService.findByEmail(principal.getName());
		mv.addObject("user", user);
		double total = findSum(user);
		mv.addObject("total", total);
		return mv;
	}

	private int findSum(User user) {
		List<Product> list = user.getProductList();
		int sum = 0;
		for (int i = 0; i < list.size(); i++) {
			Product p = list.get(i);
			sum += Double.valueOf(p.getProductPrice());
		}
		return sum;
	}

	@GetMapping("cartUser/{productId}")
	public ModelAndView addToCart(@PathVariable("productId") String productId, Principal principal) {

		ModelAndView mv = new ModelAndView("user/cartUser");
		user = userService.findByEmail(principal.getName());
		long productLongId = Long.parseLong(productId);
		Product product = productService.getProductById(productLongId).get();

		List<Product> productList = new ArrayList<>();
		productList.add(product);
		user.setProductList(productList);

		List<User> userList = new ArrayList<>();
		userList.add(user);
		product.setUserList(userList);

		userService.update(user);
		productService.addProduct(product);
		int total = findSum(user);
		mv.addObject("total", total);
		mv.addObject("user", user);
		return mv;
	}

	public int checkPr(Product product, List<Product> productList) {
		int i = 0;
		for (Product prod : productList) {
			if (prod.getProductId() == product.getProductId()) {
				return i;
			}
			i++;
		}
		return -1;
	}

	public int checkUs(User user, List<User> userList) {
		int i = 0;
		for (User us : userList) {
			if (us.getUserId() == user.getUserId()) {
				return i;
			}
			i++;
		}
		return -1;
	}
//	@GetMapping("check")
//	public ModelAndView orders(Principal principal) {
//		ModelAndView mv = new ModelAndView("user/check");
//		List<Product> us = user.getProductList1();
//		mv.addObject("user", us);
//		return mv;
//	}
//
//	@GetMapping("check/{productId}")
//	public ModelAndView buyProduct(@PathVariable("productId") String productId, Principal principal) {
//		ModelAndView mv = new ModelAndView("user/check");
//		user = userService.findByEmail(principal.getName());
//		long productLongId = Long.parseLong(productId);
//		Product product = productService.getProductById(productLongId).get();
//
//		List<Product> productList = new ArrayList<>();
//		productList.add(product);
////		user.setProductList1(productList);
//		user.setProductList2(productList, "registered");
//
//		List<User> userList = new ArrayList<>();
//		userList.add(user);
//		product.setUserList1(userList);
//
//		userService.update(user);
//		productService.addProduct(product);
//		mv.addObject("user", user);
//		return mv;
//	}

	@GetMapping("deleteProduct/{productId}")
	public ModelAndView deleteProduct(@PathVariable("productId") String productId, Principal principal) {
		ModelAndView mv = new ModelAndView("user/cartUser");
		user = userService.findByEmail(principal.getName());
		long productLongId = Long.parseLong(productId);
		Product product = productService.getProductById(productLongId).get();

		List<User> userList = product.getUserList();
		int ch1 = checkUs(user, userList);
		if (ch1 >= 0) {
			userList.remove(ch1);
		}
		product.setUserList(userList);

		List<Product> productList = user.getProductList();
		productList.forEach(System.out::println);
		int ch = checkPr(product, productList);
		if (ch >= 0) {
			productList.remove(ch);
		}
		user.setProductList(productList);
		userService.save(user);
		
		int total = findSum(user);
		mv.addObject("total", total);
		mv.addObject("user", user);
		return mv;
	}

}
