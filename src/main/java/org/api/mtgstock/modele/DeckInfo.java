package org.api.mtgstock.modele;

import java.util.Objects;

import lombok.Data;

@Data
public class DeckInfo {
	
	
	private int id;
	private Archetype archetype;
	private String playerName;
	private Integer position;
	
	
	@Override
	public String toString() {
		return Objects.isNull(archetype) ? "" : archetype.getName();
	}
	
}
