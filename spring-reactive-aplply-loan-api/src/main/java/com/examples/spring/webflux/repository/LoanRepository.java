package com.examples.spring.webflux.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.examples.spring.webflux.model.Loan;

import reactor.core.publisher.Flux;

public interface LoanRepository extends ReactiveCrudRepository<Loan, Integer>{
	

}
