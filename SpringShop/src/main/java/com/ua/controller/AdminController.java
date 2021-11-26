package com.ua.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ua.model.Product;
import com.ua.model.User;
import com.ua.service.FileUploadService;
import com.ua.service.ProductService;
import com.ua.service.UserService;

@Controller
@RequestMapping("admin")
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;

	@Autowired
	private FileUploadService fileUploadService;

	@GetMapping("main")
	public String index() {
		return "admin/main";
	}

	@GetMapping("allProducts")
	public ModelAndView listProduct() {
		ModelAndView mv = new ModelAndView("admin/allProducts");
		mv.addObject("productList", productService.listProduct());
		return mv;
	}
	
	@GetMapping("addProduct")
	public String addProduct() {
		return "admin/addProduct";
	}
	
	@PostMapping("addProduct")
	public ModelAndView addProduct(Product product, @RequestParam("file") MultipartFile file) {
		ModelAndView mv = new ModelAndView("admin/addProduct");
		System.out.println("file: " + file.getOriginalFilename());
		String filePath = fileUploadService.upload(file);
		product.setImage(filePath);

		System.out.println(product.getImage());

		productService.addProduct(product);
		mv.addObject("productList", productService.listProduct());
		return mv;
	}

	@GetMapping("deleteProduct/{productId}")
	public ModelAndView deleteProduct(@PathVariable("productId") String productId) {
		ModelAndView mv = new ModelAndView("admin/allProducts");
		productService.deleteProduct(Long.parseLong(productId));
		mv.addObject("productList", productService.listProduct());
		return mv;
	}

	@GetMapping("editProduct/{productId}")
	public ModelAndView updateProduct(@PathVariable("productId") String productId) {
		ModelAndView mv = new ModelAndView("admin/editProduct");
		mv.addObject("Product", productService.getProductById(Long.parseLong(productId)).get());
		return mv;
	}


	@GetMapping("allUsers")
	public String userList(Model model) {
		model.addAttribute("userList", userService.findAllUser());
		return "admin/allUsers";
	}

	@GetMapping("deleteUser/{userId}")
	public ModelAndView deleteUser(@PathVariable("userId") String userId) {
		ModelAndView mv = new ModelAndView("admin/allUsers");
		userService.deleteUser(Long.parseLong(userId));
		mv.addObject("userList", userService.findAllUser());
		return mv;
	}
	

}
