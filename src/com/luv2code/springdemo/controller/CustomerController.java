package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;


/**
 * @author bhaskar
 *
 * 
 */

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// need to inject customer service
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model theModel)
	{
		// get customers from service
		List<Customer> theCustomers=customerService.getCustomers();
		
		// add customers model
		
		theModel.addAttribute("customers",theCustomers);
		
		return "list-customers";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel)
	{
		Customer theCustomer=new Customer();
		theModel.addAttribute("customer",theCustomer);
		return "customer-form";
	}
	
	
	//post
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer)
	{
		customerService.saveCustomer(theCustomer);
		return "redirect:/customer/list";
}
	
	
	//update
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int theId, Model theModel)
	{
		// get customers from service
		Customer theCustomer=customerService.getCustomer(theId);
		
		//set customer
		theModel.addAttribute("customer", theCustomer);
		
		//send over to our form
			return "customer-form";
	
}
	
	//delete
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId)
	{
		// delete customers from service
		
		customerService.deleteCustomer(theId);
		
			return "redirect:/customer/list";
	
}
}