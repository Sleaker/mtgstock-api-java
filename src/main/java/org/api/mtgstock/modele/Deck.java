package org.api.mtgstock.modele;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.api.mtgstock.tools.MTGStockConstants.FORMAT;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Deck {

	private Integer id;
	private String name;
	private String player;
	private Object commander;
	private Object commanderPartner;
	private Integer position;
	private Integer tournamentId;
	private Date date;
	private Archetype archetype;
	private FORMAT format;
	private Map<DeckCard, Integer> mainboard = new ConcurrentHashMap<>();
	private Map<DeckCard, Integer> sideboard = new ConcurrentHashMap<>();
	
	@Override
	public String toString() {
		return getName();
	}	
}
