package com.prolab.customer;

import org.springframework.http.ResponseEntity;

public interface CustomerService {
	
	public ResponseEntity<Customer> createCustomer(Customer customer);
	
	public ResponseEntity<Customer> fetchCustomerByNameORNumber(String customerName);
}
