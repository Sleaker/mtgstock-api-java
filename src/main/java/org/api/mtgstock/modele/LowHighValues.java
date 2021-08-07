package org.api.mtgstock.modele;

import java.util.Date;

import org.api.mtgstock.tools.MTGStockConstants.PRICES;

import lombok.Data;

@Data
public class LowHighValues {

	private PRICES type;
	private Print print;
	private EntryValue<Date, Double> price;

}
