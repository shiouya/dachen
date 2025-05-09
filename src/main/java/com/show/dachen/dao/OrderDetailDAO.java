package com.show.dachen.dao;

import lombok.Data;

@Data
public class OrderDetailDAO {

	private Integer productId;

	private Integer quantity;

	private Integer totalPrice;

	private Integer money;
}
