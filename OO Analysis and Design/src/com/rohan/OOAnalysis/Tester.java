package com.rohan.OOAnalysis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.rohan.fp.*;



public class Tester {

	public static void main(String[] args) {
		//System.out.println("Hello World");
		//Tester myTester = new Tester();
		//myTester.go();
		//myTester.dogDoorSimulator();
		//myTester.instrumentSimulator();
		
		com.rohan.fp.Tester.main(args);

	}
	
	public void instrumentSimulator() {
		InstrumentInventory rick = new InstrumentInventory();
		ArrayList<Instrument> myList = new ArrayList<Instrument>();
		
		Map properties = new HashMap();
		properties.put("Builder", Builder.FENDER);
		properties.put("Type", Type.ACCOUSTIC);
		properties.put("Wood", Wood.BASSWOOD);
		InstrumentSpec ideal = new InstrumentSpec(properties, instrumentType.GUITAR);
		
		rick.addInstruments(makeInstruments());
		myList = rick.searchInstrument(ideal);
		
		if(myList.size() > 0) {
			System.out.println("We have " + myList.size() + " matches!!!");
			for(Iterator i = myList.iterator(); i.hasNext();) {
				System.out.println(i.next());
			}
		}
		
	}
	
	public ArrayList<Instrument> makeInstruments(){
		ArrayList myList = new ArrayList<Instrument>();
		
		Map properties = new HashMap();
		properties.put("Builder", Builder.FENDER);
		properties.put("Type", Type.ACCOUSTIC);
		properties.put("Wood", Wood.BASSWOOD);
		InstrumentSpec cero = new InstrumentSpec(properties, instrumentType.GUITAR);
		myList.add(new Instrument("1234",1000,cero));
		
		Map properties1 = new HashMap();
		properties1.put("Builder", Builder.GIBSON);
		properties1.put("Type", Type.BASS);
		properties1.put("Wood", Wood.MAHOGANY);
		InstrumentSpec uno = new InstrumentSpec(properties1, instrumentType.GUITAR);
		myList.add(new Instrument("1128",1100,uno));
		
		Map properties2 = new HashMap();
		properties2.put("Builder", Builder.YAMAHA);
		properties2.put("Type", Type.ELECTRIC);
		properties2.put("Wood", Wood.BASSWOOD);
		InstrumentSpec dos = new InstrumentSpec(properties2, instrumentType.GUITAR);
		myList.add(new Instrument("1182",1500,dos));
		
		Map properties3 = new HashMap();
		properties3.put("Builder", Builder.FENDER);
		properties3.put("Type", Type.ACCOUSTIC);
		properties3.put("Wood", Wood.BASSWOOD);
		InstrumentSpec tres = new InstrumentSpec(properties3, instrumentType.BANJO);
		myList.add(new Instrument("3456",800,tres));
		
		Map properties4 = new HashMap();
		properties4.put("Builder", Builder.FENDER);
		properties4.put("Type", Type.ACCOUSTIC);
		properties4.put("Wood", Wood.BASSWOOD);
		InstrumentSpec quatro = new InstrumentSpec(properties4, instrumentType.EUKELELE);
		myList.add(new Instrument("1548",500,quatro));

		
		
		return myList;
	}
	
	
	
	
	public void dogDoorSimulator() {
		Bark[] validBarks = {Bark.BARK_BARK,Bark.COO,Bark.WOOF_WOOF};
		DogDoor myDoor = new DogDoor(validBarks);
		DogDoorRemote myRemote = new DogDoorRemote(myDoor);
		/*System.out.println();
		myRemote.pressButton();
		System.out.println("\nFido has gone outside");
		System.out.println("\nFido's all done...........");
		
		try {
			Thread.currentThread().sleep(10000);
		} catch(InterruptedException e) {}
		
		System.out.println(".........but he's stuck outside!");
		System.out.println("\nFido starts barking........");
		System.out.println("So Gina grabs the remote control.");
		myRemote.pressButton();
		System.out.println("\nFido is back inside");
		
		System.out.println("\n \n \n \n");
		System.out.println("hear");*/
		
		BarkListener bl = new BarkListener(myDoor);
		bl.hearbark(Bark.COO);
		
		try {
			Thread.currentThread().sleep(10000);
		} catch(InterruptedException e) {}
		
		System.out.println("But Fido is stuck outside");
		
		try {
			Thread.currentThread().sleep(1000);
		} catch(InterruptedException e) {}
		
		System.out.println("Fido Starts Barking");
		
		bl.hearbark(Bark.WOOF_WOOF);
		
		/*try {
			Thread.currentThread().sleep(7000);
		} catch(InterruptedException e) {}
		System.out.println(myDoor.isOpen());*/
		
	}
	
	
	
	
	
	
	public void go() {
		Inventory myInventory = new Inventory();
		myInventory.addPlayer("Lamar Jackson", Position.QB, Team.BALTIMORE_RAVENS, 89);
		myInventory.addPlayer("Patrick Mahomes", Position.QB, Team.KANSAS_CITY_CHIEFS, 91);
		myInventory.addPlayer("Josh Allen", Position.QB, Team.BUFFALO_BILLS, 90);
		myInventory.addPlayer("Aaron Rodgers", Position.QB, Team.GREEN_BAY_PACKERS, 92);
		myInventory.addPlayer("Russell Wilson", Position.QB, Team.SEATTLE_SEAHAWKS, 88);
		myInventory.addPlayer("JK Dobbins", Position.HB, Team.BALTIMORE_RAVENS, 90);
		myInventory.addPlayer("Kareem Hunt", Position.HB, Team.CLEVELAND_BROWNS, 90);
		myInventory.addPlayer("Nick Chubb", Position.HB, Team.CLEVELAND_BROWNS, 88);
		myInventory.addPlayer("Edwards Helaire", Position.HB, Team.KANSAS_CITY_CHIEFS, 80);
		myInventory.addPlayer("Antoinne Fournette", Position.HB, Team.TAMPA_BAY_BUCCANEERS, 85);
		myInventory.addPlayer("Davante Adams", Position.WR, Team.GREEN_BAY_PACKERS, 92);
		myInventory.addPlayer("Tyreek Hill", Position.WR, Team.KANSAS_CITY_CHIEFS, 92);
		myInventory.addPlayer("Steffon Diggs", Position.WR, Team.BUFFALO_BILLS, 90);
		myInventory.addPlayer("Juju Smith Schuster", Position.WR, Team.PITTSBURGH_STEELERS, 89);
		myInventory.addPlayer("Jarvis Landry", Position.WR, Team.CLEVELAND_BROWNS, 85);
		myInventory.addPlayer("Odell Beckham Jr.", Position.WR, Team.CLEVELAND_BROWNS, 88);
		
		ArrayList<Player> temp = new ArrayList<Player>();
		
		temp = myInventory.matchPlayer(new Player("Lamar Jackson", Position.QB, Team.BALTIMORE_RAVENS, 89));
		
		System.out.println(temp.size());
		
		for(Player p : temp) {
			System.out.println(p);
		}
	}

}



























/*abstract class Animal{
	int height;
	int weight;
	String name;
	String sound;
	public void setSize(int h, int w) {
		height = h;
		weight = w;
	}
	public void setName(String n) {
		name = n;
	}
	public abstract void makeSound();
}

class Dog extends Animal{
	public void makeSound() {
		System.out.println("Bark Bark");
	}
	public String toString() {
		return name;
	}
}

class Cat extends Animal{
	public void makeSound() {
		System.out.println("Meow");
	}
}

interface Robot{
	public void scan();
}

class RoboDog extends Dog implements Robot{
	public void scan() {
		System.out.println("Scanning");
	}
}
*/





