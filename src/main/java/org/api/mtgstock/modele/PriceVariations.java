package org.api.mtgstock.modele;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Set;

import org.api.mtgstock.tools.MTGStockConstants.PRICES;

import lombok.Getter;

public class PriceVariations implements Iterable<Entry<Date, Double>> {

	private Map<Date, Double> map = new ConcurrentHashMap<>();
	@Getter
	private PRICES categ;

	public PriceVariations(PRICES categ) {
		this.categ = categ;
	}

	public void put(Date d, Double value) {
		map.put(d, value);
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public Iterator<Entry<Date, Double>> iterator() {
		return map.entrySet().iterator();
	}

	public Entry<Date, Double> getHigher() {
		return Collections.max(map.entrySet(),
				(Entry<Date, Double> e1, Entry<Date, Double> e2) -> e1.getValue().compareTo(e2.getValue()));
	}

	public Entry<Date, Double> getLower() {
		return Collections.min(map.entrySet(),
				(Entry<Date, Double> e1, Entry<Date, Double> e2) -> e1.getValue().compareTo(e2.getValue()));
	}

	public List<Entry<Date, Double>> asList() {
		return new ArrayList<>(entrySet());
	}

	public Collection<Double> values() {
		return map.values();
	}

	public Set<Entry<Date, Double>> entrySet() {
		return map.entrySet();
	}

	@Override
	public String toString() {
		return "HistoryPrice=" + map.size();
	}

}
