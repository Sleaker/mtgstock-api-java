package org.api.mtgstock.modele;

import java.util.EnumMap;
import java.util.Map;

import org.api.mtgstock.tools.MTGStockConstants.PRICES;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SealedProduct {
	private int id;
	private String name;
	private String urlImage;
	private String slug;

	private Map<PRICES, Double> latestPrices = new EnumMap<>(PRICES.class);

	@Override
	public String toString() {
		return getName();
	}

	public boolean isIntroPack() {
		return getName().startsWith("Intro Pack");
	}

	public boolean isVIP() {
		return getName().contains("VIP");
	}

	public boolean isBundle() {
		return getName().startsWith("Bundle");
	}

	public boolean isGift() {
		return getName().contains("Gift");
	}

	public boolean isFatPack() {
		return getName().startsWith("Fat Pack");
	}

	public boolean isBooster() {
		return getName().contains("Booster Pack") || getName().contains("VIP Edition Pack");
	}

	public boolean isTheme() {
		return getName().startsWith("Theme ");
	}

	public boolean isPrerelease() {
		return getName().startsWith("Prerelease ");
	}

	public boolean isSet() {
		return getName().startsWith("Set ");
	}

	public boolean isBox() {
		return getName().contains("Booster Box") || getName().contains("Booster Display");
	}

	public boolean isCollector() {
		return getName().contains("Collector");
	}

	public boolean isDraft() {
		return getName().contains("Draft");
	}

	public boolean isCase() {
		return getName().contains(" Case");
	}

	public boolean isStarter() {
		return getName().equalsIgnoreCase("Starter Deck");
	}

	public boolean isPlaneswalkerDeck() {
		return getName().contains("Planeswalker Deck");
	}

	public boolean isChallengerDeck() {
		return getName().contains("Challenger Deck");
	}
}
