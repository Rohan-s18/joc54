package com.rohan.OOAnalysis;

public class BarkListener {
	private DogDoor door;
	
	public BarkListener(DogDoor door) {
		this.door = door;
	}
	
	public void hearbark(Bark bark){
		if(door.checkbark(bark)) {
			if(door.isOpen()) {
				door.close();
			}
			else {
				door.open();
			}
		}
	}
	
	
}


enum Bark{
	WOOF_WOOF,BARK_BARK,WOOF,BARK,HOWL,COO;
	public String toString() {
		switch(this) {
		case WOOF_WOOF:
			return "woof woof";
		case BARK_BARK:
			return "woof woof";
		case WOOF:
			return "woof woof";
		case BARK:
			return "woof woof";
		case HOWL:
			return "woof woof";
		case COO:
			return "woof woof";
		}
		return null;
	}
	
}