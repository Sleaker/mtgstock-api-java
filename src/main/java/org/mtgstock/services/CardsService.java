package org.mtgstock.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.mtgstock.modele.EntryValue;
import org.mtgstock.modele.FullPrint;
import org.mtgstock.modele.Interest;
import org.mtgstock.modele.Print;
import org.mtgstock.modele.SearchResult;
import org.mtgstock.modele.Set;
import org.mtgstock.tools.MTGStockConstants;
import org.mtgstock.tools.Tools;
import org.mtgstock.tools.URLTools;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class CardsService extends AbstractMTGStockService {



	public SearchResult getBestResult(String name) {
		Optional<SearchResult> o = search(name).stream().max(Comparator.comparing(SearchResult::getSimilarity));
		
		if(o.isPresent())
			return o.get();
		else
			return null;
	}


	public List<SearchResult> search(String name)
	{
		String url = MTGStockConstants.MTGSTOCK_API_URI+"/search/autocomplete/"+name.replace(" ", "%20");
		List<SearchResult> ret = new ArrayList<>();
		
		try {
			JsonArray arr = URLTools.extractJson(url).getAsJsonArray();
			for(JsonElement e : arr)
			{
				JsonObject oe = e.getAsJsonObject();
				ret.add(new SearchResult(oe.get(ID).getAsInt(), oe.get(NAME).getAsString(), oe.get(SIMILARITY).getAsDouble()));
			}
		} catch (IOException e) {
			logger.error(e);
		}
		return ret;
	}
	
	public List<FullPrint> toPrints(List<SearchResult> res)
	{
		return toPrints(res.stream().map(SearchResult::getId).toArray(Integer[]::new));
	}
	
	public List<FullPrint> toPrints(Integer... ids)
	{
		
		List<FullPrint> ret = new ArrayList<>();

		for(Integer id : ids)
		{
			try {
				ret.add(getCard(id));
			} catch (IOException e) {
				logger.error("Error getting card for id " + id,e);
			}
		}
		
		return ret;
	}
	
	public FullPrint getCard(Interest sr) throws IOException
	{
		return getCard(sr.getPrint().getId());
	}
	

	public FullPrint getCard(SearchResult sr) throws IOException
	{
		return getCard(sr.getId());
	}
	
	public FullPrint getCard(Print p) throws IOException
	{
		return getCard(p.getId());
	}
	
	public List<Set> listSets()
	{
		String url = MTGStockConstants.MTGSTOCK_API_URI+"/card_sets";
		
		logger.debug("getting sets at " + url);
		List<Set> sets = new ArrayList<>();
		
		
		try {
			for(JsonElement e : URLTools.extractJson(url).getAsJsonArray())
				sets.add(parseSetFor(e.getAsJsonObject()));
		
		} catch (IOException e) {
			logger.error("Error gettings sets ",e);
			
		}
		
		return sets;
	}
	
	
	
	
	public FullPrint getCard(Integer id) throws IOException
	{
		
		String url = MTGStockConstants.MTGSTOCK_API_URI+"/prints/"+id;
		
		logger.debug("read card at " + url);
		JsonObject o = URLTools.extractJson(url).getAsJsonObject();
		
		FullPrint fp = new FullPrint();
				  fp.setId(o.get(ID).getAsInt());
				  fp.setName(o.get(NAME).getAsString());
				  fp.setNamePrecision(Tools.extractParenthesisValue(fp.getName()));
				  fp.setBorderless(o.get(NAME).getAsString().contains(MTGStockConstants.BORDERLESS));
				  fp.setExtendedArt(o.get(NAME).getAsString().contains(MTGStockConstants.EXTENDED_ART));
				  fp.setShowcase(o.get(NAME).getAsString().contains(MTGStockConstants.SHOWCASE));
				  fp.setOversized(o.get(NAME).getAsString().contains(MTGStockConstants.OVERSIZED));
				  fp.setRarity(o.get(RARITY).getAsString());
				  fp.setFoil(o.get(FOIL).getAsBoolean());
				  fp.setFlip(o.get(FLIP).getAsBoolean());
				  fp.setImageFlip(o.get(IMAGE_FLIP).getAsString());
				  fp.setImage(o.get(IMAGE).getAsString());
				  fp.setMkmId(o.get(MKM_ID).getAsInt());
				  fp.setMkmUrl(o.get(MKM_URL).getAsString());
				  fp.setTcgId(o.get(TCG_ID).getAsInt());
				  fp.setTcgUrl(o.get(TCG_URL).getAsString());
				  fp.setCard(parseCardFor(o.get(CARD).getAsJsonObject()));
				  fp.setCardSet(parseSetFor(o.get(CARD_SET).getAsJsonObject()));
				  fp.setAllTimeLow(new EntryValue<>(o.get(ALL_TIME_LOW).getAsJsonObject().get(AVG).getAsDouble(),Tools.initDate(o.get(ALL_TIME_LOW).getAsJsonObject().get(DATE).getAsLong())));
				  fp.setAllTimeHigh(new EntryValue<>(o.get(ALL_TIME_HIGH).getAsJsonObject().get(AVG).getAsDouble(),Tools.initDate(o.get(ALL_TIME_HIGH).getAsJsonObject().get(DATE).getAsLong())));
				  
				  if(!o.get(LATEST_PRICE_CK).getAsJsonObject().get(PRICE).isJsonNull())
					  fp.setLatestPriceCardKingdom(new EntryValue<>(o.get(LATEST_PRICE_CK).getAsJsonObject().get(PRICE).getAsDouble(),o.get(LATEST_PRICE_CK).getAsJsonObject().get(URL).getAsString()));
				  
				  if(!o.get(LATEST_PRICE_MKM).getAsJsonObject().get(AVG).isJsonNull())
					  fp.setLatestPriceMkm(new EntryValue<>(o.get(LATEST_PRICE_MKM).getAsJsonObject().get(AVG).getAsDouble(),o.get(LATEST_PRICE_MKM).getAsJsonObject().get(LOW).getAsDouble()));
				  
				  if(!o.get(LATEST_PRICE_MM).getAsJsonObject().get(PRICE).isJsonNull())
					  fp.setLatestPriceMiniatureMarket(new EntryValue<>(o.get(LATEST_PRICE_MM).getAsJsonObject().get(PRICE).getAsDouble(),o.get(LATEST_PRICE_MM).getAsJsonObject().get(URL).getAsString()));
				  
				  if(!o.get(MULTIVERSE_ID).isJsonNull())
					  fp.setMultiverseId(o.get(MULTIVERSE_ID).getAsInt());
				  
				  
				  o.get(LATEST_PRICE).getAsJsonObject().keySet().forEach(key->{
					  try {
						  fp.getLatestPrices().add(new EntryValue<>(MTGStockConstants.PRICES.valueOf(key.toUpperCase()),o.get(LATEST_PRICE).getAsJsonObject().get(key).getAsDouble()));
					  }
					  catch(Exception e)
					  {
						  //do nothing
					  }
				  });
				  
				  
					o.get(SETS).getAsJsonArray().forEach(je->{
							JsonObject obj = je.getAsJsonObject();
							fp.getSets().add(parsePrintFor(obj));
					});
					  
		return fp;
	}

	
	
	
	
}
