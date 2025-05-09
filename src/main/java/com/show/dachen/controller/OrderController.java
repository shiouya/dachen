package com.show.dachen.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.show.dachen.dao.OrderCreate;
import com.show.dachen.dao.OrderDetailDAO;
import com.show.dachen.modal.Company;
import com.show.dachen.modal.OrderDetail;
import com.show.dachen.modal.OrderHeader;
import com.show.dachen.modal.Product;
import com.show.dachen.repository.CompanyRepository;
import com.show.dachen.repository.OrderDetailRepository;
import com.show.dachen.repository.OrderHeaderRepository;
import com.show.dachen.repository.ProductRepository;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderDetailRepository orderDetailRep;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private OrderHeaderRepository orderHeaderRep;

	@Autowired
	private ProductRepository productRep;

	@GetMapping
	public ResponseEntity<List<OrderHeader>> getOrders() {
		List<OrderHeader> all = orderHeaderRep.findAll();

		if (all.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(all);
		}
	}

	@GetMapping("/company/{comid}/{start}/{end}")
	public ResponseEntity<List<OrderHeader>> getOrdersByCompany(@PathVariable Integer comid, @PathVariable String start,
			@PathVariable String end) {
		Optional<Company> com = companyRepository.findById(comid);
		Company company = com.get();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startd = null;
		Date endd = null;
		try {
			startd = sdf.parse(start);
			endd = sdf.parse(end);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<OrderHeader> all = orderHeaderRep.findByOrderDateBetweenAndCompany(startd, endd, company);

		if (all.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(all);
		}
	}

	@GetMapping("/date/{start}/{end}")
	public ResponseEntity<List<OrderHeader>> getOrdersByDate(@PathVariable String start, @PathVariable String end) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startd = null;
		Date endd = null;
		try {
			startd = sdf.parse(start);
			endd = sdf.parse(end);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<OrderHeader> all = orderHeaderRep.findByOrderDateBetween(startd, endd);

		if (all.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(all);
		}
	}

	@PostMapping("/create")
	public ResponseEntity<?> createOrders(@RequestBody OrderCreate entity) {
		List<Company> byName = companyRepository.findByName(entity.getCompanyName());
		Company company = byName.get(0);

		OrderHeader orderHeader = new OrderHeader();
		orderHeader.setCompany(company);
		orderHeader.setNote(entity.getNote());
		orderHeader.setOrderDate(entity.getOrderDate());
		orderHeader.setTotalPrice(entity.getTotalPrice());

		OrderHeader order = orderHeaderRep.save(orderHeader);

		for (OrderDetailDAO detail : entity.getOrderDetail()) {
			Optional<Product> pro = productRep.findById(detail.getProductId());
			Product product = pro.get();

			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setOrder(order);
			orderDetail.setQuantity(detail.getQuantity());
			orderDetail.setTotalPrice(detail.getTotalPrice());
			orderDetail.setProduct(product);
			orderDetailRep.save(orderDetail);
		}

		return ResponseEntity.ok(true);
	}

}
