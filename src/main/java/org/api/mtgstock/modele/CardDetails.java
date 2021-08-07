package org.api.mtgstock.modele;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CardDetails {

	private Integer id;
	private Integer cmc;
	private String name;
	private String cost;
	private List<Legality> legal = new ArrayList<>();
	private Integer lowestPrint;
	private String oracle;
	private String pwrtgh;
	private Boolean reserved;
	private String subtype;
	private String supertype;
	private String[] splitcost;
	
	public Boolean isReserved() {
		return reserved;
	}

	@Override
	public String toString() {
		return getName();
	}
}
