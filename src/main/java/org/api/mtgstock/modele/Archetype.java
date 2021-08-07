package org.api.mtgstock.modele;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Archetype {

	
	private Integer id;
	private String name;
	private Boolean old;

	public Boolean isOld() {
		return old;
	}
		
	@Override
	public String toString() {
		return getName();
	}
}
