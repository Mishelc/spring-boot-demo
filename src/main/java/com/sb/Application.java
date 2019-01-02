package com.sb;

/*
 * https://grokonez.com/spring-framework/use-spring-jpa-postgresql-spring-boot
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sb.repo.BloggerRepository;

@SpringBootApplication
public class Application implements CommandLineRunner{

	
	@Autowired
	BloggerRepository bloggerRepository;
	
	public static void main(String[] args){
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		// clear all record if existed before do the tutorial with new data 
		//repository.deleteAll();
	}
}
