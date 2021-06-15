package com.examples.spring.webflux.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examples.spring.webflux.model.Loan;
import com.examples.spring.webflux.model.ResponseMessage;
import com.examples.spring.webflux.service.LoanService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
public class LoanController {

	@Autowired
	LoanService loanService;
	
	@PostMapping("/loan")
	public Mono<ResponseEntity<ResponseMessage>> ApplyLoan(@RequestBody Loan loanDetails)
			throws URISyntaxException {
		Mono<Loan> emp = loanService.applyLoan(loanDetails);

		StringBuilder locationStr = new StringBuilder();
		emp.subscribe(e -> locationStr.append("http://localhost:8082/customers/loan/").append(e.getcustomerId()));

		// Getting current resource path
		URI location = new URI(locationStr.toString());

		return Mono.just(ResponseEntity.created(location).body(this.getResponse(loanDetails.getcustomerId(), "Loan Applied")));
	}
	
	@GetMapping("/loan/{id}")
	public Mono<Loan> getCustomerLoanDetails(@PathVariable Integer id) {
		System.out.println("Postman hit the get api");
		return loanService.getCustomerLoanDetails(id);
	}
	
	@GetMapping("/loan")
	public Flux<Loan> getAllCustomerDetails() {
		return loanService.getAllCustomerDetails();
	}
	
	@PutMapping("/{id}")
	public Mono<ResponseEntity<ResponseMessage>> updateEmployee(@RequestBody Loan loan,
			@PathVariable Integer id) {
		loan.setcustomerId(id);
		loanService.updateCustomer(loan);

		return Mono.just(ResponseEntity.ok().body(this.getResponse(loan.getcustomerId(), "Customer Updated")));
	}
	
	private ResponseMessage getResponse(Integer id, String message) {
		ResponseMessage response = new ResponseMessage();
		response.setId(id);
		response.setStatus(HttpStatus.OK.name());
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage(message);
		return response;
	}
}