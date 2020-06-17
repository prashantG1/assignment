package com.db.tradestore.model;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes= TradeStore.class)
public class TradeStoreTest {
	
	//checking tradestore value
	@Test
	public void tradeStoreTest() throws Exception {
		TradeStore reqTradeStore=new TradeStore("T2","3","CP_3","B3","16/06/2020","","N");
		assertEquals(reqTradeStore.getTradeID(),"T2");
		assertEquals(reqTradeStore.getVersion(),"3");
		assertEquals(reqTradeStore.getCounterPartyID(),"CP_3");
		assertEquals(reqTradeStore.getBookID(),"B3");
		assertEquals(reqTradeStore.getMaturityDate(),"16/06/2020");
		assertEquals(reqTradeStore.getExpired(),"N");
		
	}

}
