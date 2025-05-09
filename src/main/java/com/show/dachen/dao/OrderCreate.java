package com.show.dachen.dao;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class OrderCreate {
	
	private Date orderDate;

	private Integer totalPrice;

	private String note;

	private String companyName;

	private List<OrderDetailDAO> orderDetail;

}
