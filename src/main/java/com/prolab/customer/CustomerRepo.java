package com.prolab.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	
	@Query(value = "select * from customer_details cd where cd.customer_name  like :searchParam or cd.contact_no like :searchParam" , nativeQuery = true)
	Customer searchCustomerByContactNoORCustomerName(String searchParam);
}
