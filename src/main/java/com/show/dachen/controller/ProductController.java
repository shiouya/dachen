package com.show.dachen.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.show.dachen.modal.Product;
import com.show.dachen.repository.ProductRepository;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductRepository productRep;

	@GetMapping
	public ResponseEntity<List<Product>> getProducts() {
		List<Product> all = productRep.findAll();

		if (all.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(all);
		}
	}

	@GetMapping("Active/{Active}")
	public ResponseEntity<List<Product>> getProductsActive(@PathVariable Boolean Active) {
		List<Product> all = productRep.findByIsActive(Active);

		if (all.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(all);
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<Product> getProduct(@PathVariable Integer id) {
		Optional<Product> pro = productRep.findById(id);
		Product product = pro.get();

		return ResponseEntity.ok(product);
	}

	@PutMapping("update")
	public ResponseEntity<Product> updateProduct(@RequestBody Product entity) {
		Optional<Product> pro = productRep.findById(entity.getProductId());
		Product product = pro.get();
		product.setProductName(entity.getProductName());
		product.setPrice(entity.getPrice());
		product.setDescription(entity.getDescription());
		product.setIsActive(entity.getIsActive());
		productRep.save(product);

		return ResponseEntity.ok(product);
	}

	@PostMapping("create")
	public ResponseEntity<Product> createProduct(@RequestBody Product entity) {
		Product product = new Product();

		product.setProductName(entity.getProductName());
		product.setPrice(entity.getPrice());
		product.setDescription(entity.getDescription());
		product.setIsActive(entity.getIsActive());
		productRep.save(product);

		return ResponseEntity.ok(product);
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<List<Product>> getProductByName(@PathVariable String name) {
		List<Product> product = productRep.findByName(name);

		if (product.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(product);
		}
	}

}
