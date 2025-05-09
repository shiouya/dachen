package com.show.dachen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.show.dachen.modal.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("SELECT w FROM Product w WHERE w.productName LIKE %:name%")
	List<Product> findByName(String name);

	List<Product> findByIsActive(Boolean isActive);

}
