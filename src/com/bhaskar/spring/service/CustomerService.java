package com.bhaskar.spring.service;

import java.util.List;

import com.bhaskar.spring.entity.Customer;


/**
 * @author bhaskar
 *
 * 
 */
public interface CustomerService {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);
}
