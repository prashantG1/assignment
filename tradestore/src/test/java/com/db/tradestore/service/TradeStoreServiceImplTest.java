package com.db.tradestore.service;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import com.db.tradestore.dao.TradeStoreRepository;
import com.db.tradestore.model.TradeResponse;
import com.db.tradestore.model.TradeStore;
import com.db.tradestore.service.TradeStoreServiceImpl;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes= TradeStoreServiceImpl.class)
public class TradeStoreServiceImplTest {


	@Mock
	private TradeStoreRepository mockRepository;


	TradeResponse tradeResponse=new TradeResponse();
	@InjectMocks
	TradeStoreServiceImpl tradeStoreServiceImpl;

	//inserting entry with New Trade Id
	@Test
	public void entry_NewTrade_success() throws Exception {	
		TradeStore reqTradeStore=new TradeStore("T2","3","CP_3","B3","16/06/2021","","N");

		TradeStore mockTradeStore=null;
		Mockito.when(mockRepository.findByTradeId(reqTradeStore.getTradeID())).thenReturn(mockTradeStore);		

		Mockito.when(mockRepository.save(Mockito.anyObject())).thenReturn(null);
		tradeResponse=tradeStoreServiceImpl.Trade(reqTradeStore);
		assertEquals(tradeResponse.getCode() ,"suc_200");		

	}
	//Existing trade id with higher version-should insert into database 
	@Test
	public void entry_ExistingTrade__HigherVersion_success() throws Exception {	
		TradeStore reqTradeStore=new TradeStore("T1","4","CP_3","B3","16/06/2021","","N");

		TradeStore mockTradeStore=new TradeStore("T1","3","CP_1","B1","16/06/2021","","N");
		Mockito.when(mockRepository.findByTradeId(reqTradeStore.getTradeID())).thenReturn(mockTradeStore);		

		Mockito.when(mockRepository.save(Mockito.anyObject())).thenReturn(null);
		tradeResponse=tradeStoreServiceImpl.Trade(reqTradeStore);
		assertEquals(tradeResponse.getCode() ,"suc_200");		

	}

	//Existing trade id with same version-should update into database
	@Test
	public void entry_ExistingTrade__SameVersion_success() throws Exception {	
		TradeStore reqTradeStore=new TradeStore("T1","3","CP_3","B3","16/06/2021","","N");

		TradeStore mockTradeStore=new TradeStore("T1","3","CP_1","B1","16/06/2021","","N");
		Mockito.when(mockRepository.findByTradeId(reqTradeStore.getTradeID())).thenReturn(mockTradeStore);		

		Mockito.when(mockRepository.save(Mockito.anyObject())).thenReturn(null);
		tradeResponse=tradeStoreServiceImpl.Trade(reqTradeStore);
		assertEquals(tradeResponse.getCode() ,"suc_200");		

	}

	//Existing trade id with lower version-should through error
	@Test
	public void entry_ExistingTrade__LowerVersion_failure() throws Exception {	
		TradeStore reqTradeStore=new TradeStore("T1","2","CP_3","B3","16/06/2021","","N");

		TradeStore mockTradeStore=new TradeStore("T1","3","CP_1","B1","16/06/2021","","N");
		Mockito.when(mockRepository.findByTradeId(reqTradeStore.getTradeID())).thenReturn(mockTradeStore);		


		tradeResponse=tradeStoreServiceImpl.Trade(reqTradeStore);
		assertEquals(tradeResponse.getCode() ,"fail_101");		

	}
	//Existing/new trade id -database exception in save  
	@Test
	public void entry_ExistingOrNewTrade__Database_Exception() throws Exception {	
		TradeStore reqTradeStore=new TradeStore("T2","3","CP_3","B3","16/06/2021","","N");

		TradeStore mockTradeStore=new TradeStore("T1","3","CP_1","B1","16/06/2021","","N");
		Mockito.when(mockRepository.findByTradeId(reqTradeStore.getTradeID())).thenReturn(mockTradeStore);		

		Mockito.when(mockRepository.save(Mockito.anyObject())).thenThrow(new DataAccessException("error to persist record") {
		});
		tradeResponse=tradeStoreServiceImpl.Trade(reqTradeStore);
		assertEquals(tradeResponse.getCode() ,"fail_105");		

	}
	
	// scenario: retrieve all trade records. 
	@Test
	public void retrieveAllData() throws Exception {
		List<TradeStore> tradeList=new ArrayList<TradeStore>();
		TradeStore trade1=new TradeStore("T1","1","CP_1","B1","16/06/2021","","N");
		TradeStore trade2=new TradeStore("T1","2","CP_2","B2","16/08/2021","","N");
		tradeList.add(trade1);
		tradeList.add(trade2);
		
		Mockito.when(mockRepository.findAll()).thenReturn(tradeList);
		List<TradeStore> resTradeList=tradeStoreServiceImpl.retriveAllTradeRecords();
		assertNotNull(resTradeList);
	}

}
