package com.lti.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.lti.ProducerApplication;
import com.lti.dao.CustomerRepository;
import com.lti.model.Customer;
import com.lti.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProducerApplication.class)
public class CustomerServiceTest {

	
	@MockBean
	   
    private  CustomerRepository repo;
	
	@Autowired
	private CustomerService service;
	
	
	@Test
    public void testCreateCustomer(){
		Customer c=new Customer();
		   c.setName("ab");
		   c.setAdress("hyd");
		   c.setAge("125");
		   c.setId(12);
		   c.setAccountType("Sallery");
		   
        Mockito.when(repo.save(c)).thenReturn(c);;
        assertThat(service.save(c)).isEqualTo(c);
    }
	
	@Test
	public void getByid() {
		   Customer ticket = new Customer();
	        ticket.setId(102);
	        ticket.setName("Kumar");
	        ticket.setAdress("Chennai");
	        ticket.setAccountType("Saving");
	        ticket.setAge("12");
	     Customer tt=repo.findOne(100);
	        Mockito.when(tt).thenReturn(ticket);
	        assertThat(service.getByid(100)).isEqualTo(ticket);
	}
	
	 @Test
	    public void testGetAllBookedTickets() throws Exception{
		  Customer ticket1 = new Customer();
		    ticket1.setId(102);
	        ticket1.setName("Kumar");
	        ticket1.setAdress("Chennai");
	        ticket1.setAccountType("Saving");
	        ticket1.setAge("12");

	        Customer ticket2 = new Customer();
	        ticket2.setId(102);
	        ticket2.setName("Kumar");
	        ticket2.setAdress("Chennai");
	        ticket2.setAccountType("Saving");
	        ticket2.setAge("12");

	        List<Customer> ticketList = new ArrayList<>();
	        ticketList.add(ticket1);
	        ticketList.add(ticket2);

	        Mockito.when(repo.findAll()).thenReturn(ticketList);
	        assertThat(service.getAll()).isEqualTo(ticketList);
	    }



}
