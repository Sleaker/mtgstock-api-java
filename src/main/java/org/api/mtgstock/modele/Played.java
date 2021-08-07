package org.api.mtgstock.modele;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Played {

	private Integer quantity;
	private Integer id;
	private String name;
	private String image;
	private double avgPrice;
	
	
	@Override
	public String toString() {
		return getName();
	}

}
