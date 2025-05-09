package com.show.dachen.dao;

import lombok.Data;

@Data
public class CompanyUpdate {

	private Integer companyId;

	private String companyName;

	private String contactPerson;

	private String phoneNumber;

	private Boolean isActive;

}
