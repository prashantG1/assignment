package com.db.tradestore.service;

import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.db.tradestore.constant.TradeStoreConstant;
import com.db.tradestore.dao.TradeStoreRepository;
import com.db.tradestore.model.TradeResponse;
import com.db.tradestore.model.TradeStore;
/*
 * Class:TradeStoreServiceImpl
 *  
 */
public class TradeStoreServiceImpl implements TradeStoreService{

	@Autowired
	TradeStoreRepository tradeStoreRepository;
	
	private static final Logger logger = LogManager.getLogger(TradeStoreServiceImpl.class);;
	SimpleDateFormat formatter = new SimpleDateFormat(TradeStoreConstant.DATE_FORMAT);
	/*
	 * @param:tradeStore
	 * @return:tradeResponse
	 */
	public TradeResponse Trade(TradeStore tradeStore) {
		TradeResponse tradeResponse=new TradeResponse();
		logger.info("checking existing record into database for tradeID"+tradeStore.getTradeID());
		TradeStore existingTradeEntry= tradeStoreRepository.findByTradeId(tradeStore.getTradeID());
		if(existingTradeEntry!=null) {
			logger.info("existing record found into database for tradeID"+tradeStore.getTradeID());
			if(Integer.parseInt(existingTradeEntry.getVersion())<Integer.parseInt(tradeStore.getVersion())) {
				logger.info("existing record with lower version id  so inserting new record into database");
				tradeResponse=saveTrade(tradeStoreRepository,tradeStore);

			}else if(Integer.parseInt(existingTradeEntry.getVersion())==Integer.parseInt(tradeStore.getVersion())){
				logger.info("existing record and new record have same version so updating existing record with new record into database");
				tradeStore.setId(existingTradeEntry.getId());
				BeanUtils.copyProperties(tradeStore, existingTradeEntry);
				tradeResponse=saveTrade(tradeStoreRepository,existingTradeEntry);
			}else{
				logger.info("new record has lower version of existing record so sending error message");
				tradeResponse.setStatus(TradeStoreConstant.FAILURE);
				tradeResponse.setCode(TradeStoreConstant.FAIL_101);//fail_101
				tradeResponse.setDescription(TradeStoreConstant.DESC_LOWER_VERSION_MESSAGE);
			}
		}
		else {
			logger.info("Record with new Trade id so inserting into databse");
			tradeResponse=saveTrade(tradeStoreRepository,tradeStore);
 	}
		return tradeResponse;
	}
	/*
	 * @param:TradeStoreRepository
	 * @param:TradeStore
	 * @return:tradeResponse
	 */
	private TradeResponse saveTrade(TradeStoreRepository tradeStoreRepository,TradeStore tradeStore) {

		TradeResponse tradeResponse=new TradeResponse();
		try {
			tradeStore.setCreatedDate(String.valueOf(formatter.format(new java.util.Date())));
			tradeStoreRepository.save(tradeStore);
			tradeResponse.setStatus(TradeStoreConstant.SUCCESS);
			tradeResponse.setCode(TradeStoreConstant.SUC_200);//SEC_200
			tradeResponse.setDescription(TradeStoreConstant.DESC_SUCCESS_MESSAGE);
		}catch(DataAccessException e) {
			e.printStackTrace();
			tradeResponse.setStatus(TradeStoreConstant.FAILURE);
			tradeResponse.setCode(TradeStoreConstant.FAIL_105);//fail_105
			tradeResponse.setDescription(TradeStoreConstant.DESC_FAILURE_MESSAGE);

		}

		return tradeResponse;
	}
	
	/*
	 * @return:List<TradeStore>
	 */
public List<TradeStore> retriveAllTradeRecords() {
		
		return tradeStoreRepository.findAll();
	}

}
