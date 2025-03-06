package com.shop.cafe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.cafe.dto.Product;
import com.shop.cafe.service.ProductService;

@RestController
//@CrossOrigin("http://127.0.0.1:8080")
public class ProductController {
	
	@Autowired // DI
	ProductService productService;
	
	@GetMapping("getAllProducts")
	public List<Product> getAllProducts() {
		try { // Exception이 났을 때 return 값을 어떻게 전략을 짤지 하려고 try-catch 문 사용
			return productService.getAllProducts();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
