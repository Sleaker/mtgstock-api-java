package org.api.mtgstock.modele;

import lombok.Data;

@Data
public class DeckCard {

	private String cardType;
	private String name;
	private Integer cmc;
	private String color;
	private CardSet set;
	
	
	@Override
	public String toString() {
		return getName();
	}

}
