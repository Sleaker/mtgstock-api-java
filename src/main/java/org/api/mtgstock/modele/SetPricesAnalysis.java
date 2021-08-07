package org.api.mtgstock.modele;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.api.mtgstock.tools.MTGStockConstants.PRICES;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SetPricesAnalysis {

	private Map<PRICES, PriceVariations> prices = new EnumMap<>(PRICES.class);
	private CardSet cardSet;
	private List<PriceHash> priceHash = new ArrayList<>();
	private PriceHash booster;

}
