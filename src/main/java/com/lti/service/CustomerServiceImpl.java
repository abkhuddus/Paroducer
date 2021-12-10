package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dao.CustomerRepository;
import com.lti.model.Customer;

@Service
public class CustomerServiceImpl implements CustomerService {

	
	@Autowired
	private CustomerRepository repo;
	
	@Override
	public Customer save(Customer customer) {
		return repo.save(customer);
	}

	@Override
	public Customer getByid(Integer id) {
		 Customer c=repo.findOne(id);
		 if(c!=null) {
			 return c;
		 }
		return null;
	}

	@Override
	public List<Customer> getAll() {
	
		return repo.findAll();
	}

	@Override
	public boolean deleteById(Integer id) {
		
		Customer c=repo.findOne(id);
		if(c!=null) {
			repo.delete(c);
			return true;
		}
		return false;
	}

	
	@Override
	public Customer update(Integer id,Customer customer) {
		try {
			if(customer!=null) {
				Customer c=repo.findOne(customer.getId());
				 if(c!=null) {
					 c.setName(customer.getName());
					 c.setAge(customer.getAge());
					 c.setAdress(customer.getAdress());
					 c.setAccountType(customer.getAccountType());
			        return repo.save(customer);
				 }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
