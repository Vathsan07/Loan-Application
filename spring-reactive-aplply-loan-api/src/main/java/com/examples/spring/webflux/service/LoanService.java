package com.examples.spring.webflux.service;



import com.examples.spring.webflux.model.Loan;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LoanService {
	public Mono<Loan> getCustomerLoanDetails(Integer empId);
	public Mono<Loan> applyLoan(Loan employee);
	public Flux<Loan> getAllCustomerDetails();
	public Mono<Boolean> updateCustomer(Loan employee);
}
