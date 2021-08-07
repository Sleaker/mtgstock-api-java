package org.api.mtgstock.modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Interests {

	private Date date;
	private List<Interest> average = new ArrayList<>();
	private List<Interest> averageFoil = new ArrayList<>();
	private List<Interest> market = new ArrayList<>();
	private List<Interest> marketFoil = new ArrayList<>();

}
