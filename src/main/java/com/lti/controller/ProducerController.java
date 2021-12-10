package com.lti.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lti.exception.ResourceNotFoundException;
import com.lti.model.Customer;
import com.lti.service.CustomerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
public class ProducerController {
		
	@Autowired
	private CustomerService service;
	
	
	@RequestMapping(value = "/customer", method = RequestMethod.POST)
	public Customer addCustomer(@RequestBody Customer customer) {

		return service.save(customer);
	}
   
	
	@RequestMapping(value = "/customer", method = RequestMethod.GET)
	public List<Customer>  getCustomerdetails() {		
		 List<Customer> customerList= service.getAll();
		 return customerList;	
	}
	
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.GET)
	public Customer getCustomerdetailbyId(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {		
	 return service.getByid(id);
	
	}
	
	
	
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
	public Map<String, Boolean> deleteCustomerById(@PathVariable(value = "id") Integer id) {
		 Boolean tr=service.deleteById(id);
		 Map<String, Boolean> response = new HashMap<>();
			response.put("deleted", tr);
			return response;
	}
   
	@RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT)
	public Customer updateEmployee(@PathVariable(value = "id") Integer id,
		 @RequestBody Customer customer) throws ResourceNotFoundException {
		 return service.update(id, customer);
	}
	
	
	@RequestMapping(value = "/getcustomer", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "getDataFallBack")
	public List<Customer>  getCustomer() {		
		 List<Customer> customerList= service.getAll();
	
		 if(customerList==null) {
			  new RuntimeException();
		 }
		 return customerList;	
	}
	

	public List<Customer> getDataFallBack() {
		Customer c = new Customer();
		c.setName("demo name");
		c.setAge("21");
		List<Customer> customerlist = new ArrayList<Customer>();
		customerlist.add(c);
		return customerlist; 
	}

}
