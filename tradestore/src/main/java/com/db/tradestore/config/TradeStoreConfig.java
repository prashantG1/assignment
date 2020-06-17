package com.db.tradestore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.db.tradestore.service.TradeStoreService;
import com.db.tradestore.service.TradeStoreServiceImpl;
/*
 * class: TradeStoreConfig
 */
@Configuration
public class TradeStoreConfig {
	
	@Bean
	public TradeStoreService tradeStoreService() {
		return new TradeStoreServiceImpl();
	}
	
	

}
