package com.show.dachen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.show.dachen.modal.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

	@Query("SELECT w FROM Company w WHERE w.companyName LIKE %:name%")
	List<Company> findByName(String name);

	List<Company> findByIsActive(Boolean isActive);

}
