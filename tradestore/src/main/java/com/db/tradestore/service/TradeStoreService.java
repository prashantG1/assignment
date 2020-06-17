package com.db.tradestore.service;

import java.util.List;

import com.db.tradestore.model.TradeResponse;
import com.db.tradestore.model.TradeStore;

public interface TradeStoreService {
	
	/*
	 * @param:tradeStore
	 * @return:tradeResponse
	 */
	public TradeResponse Trade(TradeStore tradeStore) ;
	/*
	 * @return:List<TradeStore>
	 */
	public List<TradeStore> retriveAllTradeRecords();

}
