package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class GreetingService {

	@Autowired
	private GreetingClient greetingClient;

	@HystrixCommand(fallbackMethod = "circuitBreakerGreeting")
	public String getGreeting() {
		return greetingClient.greeting();
	}
	
	public String circuitBreakerGreeting() {
		return "This is complimentory greeting from sachin";
	}
}
