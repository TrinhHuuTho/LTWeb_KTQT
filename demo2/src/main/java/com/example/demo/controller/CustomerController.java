package com.example.demo.controller;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.CustomerModel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Configuration
@RestController
@EnableMethodSecurity
public class CustomerController {
	final private List<CustomerModel> customers = List.of(CustomerModel.builder().id("22110238").name("Trịnh Hửu Thọ")
			.email("trinhuutho@gmail.com").phoneNumber("0123456789").build());

	@GetMapping("/hello")
	public ResponseEntity<String> hello() {
		return ResponseEntity.ok("Hello is Guest");
	}

	@GetMapping("/customer/all")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<List<CustomerModel>> getCustomerList() {
		List<CustomerModel> list = this.customers;
		return ResponseEntity.ok(list);
	}

	@GetMapping("/customer/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<CustomerModel> getCustomerList(@PathVariable("id") String id)
	{
		List<CustomerModel> customers = this.customers.stream().filter(customer -> customer.getId().equals(id)).toList();
		return ResponseEntity.ok(customers.get(0));
	}
	
	
}
