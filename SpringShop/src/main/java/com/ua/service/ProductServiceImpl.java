package com.ua.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ua.dao.ProductRepository;
import com.ua.model.Product;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public void addProduct(Product product) {
		productRepository.save(product);
	}

	@Override
	public List<Product> listProduct() {
		return productRepository.findAll();
	}

	@Override
	public Optional<Product> getProductById(long productId) {
		return productRepository.findById(productId);
	}

	@Override
	public void deleteProduct(long productId) {
		productRepository.deleteById(productId);
	}

	@Override
	public void deleteProduct(Product product) {
		productRepository.delete(product);
	}

	@Override
	public Page<Product> listProduct(Pageable pageable) {
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Product> list;
		List<Product> products = productRepository.findAll();

		if (products.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, products.size());
			list = products.subList(startItem, toIndex);
		}

		Page<Product> productList = new PageImpl<Product>(list, PageRequest.of(currentPage, pageSize), products.size());

		return productList;
	}

	@Override
	public Page<Product> getProductSortNameDescList(Pageable pageable) {
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Product> list;
		List<Product> products = productRepository.findAll();
		Sort val = Sort.by("productName").descending();

		if (products.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, products.size());
			list = products.subList(startItem, toIndex);
		}
		Pageable paging = PageRequest.of(currentPage, pageSize, val);
		Page<Product> productList = productRepository.findAll(paging);
		return productList;
	}

	@Override
	public Page<Product> getProductSortNameAscList(Pageable pageable) {
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Product> list;
		List<Product> products = productRepository.findAll();
		Sort val = Sort.by("productName").ascending();

		if (products.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, products.size());
			list = products.subList(startItem, toIndex);
		}
		Pageable paging = PageRequest.of(currentPage, pageSize, val);
		Page<Product> productList = productRepository.findAll(paging);
		return productList;

	}

	@Override
	public Page<Product> getProductSortPriceDescList(Pageable pageable) {
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Product> list;
		List<Product> products = productRepository.findAll();
		Sort val = Sort.by("productPrice").descending();

		if (products.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, products.size());
			list = products.subList(startItem, toIndex);
		}
		Pageable paging = PageRequest.of(currentPage, pageSize, val);
		Page<Product> productList = productRepository.findAll(paging);
		return productList;

	}

	@Override
	public Page<Product> getProductSortPriceAscList(Pageable pageable) {
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Product> list;
		List<Product> products = productRepository.findAll();
		Sort val = Sort.by("productPrice").ascending();

		if (products.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, products.size());
			list = products.subList(startItem, toIndex);
		}
		Pageable paging = PageRequest.of(currentPage, pageSize, val);
		Page<Product> productList = productRepository.findAll(paging);
		return productList;
	}

	@Override
	public Page<Product> getProductSortNovAscList(Pageable pageable) {
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Product> list;
		List<Product> products = productRepository.findAll();
		Sort val = Sort.by("created").ascending();

		if (products.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, products.size());
			list = products.subList(startItem, toIndex);
		}
		Pageable paging = PageRequest.of(currentPage, pageSize, val);
		Page<Product> productList = productRepository.findAll(paging);
		return productList;
	}

	@Override
	public Page<Product> getProductSortNovDescList(Pageable pageable) {
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Product> list;
		List<Product> products = productRepository.findAll();
		Sort val = Sort.by("created").descending();

		if (products.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, products.size());
			list = products.subList(startItem, toIndex);
		}
		Pageable paging = PageRequest.of(currentPage, pageSize, val);
		Page<Product> productList = productRepository.findAll(paging);
		return productList;
	}

	@Override
	public Page<Product> listGlasses(Pageable pageable) {
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Product> list;
		List<Product> products = productRepository.findAll();
		List<Product> glasses = new ArrayList<>();

		for (Product product : products) {
			if (product.getProductCategory().equals("чашка")) {
				glasses.add(product);
			}
		}
		if (glasses.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, glasses.size());
			list = glasses.subList(startItem, toIndex);
		}
		Page<Product> productList = new PageImpl<Product>(list, PageRequest.of(currentPage, pageSize), glasses.size());
		return productList;
	}

	@Override
	public Page<Product> listKettles(Pageable pageable) {
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Product> list;
		List<Product> products = productRepository.findAll();
		List<Product> kettles = new ArrayList<>();

		for (Product product : products) {
			if (product.getProductCategory().equals("чайник")) {
				kettles.add(product);
			}
		}
		if (kettles.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, kettles.size());
			list = kettles.subList(startItem, toIndex);
		}
		Page<Product> productList = new PageImpl<Product>(list, PageRequest.of(currentPage, pageSize), kettles.size());
		return productList;
	}



	@Override
	public Page<Product> list2020(Pageable pageable) {
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Product> list;
		List<Product> products = productRepository.findAll();
		List<Product> list2020 = new ArrayList<>();

		for (Product product : products) {
			if (product.getCreated().equals("2020")) {
				list2020.add(product);
			}
		}
		if (list2020.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, list2020.size());
			list = list2020.subList(startItem, toIndex);
		}
		Page<Product> productList = new PageImpl<Product>(list, PageRequest.of(currentPage, pageSize), list2020.size());
		return productList;
	}

	@Override
	public Page<Product> list2021(Pageable pageable) {
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Product> list;
		List<Product> products = productRepository.findAll();
		List<Product> list2021 = new ArrayList<>();

		for (Product product : products) {
			if (product.getCreated().equals("2021")) {
				list2021.add(product);
			}
		}
		if (list2021.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, list2021.size());
			list = list2021.subList(startItem, toIndex);
		}
		Page<Product> productList = new PageImpl<Product>(list, PageRequest.of(currentPage, pageSize), list2021.size());
		return productList;
	}

}
