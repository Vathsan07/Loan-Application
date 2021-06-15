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

import com.examples.spring.webflux.model.Customer;
import com.examples.spring.webflux.model.ResponseMessage;
import com.examples.spring.webflux.service.CustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	CustomerService custService;

	@GetMapping
	public Flux<Customer> getAllCustomers() {
		return custService.getAllCustomers();
	}

	@GetMapping("/{id}")
	public Mono<Customer> getCustomer(@PathVariable Integer id) {
		return custService.getCustomer(id);
	}

	@PostMapping
	public Mono<ResponseEntity<ResponseMessage>> createCustomer(@RequestBody Customer customer)
			throws URISyntaxException {
		Mono<Customer> emp = custService.createCustomer(customer);

		StringBuilder locationStr = new StringBuilder();
		emp.subscribe(e -> locationStr.append("http://localhost:9010/customers/").append(e.getId()));

		// Getting current resource path
		URI location = new URI(locationStr.toString());

		return Mono.just(ResponseEntity.created(location).body(this.getResponse(customer.getId(), "Customer Created")));
	}

	@PutMapping("/{id}")
	public Mono<ResponseEntity<ResponseMessage>> updateEmployee(@RequestBody Customer customer,
			@PathVariable Integer id) {
		customer.setId(id);
		custService.updateCustomer(customer);

		return Mono.just(ResponseEntity.ok().body(this.getResponse(customer.getId(), "Customer Updated")));
	}

	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<ResponseMessage>> deleteEmployee(@PathVariable Integer id) {
		custService.deleteCustomer(id);
		ResponseMessage response = getResponse(id, "Customer Deleted");

		return Mono.just(ResponseEntity.accepted().body(response));
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
