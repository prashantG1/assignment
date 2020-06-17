package com.db.tradestore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
/*
 * class: TradestoreApplication
 */
@SpringBootApplication
@EnableScheduling
public class TradestoreApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(TradestoreApplication.class, args);
	}

}
