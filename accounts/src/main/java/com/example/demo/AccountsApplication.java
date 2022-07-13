package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@RefreshScope
//@EnableJpaRepositories("com.example.demo.repository")
//@EntityScan("com.example.demo.model")
@RequiredArgsConstructor
public class AccountsApplication {

	@Bean
	public ModelMapper modelMapper(){

		return new ModelMapper();
	}

	public static void main(String[] args) {

		SpringApplication.run(AccountsApplication.class, args);
	}

//	@EventListener(ApplicationReadyEvent.class)
//	public void triggerMail() throws MessagingException {
//		emailSender.send("nogevi3085@meidir.com",
//				"This is email body",
//				"This is email subject");
//
//	}

}
