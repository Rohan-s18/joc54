package com.rohan.OOAnalysis;

public class Instrument {
	String serialNumber;
	double price;
	InstrumentSpec mySpec;
	
	public Instrument(String serialNumber, double price, InstrumentSpec spec) {
		this.serialNumber = serialNumber;
		this.price = price;
		mySpec = spec;
		
	}
	
	public String getSerialNumber() {
		return serialNumber;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
	
	public InstrumentSpec getSpec() {
		return mySpec;
	}
	
	public String toString() {
		return "Instrument: " + mySpec.getInstrumentType() + ", Serial Number: #"  + serialNumber + ", for: $" + price;
	}
	
}


enum instrumentType{
	GUITAR,MANDOLIN,BANJO,EUKELELE;
	
	public String toString() {
		switch(this) {
		case GUITAR:
			return "Guitar";
		case MANDOLIN:
			return "Mandolin";
		case BANJO:
			return "Banjo";
		case EUKELELE:
			return "Eukelele";
		}
		return "";
	}
	
}



