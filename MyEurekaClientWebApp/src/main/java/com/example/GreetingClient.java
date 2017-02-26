package com.example;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("MYEUREKACLIENT1")
public interface GreetingClient {
	@RequestMapping("/greeting")
	String greeting();
}