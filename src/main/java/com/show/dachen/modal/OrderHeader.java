package com.show.dachen.modal;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "order_header")
public class OrderHeader {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Integer orderId;

	@Column(name = "order_date")
	private Date orderDate;

	@Column(name = "total_price")
	private Integer totalPrice;

	@Column(name = "note")
	private String note;

	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company company;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderDetail> orderDetail = new LinkedList<OrderDetail>();

}
