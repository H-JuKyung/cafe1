package com.shop.cafe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.shop.cafe.dao.ProductDao;
import com.shop.cafe.dto.Product;

@Service // 서비스 컴포넌트들은 Component 대신 Service로 사용 가능(기호만 보고도 무슨 역할인지 알 수 있음)
public class ProductService {
	
	@Autowired // new 없이 자동화
	ProductDao productDao; // 항상 Dao 와 함께 일함
	
	public List<Product> getAllProducts() throws Exception {
		return productDao.getAllProducts();
	}
}
