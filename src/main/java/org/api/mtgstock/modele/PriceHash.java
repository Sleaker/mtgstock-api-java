package org.api.mtgstock.modele;

import java.util.ArrayList;
import java.util.List;

import org.api.mtgstock.tools.MTGStockConstants.PRICES;
import org.api.mtgstock.tools.MTGStockConstants.RARITY;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PriceHash {

	private RARITY rarity;
	
	private List<EntryValue<PRICES, Double>> avg = new ArrayList<>() ;
	private List<EntryValue<PRICES, Double>> sum = new ArrayList<>();
	
	private int num;
	
}
