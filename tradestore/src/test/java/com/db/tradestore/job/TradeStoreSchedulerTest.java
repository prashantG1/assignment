package com.db.tradestore.job;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import com.db.tradestore.model.TradeStore;
import com.db.tradestore.dao.TradeStoreRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes= TradeStoreScheduler.class)
public class TradeStoreSchedulerTest {
	
	@Mock
	private TradeStoreRepository mockRepository;
	@InjectMocks
	TradeStoreScheduler scheduler;
	@Before
	public void init() {
		Mockito.when(mockRepository.save(Mockito.anyObject())).thenReturn(null);
	}
	
	//Expired maturity date scenario : Expired flag should be updated into database
		@Test
		public void saveNewTrade() throws Exception {
			
			TradeStore tradeStore=new TradeStore("T1","3","CP_3","B3","16/06/2021","","N");
			TradeStore tradeStoreWithExpiredMaturity=new TradeStore("T1","3","CP_3","B3","16/06/2019","","N");
			List<TradeStore> tradeList=new ArrayList<TradeStore>();
			tradeList.add(tradeStore);
			tradeList.add(tradeStoreWithExpiredMaturity);
					
					Mockito.when(mockRepository.findByExpiryFlag()).thenReturn(tradeList);
					 
					
					scheduler.updateExpiryRecords();
		
			
			
			
			 
			 
		}


	


}
