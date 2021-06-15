package com.examples.spring.webflux.service;

import com.examples.spring.webflux.model.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
	public Flux<Customer> getAllCustomers();
	public Mono<Customer> getCustomer(Integer empId);
	public Mono<Customer> createCustomer(Customer employee);
	public Mono<Boolean> updateCustomer(Customer employee);
	public Mono<Boolean> deleteCustomer(Integer empId);
}
