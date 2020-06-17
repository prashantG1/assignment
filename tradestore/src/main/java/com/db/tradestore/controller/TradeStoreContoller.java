package com.db.tradestore.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.db.tradestore.constant.TradeStoreConstant;
import com.db.tradestore.model.TradeResponse;
import com.db.tradestore.model.TradeStore;
import com.db.tradestore.service.TradeStoreService;


/*
 * class:TradeStoreContoller
 */
@RestController
public class TradeStoreContoller {
	
	@Autowired
	TradeStoreService tradeStoreService;
	
	
	private static final Logger logger = LogManager.getLogger(TradeStoreContoller.class);
	/*
	 * @param:TradeStore
	 * @return:tradeResponse
	 */
	@RequestMapping(value="/tradeEntry", method = RequestMethod.POST)
	public TradeResponse Trade(@RequestBody TradeStore tradeStore) {
		SimpleDateFormat formatter = new SimpleDateFormat(TradeStoreConstant.DATE_FORMAT);
		TradeResponse tradeResponse =new TradeResponse();
		try {
			logger.info("Checking Maturity date with current date");
		if (formatter.parse(formatter.format(new Date())).compareTo(formatter.parse(tradeStore.getMaturityDate())) > 0) {
			tradeResponse.setStatus(TradeStoreConstant.FAILURE);
			tradeResponse.setCode(TradeStoreConstant.FAIL_102);//fail_102
			tradeResponse.setDescription(TradeStoreConstant.DESC_Maturity_MESSAGE);
		}else {
			tradeResponse=tradeStoreService.Trade(tradeStore);	
		}
		}catch(ParseException pe) {pe.printStackTrace();}
		return tradeResponse;
	}
	
	/*
	 * @return: list<TradeStore>
	 */
	@RequestMapping(value="/all")
	public List<TradeStore> retriveAllTradeRecords() {
		
		return tradeStoreService.retriveAllTradeRecords();
	}

}
