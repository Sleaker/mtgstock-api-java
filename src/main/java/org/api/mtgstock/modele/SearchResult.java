package org.api.mtgstock.modele;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SearchResult {

	private Integer id;
	private String name;
	
	@Override
	public String toString() {
		return getName();
	}		
}
