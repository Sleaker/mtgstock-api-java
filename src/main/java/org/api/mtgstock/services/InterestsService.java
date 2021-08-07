package org.api.mtgstock.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.api.mtgstock.modele.Interest;
import org.api.mtgstock.modele.Interests;
import org.api.mtgstock.tools.MTGStockConstants;
import org.api.mtgstock.tools.MTGStockConstants.FORMAT;
import org.api.mtgstock.tools.MTGStockConstants.PRICES;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class InterestsService extends AbstractMTGStockService {

	private Interests interests;

	public void reload() {
		interests = null;
		interests = getInterests();
	}

	public List<Interest> getInterestFor(PRICES c, boolean foil, FORMAT f) {
		return getInterestFor(c, foil).stream().filter(i -> i.isLegalFor(f)).collect(Collectors.toList());
	}

	public List<Interest> getInterestFor(PRICES c, FORMAT f) {
		return getInterestFor(c).stream().filter(i -> i.isLegalFor(f)).collect(Collectors.toList());
	}

	public List<Interest> getInterestFor(PRICES c) {
		List<Interest> ret = new ArrayList<>();

		ret.addAll(getInterestFor(c, true));
		ret.addAll(getInterestFor(c, false));

		return ret;
	}

	public List<Interest> getInterestFor(PRICES c, boolean foil) {
		log.debug("GetInterest for " + c + " " + ((foil) ? "Foil" : ""));

		if (c == PRICES.AVERAGE)
			return (foil) ? getInterests().getAverageFoil() : getInterests().getAverage();

		return (foil) ? getInterests().getMarketFoil() : getInterests().getMarket();

	}

	public Interests getInterests() {

		if (interests != null)
			return interests;

		interests = new Interests();

		String urlAvg = MTGStockConstants.MTGSTOCK_API_URI + "/interests/average";
		String urlMkt = MTGStockConstants.MTGSTOCK_API_URI + "/interests/market";

		try {
			log.debug("init connection");
			client.doGet(MTGStockConstants.MTGSTOCK_WEBSITE_URI);
			log.debug("init connection done");
		} catch (IOException e1) {
			log.error(e1);
			return interests;
		}

		try {
			var interestJson = client.extractJson(urlAvg).getAsJsonObject();
			interests.setDate(new Date(interestJson.get(DATE).getAsLong()));

			interests.setAverage(parseInterestFor(PRICES.AVERAGE, interestJson.get(PRICES.AVERAGE.name().toLowerCase())
					.getAsJsonObject().get(NORMAL).getAsJsonArray()));
			interests.setAverageFoil(parseInterestFor(PRICES.AVERAGE, interestJson
					.get(PRICES.AVERAGE.name().toLowerCase()).getAsJsonObject().get(FOIL).getAsJsonArray()));

			interestJson = client.extractJson(urlMkt).getAsJsonObject();
			interests.setMarket(parseInterestFor(PRICES.MARKET, interestJson.get(PRICES.MARKET.name().toLowerCase())
					.getAsJsonObject().get(NORMAL).getAsJsonArray()));
			interests.setMarketFoil(parseInterestFor(PRICES.MARKET,
					interestJson.get(PRICES.MARKET.name().toLowerCase()).getAsJsonObject().get(FOIL).getAsJsonArray()));

			log.debug("Interests are stored in memory at date : " + interests.getDate());

		} catch (Exception e) {
			log.error("error getting interests", e);
		}
		return interests;
	}

}
