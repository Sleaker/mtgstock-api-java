package org.api.mtgstock.modele;

import java.util.Date;

import org.api.mtgstock.tools.MTGStockConstants.FORMAT;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Metagame {

	private Archetype archetype;
	private Date date;
	private FORMAT format;
	private Integer placings;
	private Integer total;
	
	
	@Override
	public String toString() {
		return String.valueOf(getArchetype());
	}
}
