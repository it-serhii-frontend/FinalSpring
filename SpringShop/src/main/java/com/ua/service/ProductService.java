package com.ua.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.ua.model.Product;

public interface ProductService {

	public void addProduct(Product product);

	public List<Product> listProduct();

	public Optional<Product> getProductById(long productId);

	public void deleteProduct(long productId);

	public void deleteProduct(Product product);

	public Page<Product> listProduct(Pageable pageable);

	Page<Product> getProductSortNameDescList(Pageable pageable);
	Page<Product> getProductSortNameAscList(Pageable pageable);
	Page<Product> getProductSortPriceDescList(Pageable pageable);
	Page<Product> getProductSortPriceAscList(Pageable pageable);
	Page<Product> getProductSortNovAscList(Pageable pageable);
	Page<Product> getProductSortNovDescList(Pageable pageable);

	public Page<Product> listGlasses(Pageable pageable);
	public Page<Product> listKettles(Pageable pageable);
	
	public Page<Product> list2020(Pageable pageable);
	public Page<Product> list2021(Pageable pageable);


}
