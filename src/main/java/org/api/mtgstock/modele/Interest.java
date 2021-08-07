package org.api.mtgstock.modele;

import java.util.Date;

import org.api.mtgstock.tools.MTGStockConstants.FORMAT;
import org.api.mtgstock.tools.MTGStockConstants.PRICES;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Interest {

	private Boolean foil;
	private Date date;
	private Double percentage;
	private String interestType;
	private Double pricePresent;
	private Double pricePast;
	private Print print = new Print();
	private PRICES category;

	public Boolean isFoil() {
		return foil;
	}

	public boolean isLegalFor(FORMAT f) {
		return print.isLegalFor(f);
	}

	public double getPriceDayChange() {
		return getPricePresent() - getPricePast();
	}

	@Override
	public String toString() {
		return print + ":" + getPricePresent();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null)
			return false;

		if (!(obj instanceof Interest))
			return false;

		return this.getPrint().getId() == ((Interest) obj).getPrint().getId();
	}

	@Override
	public int hashCode() {
		return this.getPrint().hashCode();
	}

}
