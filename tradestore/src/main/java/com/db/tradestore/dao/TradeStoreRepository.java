package com.db.tradestore.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.db.tradestore.model.TradeStore;

@Repository
public interface TradeStoreRepository extends JpaRepository<TradeStore, Long> {
	
	  /*
	   * @param: String
	   * @return:tradeStore
	   */
	  @Query(value="SELECT *  FROM TRADE_STORE  WHERE  VERSION= (select  max(VERSION)  from  TRADE_STORE  where TRADE_ID= ?1   )", nativeQuery = true)
	  TradeStore findByTradeId(String  tradeID);
	  
	  /*
	    * @return: list<TradeStore>
	   */
	  @Query(value="SELECT *  FROM TRADE_STORE  WHERE  expired= 'N' ", nativeQuery = true)
	  List<TradeStore> findByExpiryFlag();

}
