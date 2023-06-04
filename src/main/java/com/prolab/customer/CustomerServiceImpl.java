package com.prolab.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Override
	public ResponseEntity<Customer> createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		try {
			customerRepo.save(customer);
		} catch (Exception e) {
			// TODO: handle exception
			ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(customer);
	}

	@Override
	public ResponseEntity<Customer> fetchCustomerByNameORNumber(String customerName) {
		// TODO Auto-generated method stub
		try {
			String customerDetails = customerName + "%";
			Customer fectchedCustomerDetails = customerRepo.searchCustomerByContactNoORCustomerName(customerDetails);
			if (fectchedCustomerDetails != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(fectchedCustomerDetails);
			}
		} catch (Exception e) {
			
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Customer());
	}


}
