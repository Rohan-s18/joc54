package com.rohan.OOAnalysis;

import java.util.ArrayList;

public class InstrumentInventory {
	private ArrayList<Instrument> instrumentList;
	
	public InstrumentInventory() {
		instrumentList = new ArrayList<Instrument>();
	}
	
	public void addInstrument(String serialNumber, double price, InstrumentSpec spec) {
		instrumentList.add(new Instrument(serialNumber,price,spec));
	}
	
	public void addInstrument(Instrument instrument) {
		instrumentList.add(instrument);
	}

	public void addInstruments(ArrayList<Instrument> list) {
		for(int i = 0; i < list.size(); i++) {
			addInstrument(list.get(i));
		}
	}
	
	public ArrayList<Instrument> searchInstrument(InstrumentSpec spec){
		ArrayList<Instrument> myList = new ArrayList<Instrument>();
		for(Instrument i: instrumentList) {
			if(spec.matches(i.getSpec())) {
				myList.add(i);
			}
		}
		return myList;
	}
	
	public void showInventory() {
		for(int i = 0; i < instrumentList.size(); i++) {
			System.out.println(instrumentList.get(i));
		}
	}

}
