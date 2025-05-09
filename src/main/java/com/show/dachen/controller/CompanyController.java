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

import com.show.dachen.modal.Company;
import com.show.dachen.repository.CompanyRepository;



@RestController
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyRepository companyRep;

	@GetMapping
	public ResponseEntity<List<Company>> getCompanys() {
		List<Company> all = companyRep.findAll();

		if (all.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(all);
		}
	}

	@GetMapping("Active/{Active}")
	public ResponseEntity<List<Company>> getCompanysActive(@PathVariable Boolean Active) {
		List<Company> all = companyRep.findByIsActive(Active);

		if (all.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(all);
		}
	}

	@GetMapping("{id}")
	public ResponseEntity<Company> getCompany(@PathVariable Integer id) {
		Optional<Company> com = companyRep.findById(id);
		Company company = com.get();

		return ResponseEntity.ok(company);
	}

	@PutMapping("update")
	public ResponseEntity<Company> updateCompany(@RequestBody Company entity) {
		Optional<Company> com = companyRep.findById(entity.getCompanyId());
		Company company = com.get();
		company.setCompanyName(entity.getCompanyName());
		company.setContactPerson(entity.getContactPerson());
		company.setIsActive(entity.getIsActive());
		company.setPhoneNumber(entity.getPhoneNumber());
		companyRep.save(company);

		return ResponseEntity.ok(company);
	}

	@PostMapping("create")
	public ResponseEntity<Company> createCompany(@RequestBody Company entity) {
		Company company = new Company();

		company.setCompanyName(entity.getCompanyName());
		company.setContactPerson(entity.getContactPerson());
		company.setIsActive(entity.getIsActive());
		company.setPhoneNumber(entity.getPhoneNumber());
		companyRep.save(company);

		return ResponseEntity.ok(company);
	}

	@GetMapping("/name/{name}")
	public ResponseEntity<List<Company>> getCompanysByName(@PathVariable String name) {
		List<Company> companys = companyRep.findByName(name);

		if (companys.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(companys);
		}
	}

}
