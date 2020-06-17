package com.db.tradestore.controller;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.db.tradestore.model.TradeResponse;
import com.db.tradestore.model.TradeStore;
import com.db.tradestore.service.TradeStoreServiceImpl;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
@WebMvcTest(value = TradeStoreContoller.class)
public class TradeStoreContollerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TradeStoreServiceImpl TradeStoreService;


	TradeResponse tradeResponse=new TradeResponse();






	//success scenario : new trade entry with valid maturity date
	@Test
	public void saveNewTrade() throws Exception {

		tradeResponse.setCode("SEC_200");
		tradeResponse.setStatus("success");
		tradeResponse.setDescription("Successfully save/update trade");
		String tradeStoreJson = "{\"tradeID\":\"T1\",\"version\":\"1\",\"counterPartyID\":\"CP-1\",\"bookID\":\"B1\",\"maturityDate\":\"16/06/2021\",\"expired\":\"N\"}";


		Mockito.when(TradeStoreService.Trade(Mockito.anyObject())).thenReturn(tradeResponse);


		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/tradeEntry")
				.contentType(MediaType.APPLICATION_JSON)
				.content(tradeStoreJson);


		MvcResult result = mockMvc.perform(requestBuilder).andReturn();


		String expected = "{\"status\":\"success\",\"code\":\"SEC_200\",\"description\":\"Successfully save/update trade\"}";



		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(),
				false);

	}

	// scenario: maturity date less then todays date 
	@Test
	public void saveWithLessMaturityDate() throws Exception {

		tradeResponse.setCode("SEC_200");
		tradeResponse.setStatus("success");
		tradeResponse.setDescription("Successfully save/update trade");
		String tradeStoreJson = "{\"tradeID\":\"T1\",\"version\":\"1\",\"counterPartyID\":\"CP-1\",\"bookID\":\"B1\",\"maturityDate\":\"16/06/2019\",\"expired\":\"N\"}";


		Mockito.when(TradeStoreService.Trade(Mockito.anyObject())).thenReturn(tradeResponse);


		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/tradeEntry")
				.contentType(MediaType.APPLICATION_JSON)
				.content(tradeStoreJson);


		MvcResult result = mockMvc.perform(requestBuilder).andReturn();


		String expected = "{\"status\":\"failure\",\"code\":\"fail_102\",\"description\":\"Maturity date less then todays date\"}";


		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(),
				false);

	}

	// scenario: retrieve all trade records. 
	@Test
	public void retrieveAllData() throws Exception {
		List<TradeStore> tradeList=new ArrayList<TradeStore>();
		TradeStore trade1=new TradeStore("T1","1","CP_1","B1","16/06/2021","","N");
		TradeStore trade2=new TradeStore("T1","2","CP_2","B2","16/08/2021","","N");
		tradeList.add(trade1);
		tradeList.add(trade2);


		Mockito.when(TradeStoreService.retriveAllTradeRecords()).thenReturn(tradeList);


		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/all")
				.contentType(MediaType.APPLICATION_JSON);




		MvcResult result = mockMvc.perform(requestBuilder).andReturn();



		assertNotNull(result.getResponse());



	}

}
