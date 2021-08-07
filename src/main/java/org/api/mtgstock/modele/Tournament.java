package org.api.mtgstock.modele;

import java.util.Date;

import lombok.Data;

@Data
public class Tournament {

	private Integer id;
	private String name;
	private Integer numPlayers;
	private String tournamentType;
	private Date date;
	
	@Override
	public String toString() {
		return getName();
	}

}
