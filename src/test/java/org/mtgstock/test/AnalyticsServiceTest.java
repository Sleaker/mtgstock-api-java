package org.mtgstock.test;

import org.junit.Test;
import org.mtgstock.modele.Interests;
import org.mtgstock.services.AnalyticsService;
import org.mtgstock.services.InterestsService;
import org.mtgstock.tools.MTGStockConstants.CATEGORY;
import org.mtgstock.tools.MTGStockConstants.FORMAT;

public class AnalyticsServiceTest {

	
	
	@Test
	public void testSetPrices()
	{
		InterestsService serv = new InterestsService();
		serv.getInterestFor(CATEGORY.MARKET,true, FORMAT.STANDARD).forEach(System.out::println);
	}
}