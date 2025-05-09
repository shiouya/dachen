package com.show.dachen.modal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "order_detail")
public class OrderDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_detail_id")
	private Integer orderDetailId;

	@Column(name = "quantity")
	private Integer quantity;

	@Column(name = "total_price")
	private Integer totalPrice;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "order_id")
	private OrderHeader order;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

}
