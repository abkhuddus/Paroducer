package com.lti.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lti.model.Customer;
@Service
public interface CustomerService {

	
	public Customer save(Customer customer);
	
	public Customer getByid(Integer id);
	
	public List<Customer> getAll();
	
	public boolean deleteById(Integer id);
	
	public Customer update(Integer id,Customer customer); 
	
}
