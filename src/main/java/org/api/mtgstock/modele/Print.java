package org.api.mtgstock.modele;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.api.mtgstock.tools.MTGStockConstants;
import org.api.mtgstock.tools.MTGStockConstants.FORMAT;
import org.api.mtgstock.tools.MTGStockConstants.PRICES;
import org.api.mtgstock.tools.MTGStockConstants.RARITY;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.api.mtgstock.tools.Tools;

@Data
@NoArgsConstructor
public class Print {

	protected Integer id;
	protected String name;
	protected String namePrecision;
	protected String image;
	protected String iconClass;
	protected List<Legality> legal = new ArrayList<>();
	protected RARITY rarity;
	protected Boolean reserved;
	protected Integer setId;
	protected String setName;
	protected String setType;
	protected Boolean includeDefault;
	protected boolean extendedArt;
	protected boolean oversized;
	protected boolean borderless;
	protected boolean showcase;
	protected boolean foil;
	protected boolean fullArt;
	protected boolean etched;
	protected boolean japanese;

	protected Map<PRICES, Double> latestPrices = new EnumMap<>(PRICES.class);
	protected Double lastWeekPrice;
	protected Double lastWeekPreviousPrice;

	public String getWebPage() {
		return MTGStockConstants.MTGSTOCK_WEBSITE_URI + "/prints/" + getId();
	}

	public boolean isLegalFor(FORMAT f) {
		Optional<Legality> opt = getLegal().stream().filter(l -> l.getFormat() == f).findAny();

		if (opt.isEmpty())
			return false;

		return opt.get().getLegal().equalsIgnoreCase("legal");

	}

	@Override
	public String toString() {
		return getCleanName();
	}

	public Boolean isReserved() {
		return reserved;
	}

	public Boolean isIncludeDefault() {
		return includeDefault;
	}

	public void setName(String name) {
		this.name = name;
		setNamePrecision(Tools.extractParenthesisValue(name));
	}

	public String getCleanName() {
		return getName().replaceAll("\\([^()]*\\)", "").replace(MTGStockConstants.FULL_ART, "").trim();
	}
}
