package com.examples.spring.webflux.service;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.examples.spring.webflux.model.Customer;

import reactor.test.StepVerifier;

public class CustomerServiceImplTests {
	
	CustomerServiceImpl custService;
	
	
	
	@BeforeAll
	public static void setup() {
		System.out.println("Before All called...");
	}

	@BeforeEach
	public void setupTest() {
		System.out.println("Before Each called...");
		custService = new CustomerServiceImpl();		
	}
	
	@Test
	public void testGetEmployees() {
		
		custService.createCustomer(new Customer(1, "Anand","Chennai","India","TN","GGAESP1019H","SAVINGS"));
		
		StepVerifier
		.create(custService.getAllCustomers())
		.expectNextCount(4)
		.expectComplete()
		.verify();			
	}
	
	@Test
	public void givenEmployee_whenCreateEmployee_thenReturnCreatedEmployee() {
		
		Customer cust = new Customer(2, "Shri","Hyd","India","TN","GGASKM1239H","CURRENT");
		
		StepVerifier
		.create(custService.createCustomer(cust))
		.expectNext(cust)
		.expectComplete()
		.verify();
				
		StepVerifier
		.create(custService.getCustomer(5))
		.expectNext(cust)
		.expectComplete()
		.verify();			
		
	}	
	

	@AfterAll
	public static void tearDown() {
		
		System.out.println("After All called...");
	}

}
