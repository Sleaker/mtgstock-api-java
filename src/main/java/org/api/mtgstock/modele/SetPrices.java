package org.api.mtgstock.modele;

import java.util.EnumMap;
import java.util.Map;

import org.api.mtgstock.tools.MTGStockConstants.PRICES;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SetPrices {

	private CardSet set;
	private Map<PRICES, Double> prices = new EnumMap<>(PRICES.class);
	private Integer num;

	@Override
	public String toString() {
		return String.valueOf(getSet());
	}

	public void put(PRICES p, Double value) {
		prices.put(p, value);
	}
}
