package org.mtgstock.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.mtgstock.modele.Archetype;
import org.mtgstock.modele.CardDetails;
import org.mtgstock.modele.DeckCard;
import org.mtgstock.modele.EntryValue;
import org.mtgstock.modele.Interest;
import org.mtgstock.modele.Legality;
import org.mtgstock.modele.Print;
import org.mtgstock.modele.CardSet;
import org.mtgstock.tools.MTGStockConstants;
import org.mtgstock.tools.MTGStockConstants.CATEGORY;
import org.mtgstock.tools.MTGStockConstants.FORMAT;
import org.mtgstock.tools.Tools;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public abstract class AbstractMTGStockService {
	
	protected static final String PREVIOUS_PRICE = "previous_price";
	protected static final String LAST_WEEK_PRICE = "last_week_price";
	protected static final String COST = "cost";
	protected static final String ABBREVIATION = "abbreviation";
	protected static final String INCLUDE_DEFAULT = "include_default";
	protected static final String SET_ID = "set_id";
	protected static final String SET_NAME = "set_name";
	protected static final String ICON_CLASS = "icon_class";
	protected static final String SET_TYPE = "set_type";
	protected static final String TYPE = "type";
	protected static final String LEGAL = "legal";
	protected static final String NORMAL = "normal";
	protected static final String LOWEST_PRINT = "lowest_print";
	protected static final String SET = "set";
	protected static final String CARD_TYPE = "card_type";
	protected static final String CMC = "cmc";
	protected static final String COLOR = "color";
	protected static final String RESERVED = "reserved";
	protected static final String AVG = "avg";
	protected static final String NAME = "name";
	protected static final String QUANTITY = "quantity";
	protected static final String CARD = "card";
	protected static final String PRINT = "print";
	protected static final String LATEST_PRICE = "latest_price";
	protected static final String IMAGE = "image";
	protected static final String ID = "id";
	protected static final String PAST_PRICE = "past_price";
	protected static final String PRESENT_PRICE = "present_price";
	protected static final String PERCENTAGE = "percentage";
	protected static final String INTEREST_TYPE = "interest_type";
	protected static final String DATE = "date";
	protected static final String URL = "url";
	protected static final String LATEST_PRICE_MM = "latest_price_mm";
	protected static final String LATEST_PRICE_MKM = "latest_price_mkm";
	protected static final String LATEST_PRICE_CK = "latest_price_ck";
	protected static final String PRICE = "price";
	protected static final String ALL_TIME_HIGH = "all_time_high";
	protected static final String ALL_TIME_LOW = "all_time_low";
	protected static final String CARD_SET = "card_set";
	protected static final String TCG_URL = "tcg_url";
	protected static final String TCG_ID = "tcg_id";
	protected static final String MKM_URL = "mkm_url";
	protected static final String MKM_ID = "mkm_id";
	protected static final String IMAGE_FLIP = "image_flip";
	protected static final String FLIP = "flip";
	protected static final String FOIL = "foil";
	protected static final String RARITY = "rarity";
	protected static final String SETS = "sets";
	protected static final String MULTIVERSE_ID = "multiverse_id";
	protected static final String SIMILARITY = "similarity";
	protected static final String MOSTPLAYED = "mostplayed";
	protected static final String LOW = "low";
	protected static final String OLD = "old";
	protected static final String NUM_PLAYERS = "num_players";
	protected static final String TOURNAMENTTYPE = "tournamenttype";
	protected static final String POSITION = "position";
	protected static final String PLAYER = "player";
	protected static final String DECKS = "decks";
	protected static final String ARCHETYPE = "archetype";
	protected static final String TOTAL = "total";
	protected static final String PLACING = "placing";

	protected static final String DATE_FORMAT = "yyyy-MM-dd";



	protected Logger logger = LogManager.getLogger(this.getClass());

	
	

	protected Print parsePrintFor(JsonObject obj) {
		Print p = new Print();
			  p.setId(obj.get(ID).getAsInt());
			  p.setName(obj.get(NAME).getAsString());
			  p.setRarity(obj.get(RARITY).getAsString());
			  
			  if(obj.get(RESERVED)!=null)
				  p.setReserved(obj.get(RESERVED).getAsBoolean());
			 
			  if(obj.get(SET_ID)!=null)
				  p.setSetId(obj.get(SET_ID).getAsInt());
			 
			  
			  if(obj.get(FOIL)!=null)
				  p.setFoil(obj.get(FOIL).getAsBoolean());
			  
			  if(obj.get(SET_NAME)!=null)
				  p.setSetName(obj.get(SET_NAME).getAsString());
			  
			  
			  if(obj.get(ICON_CLASS)!=null && !obj.get(ICON_CLASS).isJsonNull())
				  p.setIconClass(obj.get(ICON_CLASS).getAsString());

			  
			  if(obj.get(SET_TYPE)!=null)
				  p.setSetType(obj.get(SET_TYPE).getAsString());
			  
			  if(obj.get(INCLUDE_DEFAULT)!=null)
					 p.setIncludeDefault(obj.get(INCLUDE_DEFAULT).getAsBoolean());
			
			  
			  if(obj.get(LAST_WEEK_PRICE)!=null)
				  	p.setLastWeekPrice(obj.get(LAST_WEEK_PRICE).getAsDouble());
			  
			  if(obj.get(PREVIOUS_PRICE)!=null)
				  	p.setLastWeekPreviousPrice(obj.get(PREVIOUS_PRICE).getAsDouble());
			  
			  
			  
			  
			  p.setExtendedArt(obj.get(NAME).getAsString().contains(MTGStockConstants.EXTENDED_ART));
			  p.setOversized(obj.get(NAME).getAsString().contains(MTGStockConstants.OVERSIZED));
			  p.setBorderless(obj.get(NAME).getAsString().contains(MTGStockConstants.BORDERLESS));
			  p.setShowcase(obj.get(NAME).getAsString().contains(MTGStockConstants.SHOWCASE));
			  
			  
			  if(obj.get(LATEST_PRICE)!=null)
			  {
				  obj.get(LATEST_PRICE).getAsJsonObject().keySet().forEach(key->{
					  try {
						  p.getLatestPrices().add(new EntryValue<>(MTGStockConstants.PRICES.valueOf(key.toUpperCase()),obj.get(LATEST_PRICE).getAsJsonObject().get(key).getAsDouble()));
					  }
					  catch(Exception e)
					  {
						  //do nothing
					  }
				  });
			  }
			  
			  if(obj.get(LEGAL)!=null)
				  for(String key : obj.get(LEGAL).getAsJsonObject().keySet())
				  {
					  try {
						p.getLegal().add(new Legality(FORMAT.valueOf(key.toUpperCase()), obj.get(LEGAL).getAsJsonObject().get(key).getAsString()));
					} catch (Exception e) {
						logger.error("Not legality found for " + key);
					}
				  }
			
			  
			  try {
				p.setImage(obj.get(IMAGE).getAsString());
				} catch (Exception e) {
					//do nothing
				}
			 
		
		return p;
	}
	
	protected CardSet parseSetFor(JsonObject o) {
		CardSet set = new CardSet();
			set.setId(o.get(ID).getAsInt());
			set.setName(o.get(NAME).getAsString());
			set.setIconClass(o.get(ICON_CLASS).getAsString());
			set.setSetType(o.get(SET_TYPE).getAsString());
			
			try {
				set.setAbbrevation(o.get(ABBREVIATION).getAsString());
			}
			catch(Exception e)
			{
				//do nothing

				if(set.getId()==305)
					set.setAbbrevation("FBB");
				
				if(set.getId()==306)
					set.setAbbrevation("FWB");
				
				if(set.getId()==370)
					set.setAbbrevation("PLIST");
				
				if(set.getId()==117)
					set.setAbbrevation("PPRO");
				
				if(set.getId()==116)
					set.setAbbrevation("PPRE");
				
				if(set.getId()==377)
					set.setAbbrevation("AMH1");
				
				if(set.getId()==351)
					set.setAbbrevation("PBBD");
				
				if(set.getId()==236)
					set.setAbbrevation("PCMP");
				
				if(set.getId()==355)
					set.setAbbrevation("M21");
				
				if(set.getId()==353)
					set.setAbbrevation("IKO");
				
				if(set.getId()==356)
					set.setAbbrevation("2XM");
				
				if(set.getId()==345)
					set.setAbbrevation("THB");
				
				if(set.getId()==304)
					set.setAbbrevation("DPA");
				
				if(set.getId()==333)
					set.setAbbrevation("CEI");
				
				if(set.getId()==342)
					set.setAbbrevation("CMB1");
				
				if(set.getId()==325)
					set.setAbbrevation("GK2");
				
				if(set.getId()==228)
					set.setAbbrevation("PCEL");
				
				if(set.getId()==367)
					set.setAbbrevation("SUM");
				
				if(set.getId()==245)
					set.setAbbrevation("UGIN");
				
				if(set.getId()==369)
					set.setAbbrevation("AZNR");
			}
			
		
		
		return set;
	}


	protected CardDetails parseCardFor(JsonObject o) {
		
		CardDetails c = new CardDetails();
			 c.setId(o.get(ID).getAsInt());
			 c.setCmc(o.get(CMC).getAsInt());
			 c.setCost(o.get(COST).getAsString());
			 o.get(LEGAL).getAsJsonObject().entrySet().forEach(e->c.getLegal().add(new Legality(FORMAT.valueOf(e.getKey().toUpperCase()), e.getValue().getAsString())));
			 c.setLowestPrint(o.get(LOWEST_PRINT).getAsInt());
			 c.setName(o.get(NAME).getAsString());
			 c.setOracle(o.get("oracle").getAsString());
			 c.setPwrtgh(o.get("pwrtgh").getAsString());
			 c.setReserved(o.get(RESERVED).getAsBoolean());
			 c.setSubtype(o.get("subtype").getAsString());
			 c.setSupertype(o.get("supertype").getAsString());
			 
		return null;
	}


	protected Archetype parseArchetypeFor(JsonObject e) {
		Archetype at = new Archetype();
		at.setId(e.get(ID).getAsInt());
		at.setName(e.get(NAME).getAsString());
		
		if(e.get(OLD)!=null)
			at.setOld(e.get(OLD).getAsBoolean());
		
		return at;
	}
	
	protected DeckCard parseDeckCardFor(JsonObject o)
	{
		DeckCard d = new DeckCard();
				 d.setCardType(o.get(CARD_TYPE).getAsString());
				 d.setCmc(o.get(CMC).getAsInt());
				 d.setColor(o.get(COLOR).getAsString());
				 d.setName(o.get(NAME).getAsString());
				 d.setSet(parseSetFor(o.get(LOWEST_PRINT).getAsJsonObject().get(SET).getAsJsonObject()));
		return d;
	}
	
	protected List<Interest> parseInterestFor(CATEGORY c,boolean foil,JsonObject interests)
	{
		JsonArray arrs = interests.get( (foil)?FOIL:NORMAL).getAsJsonArray(); 
		List<Interest> ret = new ArrayList<>();
		
		for(JsonElement e : arrs)
		{
			JsonObject obj = e.getAsJsonObject();
			Interest t = new Interest();
					 t.setCategory(c);
					 t.setDate(new Date(obj.get(DATE).getAsLong()));
				     t.setInterestType(obj.get(INTEREST_TYPE).getAsString());
					 t.setPercentage(obj.get(PERCENTAGE).getAsDouble());
					 t.setPricePresent(obj.get(PRESENT_PRICE).getAsDouble());
					 t.setPricePast(obj.get(PAST_PRICE).getAsDouble());
					 t.setPrint(parsePrintFor(obj.get(PRINT).getAsJsonObject()));
					 t.setFoil(foil);
					 
			 ret.add(t);
		}
		return ret;
	}
	
	
}