package com.Arius.myNetwork;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

@SpringBootApplication
public class MyNetworkApplication {
	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	public static void main(String[] args) {
		SpringApplication.run(MyNetworkApplication.class, args);
	}

}
