package com.db.tradestore.job;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.db.tradestore.constant.TradeStoreConstant;
import com.db.tradestore.dao.TradeStoreRepository;
import com.db.tradestore.model.TradeStore;
import java.text.ParseException;
/*
 * class: TradeStoreScheduler
 */
@Component
public class TradeStoreScheduler {
	
	@Autowired
	TradeStoreRepository tradeStoreRepository;
	private static final Logger logger = LogManager.getLogger(TradeStoreScheduler.class);
	SimpleDateFormat formatter = new SimpleDateFormat(TradeStoreConstant.DATE_FORMAT);
		/*
		 * to execute on given interval and updating database
		 */
	@Scheduled(fixedRateString = "${console.fetchMetrics}")
	public void updateExpiryRecords() {
		logger.info("Retrieving all non expired recoreds to check maturity date");
		List<TradeStore> tradeList= tradeStoreRepository.findByExpiryFlag();
		
		tradeList.forEach(tradeEntry->{
				try {
					logger.info("Updating expire flag if maturity date is null, blank or less then todays date");
			if (tradeEntry.getMaturityDate() == null || tradeEntry.getMaturityDate().isEmpty() || formatter.parse(formatter.format(new Date())).compareTo(formatter.parse(tradeEntry.getMaturityDate())) > 0) {
				logger.info("Found trade with expired maturity date::"+tradeEntry.getMaturityDate()+ ", so updating database as expired record");
				tradeEntry.setExpired("Y");
				tradeStoreRepository.save(tradeEntry);
			}
				}catch(ParseException pe) {pe.printStackTrace();}
				
			
		});
		
		
		
		
		
	}
	

}
