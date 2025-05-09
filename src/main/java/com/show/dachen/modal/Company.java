package com.show.dachen.modal;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "company")
public class Company {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "company_id")
	private Integer companyId;

	@Column(name = "company_name")
	private String companyName;

	@Column(name = "contact_person")
	private String contactPerson;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "is_active")
	private Boolean isActive;

	@JsonIgnore
	@OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
	private List<OrderHeader> orderHeader = new LinkedList<OrderHeader>();

}
