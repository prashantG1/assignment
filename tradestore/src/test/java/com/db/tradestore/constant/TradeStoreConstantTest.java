package com.db.tradestore.constant;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(classes= TradeStoreConstant.class)
public class TradeStoreConstantTest {
	
	//checking constant value
			@Test
			public void constantTest() throws Exception {
				assertEquals(TradeStoreConstant.SUC_200 ,"suc_200");
				assertEquals(TradeStoreConstant.FAIL_101 ,"fail_101");
				assertEquals(TradeStoreConstant.FAIL_102 ,"fail_102");
				assertEquals(TradeStoreConstant.FAIL_105 ,"fail_105");
				assertEquals(TradeStoreConstant.SUCCESS ,"success");
				assertEquals(TradeStoreConstant.FAILURE ,"failure");
				assertEquals(TradeStoreConstant.DATE_FORMAT ,"dd/MM/yyyy");
			}

}
