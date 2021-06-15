package com.examples.spring.webflux.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.examples.spring.webflux.model.Loan;
import com.examples.spring.webflux.model.ResponseMessage;
import com.examples.spring.webflux.repository.LoanRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class LoanServiceImpl implements LoanService{

	@Autowired
	LoanRepository repo;
	
	public Mono<Loan> applyLoan(Loan loanDetails) {
		//loan.put(loanDetails.getCustomerId(), loanDetails);
		//return Mono.just(loanDetails);
		return repo.save(loanDetails);
	}

	@Override
	public Mono<Loan> getCustomerLoanDetails(Integer Id) {
	 //System.out.println("Control reached here");
    //return Mono.just(loan.get(Id));
		return repo.findById(Id);
	}
	
	@Override
	public Flux<Loan> getAllCustomerDetails() {
		//return Flux.fromIterable(customers.values());
		return repo.findAll();
	}
	
	@Override
	public Mono<Boolean> updateCustomer(Loan loan) {
		//customers.put(customer.getId(), customer);
		try {		

			repo.save(loan).block();
		}catch(Exception e) {
			return Mono.just(Boolean.FALSE);
		}
		return Mono.just(Boolean.TRUE);
	}
}