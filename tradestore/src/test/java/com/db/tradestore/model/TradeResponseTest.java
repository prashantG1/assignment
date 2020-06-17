package com.db.tradestore.model;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes= TradeResponse.class)
public class TradeResponseTest {
	
	//checking tradeResponse value
		@Test
		public void tradeResponseTest() throws Exception {
			TradeResponse response=new TradeResponse();
			response.setCode("suc_200");
			response.setStatus("success");
			response.setDescription("Successfully save/update trade");
			
			assertEquals(response.getCode(),"suc_200");
			assertEquals(response.getStatus(),"success");
			assertEquals(response.getDescription(),"Successfully save/update trade");
		
			
		}


}
