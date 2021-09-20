package com.rohan.OOAnalysis;

import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;

public class InstrumentSpec {
	private Map properties;
	instrumentType iType;
	
	public InstrumentSpec(Map properties,instrumentType iType) {
		this.properties = new HashMap(properties);
		this.iType = iType;
	}
	
	public Object getProperty(String propertyName) {
		return properties.get(propertyName);
	}
	
	public instrumentType getInstrumentType() {
		return iType;
	}
	
	public Map getProperties() {
		return properties;
	}
	
	public String toString() {
		return "Model: " + properties.get("Model")+ ", Builder: " + properties.get("Builder");
	}
	
	public boolean matches(InstrumentSpec otherSpec) {
		for(Iterator i = otherSpec.getProperties().keySet().iterator(); i.hasNext();){
			String propertyName = (String)i.next();
			if(!(otherSpec.getInstrumentType().equals(iType))) {
				return false;
			}
			if(properties.get(propertyName).equals(otherSpec.getProperty(propertyName))) {
				return true;
			}
		}
		return false;
	}

}




enum Builder{
	GIBSON,GUILD,SEAGULL,YAMAHA,FENDER;
	public String toString() {
		switch(this) {
		case GIBSON:
			return "gibson";
		case GUILD:
			return "guild";
		case SEAGULL:
			return "seagull";
		case YAMAHA:
			return "yamaha";
		case FENDER:
			return "fender";
		}
		return null;
	}
}



enum Type{
	ELECTRIC,BASS,CLASSICAL,ACCOUSTIC,SPANISH;
	public String toString() {
		switch(this) {
		case ELECTRIC:
			return "electric";
		case BASS:
			return "bass";
		case CLASSICAL:
			return "classical";
		case ACCOUSTIC:
			return "accoustic";
		case SPANISH:
			return "spanish";
		}
		return null;
	}
}


enum Wood{
	BASSWOOD,MAHOGANY,SWAMPASH,WALNUT,KOA;
	public String toString() {
		switch(this) {
		case BASSWOOD:
			return "basswood";
		case MAHOGANY:
			return "mahogany";
		case SWAMPASH:
			return "swampash";
		case WALNUT:
			return "walnut";
		case KOA:
			return "koa";
		}
		return null;
	}
}













