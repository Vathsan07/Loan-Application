package com.examples.spring.webflux.client;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.reactive.function.client.WebClient;



import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class SpringReactiveCustomerRegistrationWebclientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringReactiveCustomerRegistrationWebclientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		//Customer service webclient
		
		//Creating Webclient for Customer registration service
		WebClient webClient =WebClient.create("http://localhost:9010");
   
	 	//Update Customer Account detail based on ID	
	   Customer custUpdate = new Customer(1,"shreevathsa","Maharashtra","India","MH","DSFGSP2819H","SAVINGS");
	   Mono<ResponseMessage> response1= 
			   							webClient.put()
			   							.uri("/customers/{id}",1)
			   							.bodyValue(custUpdate)
			   							.retrieve()
			   							.bodyToMono(ResponseMessage.class); 
	   
	   response1.subscribe(r -> System.out.printf("Customer details updated successfully. ", r.getMessage()));

	   //Printing the specific customer details after modification
	   Mono<Customer> customer =
                                webClient.get()
                                .uri("/customers/{id}",1)
                                .retrieve()
                                .bodyToMono(Customer.class);
	   customer.subscribe(c -> System.out.printf("The details of Customer ID: 1(After updation) - ID :%s, name : %s, Address: %s, Pancard:%s, State:%s, country:%s, AccountType:%s \n",c.getId(),c.getName(),c.getAddress(),c.getPancard(),c.getState(),c.getCountry(),c.getAccounttype()));
	}

}
