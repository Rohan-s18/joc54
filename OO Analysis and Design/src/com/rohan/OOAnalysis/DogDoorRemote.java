package com.rohan.OOAnalysis;

public class DogDoorRemote {
	private DogDoor door;
	
	public DogDoorRemote(DogDoor door) {
		this.door = door;
	}
	
	public void pressButton() {
		System.out.println("Pressing the remote button");
		if(door.isOpen()) {
			door.close();
		}
		else {
			door.open();
		}
	}

}
