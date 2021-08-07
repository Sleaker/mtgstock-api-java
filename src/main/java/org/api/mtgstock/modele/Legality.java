package org.api.mtgstock.modele;

import org.api.mtgstock.tools.MTGStockConstants.FORMAT;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Legality {

	private FORMAT format;
	private String legal;

	@Override
	public String toString() {
		return getFormat() + ":" + getLegal();
	}

}
