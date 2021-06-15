package com.examples.spring.webflux.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examples.spring.webflux.model.Customer;
import com.examples.spring.webflux.repository.CustomerRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements CustomerService {

	/*	private Map<Integer, Customer> customers = new HashMap<>();

	{

		customers.put(1, new Customer(1, "Anand","Chennai","India","TN","GGAESP1019H","SAVINGS"));
		customers.put(2, new Customer(2, "Shri","Hyd","India","TN","GGASKM1239H","CURRENT"));
		customers.put(3, new Customer(3, "Vidhya","Chennai","India","TN","DSFGSP2819H","SAVINGS"));
	}
	 */
	@Autowired
	CustomerRepository customers;

	@Override
	public Flux<Customer> getAllCustomers() {
		//return Flux.fromIterable(customers.values());
		return customers.findAll();
	}

	@Override
	public Mono<Customer> getCustomer(Integer empId) {
		//return Mono.just(customers.get(empId));
		return customers.findById(empId);
	}

	@Override
	public Mono<Customer> createCustomer(Customer customer) {

		//customers.put(customer.getId(), customer);
		//return Mono.just(customer);
		return customers.save(customer);
	}

	@Override
	public Mono<Boolean> updateCustomer(Customer customer) {
		//customers.put(customer.getId(), customer);
		try {		

			customers.save(customer).block();
		}catch(Exception e) {
			return Mono.just(Boolean.FALSE);
		}
		return Mono.just(Boolean.TRUE);
	}

	@Override
	public Mono<Boolean> deleteCustomer(Integer empId) {
		//customers.remove(empId);
		try {
			customers.deleteById(empId).block();
		}catch(Exception e) {
			return Mono.just(Boolean.FALSE);
		}
		return Mono.just(Boolean.TRUE);
	}

}
