package com.show.dachen.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.show.dachen.modal.Company;
import com.show.dachen.modal.OrderHeader;

public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Integer> {

	List<OrderHeader> findByCompanyAndOrderDate(Company company, Date orderDate);

	List<OrderHeader> findByCompany(Company company);

	List<OrderHeader> findByOrderDateBetween(Date orderDateStart, Date orderDateEnd);

	List<OrderHeader> findByOrderDateBetweenAndCompany(Date orderDateStart, Date orderDateEnd, Company company);

}
