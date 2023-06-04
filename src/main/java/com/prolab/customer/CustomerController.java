package com.prolab.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/create")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		return customerService.createCustomer(customer);
	}
	
	@GetMapping("/fetch-customer/{searchParam}")
	public ResponseEntity<Customer> fetchCustomerByNameORNumber(@PathVariable String searchParam) {
		return customerService.fetchCustomerByNameORNumber(searchParam);
	}
}
