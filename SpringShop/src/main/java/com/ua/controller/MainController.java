package com.ua.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ua.additional.UserHelp;
import com.ua.mail.Mail;
import com.ua.model.Product;
import com.ua.model.User;
import com.ua.service.ProductService;
import com.ua.service.UserService;

@Controller
@RequestMapping("/")
public class MainController {
	private String code = null;

	@Autowired
	private UserService userService;

	@Autowired
	private ProductService productService;

	@GetMapping({ "main", "/" })
	public String index(Model model) {
		model.addAttribute("productList", productService.listProduct());
		return "main";
	}

	@GetMapping("allDish/desc-n")
	public String listDescNameProducts(Model model, @RequestParam(defaultValue = "0") int page) {
		PageRequest pageable = PageRequest.of(page, 4);
		Page<Product> products = productService.getProductSortNameDescList(pageable);
		model.addAttribute("productList", products);
		model.addAttribute("currentPage", page);
		return "all-products";
	}

	@GetMapping("allDish/asc-n")
	public String listAscNameProducts(Model model, @RequestParam(defaultValue = "0") int page) {
		PageRequest pageable = PageRequest.of(page, 4);
		Page<Product> products = productService.getProductSortNameAscList(pageable);
		model.addAttribute("productList", products);
		model.addAttribute("currentPage", page);
		return "all-products";
	}

	@GetMapping("allDish/desc-p")
	public String listDescPriceProducts(Model model, @RequestParam(defaultValue = "0") int page) {
		PageRequest pageable = PageRequest.of(page, 4);
		Page<Product> products = productService.getProductSortNameDescList(pageable);
		model.addAttribute("productList", products);
		model.addAttribute("currentPage", page);
		return "all-products";
	}

	@GetMapping("allDish/asc-p")
	public String listAscPriceProducts(Model model, @RequestParam(defaultValue = "0") int page) {
		PageRequest pageable = PageRequest.of(page, 4);
		Page<Product> products = productService.getProductSortNameAscList(pageable);
		model.addAttribute("productList", products);
		model.addAttribute("currentPage", page);
		return "all-products";
	}

	@GetMapping("allDish/asc-nov")
	public String listAscNovalityProducts(Model model, @RequestParam(defaultValue = "0") int page) {
		PageRequest pageable = PageRequest.of(page, 4);
		Page<Product> products = productService.getProductSortNovAscList(pageable);
		model.addAttribute("productList", products);
		model.addAttribute("currentPage", page);
		return "all-products";
	}

	@GetMapping("allDish/desc-nov")
	public String listDescNovalityProducts(Model model, @RequestParam(defaultValue = "0") int page) {
		PageRequest pageable = PageRequest.of(page, 4);
		Page<Product> products = productService.getProductSortNovDescList(pageable);
		model.addAttribute("productList", products);
		model.addAttribute("currentPage", page);
		return "all-products";
	}

	@GetMapping("allDish")
	public String listProducts(Model model, @RequestParam(defaultValue = "0") int page) {
		PageRequest pageable = PageRequest.of(page, 4);
		Page<Product> products = productService.listProduct(pageable);
		model.addAttribute("productList", products);
		model.addAttribute("currentPage", page);
		return "all-products";
	}

	@GetMapping("allDish/glasses")
	public String listGlassess(Model model, @RequestParam(defaultValue = "0") int page) {
		PageRequest pageable = PageRequest.of(page, 4);
		Page<Product> products = productService.listGlasses(pageable);
		model.addAttribute("productList", products);
		model.addAttribute("currentPage", page);
		return "all-products";
	}
	
	@GetMapping("allDish/kettles")
	public String listKettles(Model model, @RequestParam(defaultValue = "0") int page) {
		PageRequest pageable = PageRequest.of(page, 4);
		Page<Product> products = productService.listKettles(pageable);
		model.addAttribute("productList", products);
		model.addAttribute("currentPage", page);
		return "all-products";
	}

	@GetMapping("allDish/2020")
	public String list2020(Model model, @RequestParam(defaultValue = "0") int page) {
		PageRequest pageable = PageRequest.of(page, 4);
		Page<Product> products = productService.list2020(pageable);
		model.addAttribute("productList", products);
		model.addAttribute("currentPage", page);
		return "all-products";
	}
	@GetMapping("allDish/2021")
	public String list2021(Model model, @RequestParam(defaultValue = "0") int page) {
		PageRequest pageable = PageRequest.of(page, 4);
		Page<Product> products = productService.list2021(pageable);
		model.addAttribute("productList", products);
		model.addAttribute("currentPage", page);
		return "all-products";
	}
	
	@GetMapping("login")
	public String login() {
		return "login";
	}

	@GetMapping("cart")
	public String cart() {
		return "cart";
	}

	@PostMapping("cart")
	public ModelAndView cart(User user) {
		ModelAndView mv = new ModelAndView("/cart");
		userService.save(user);
		mv.addObject("prodlist", productService.listProduct());
		return mv;
	}

	@GetMapping("cart/{productId}")
	public ModelAndView addToCart(@PathVariable("productId") String productId, Principal principal) {
		ModelAndView mv = new ModelAndView("cart.html");
		long productLongId = Long.parseLong(productId);
		Product product = productService.getProductById(productLongId).get();
		List<Product> prodList = new ArrayList<>();
		prodList.add(product);
		double total = Double.parseDouble(product.getProductPrice());
		mv.addObject("prodList", prodList);
		mv.addObject("total", total);

		return mv;

	}

	@GetMapping(value = "/controlCode")
	public String showAllData(String email, ModelMap model) {
		UserHelp uh = new UserHelp();
		if (uh.checkEmailValid(email)) {

			Mail mail = new Mail();
			String[] arr = { email };
			try {
				mail.sendMail(arr);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			code = mail.getCode();
		}
		return "signup";

	}

	@GetMapping("signup")
	public String signup(@ModelAttribute("user") User user) {
		return "signup";
	}
	
	@GetMapping("aboutUs")
	public String aboutUs() {
		return "aboutUs";
	}

	@PostMapping("signup")
	public String addUser(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "signup";
		}
		User emailChcek = userService.findByEmail(user.getEmail());
		if (emailChcek != null) {
			return "signupEmailEx";
		}
		ModelAndView mv = new ModelAndView("/main");
		userService.save(user);
		mv.addObject("productList", productService.listProduct());
		return "main";
	}

	@GetMapping("error")
	public String error() {
		return "error";
	}

}
