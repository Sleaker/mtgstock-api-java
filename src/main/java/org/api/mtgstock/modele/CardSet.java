package org.api.mtgstock.modele;

import java.util.Date;

import lombok.Data;

@Data
public class CardSet {

	private Integer id;
	private String name;
	private String abbrevation;
	private String iconClass;
	private String setType;
	private Date date;
	private CardSet extraSet;
	
	@Override
	public String toString() {
		return getName();
	}	
}
