package org.api.mtgstock.modele;

import java.util.EnumMap;
import java.util.Map;

import org.api.mtgstock.tools.MTGStockConstants.PRICES;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SealedPricesAnalysis {

	private Map<PRICES,PriceVariations> prices = new EnumMap<>(PRICES.class);
			
}
