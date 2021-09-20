package com.rohan.OOAnalysis;

import java.util.Timer;
import java.util.TimerTask;

public class DogDoor {
	private boolean open;
	private Bark[] allowedBarks;
	
	public DogDoor(Bark[] bark) {
		open = false;
		allowedBarks = bark;
	}
	
	public void open() {
		System.out.println("The Dog Door is now open");
		open = true;
		final Timer  timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				close();
				timer.cancel();
			}
		}		
		,5000);
		
	}
	
	public void close() {
		System.out.println("The Dog Door is now close");
		open = false;
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public Bark[] getAllowedBarks() {
		return allowedBarks;
	}
	
	public boolean checkbark(Bark bark) {
		boolean ownerDogBark = false;		
		for(int index = 0; index < allowedBarks.length; index++) {
			if(bark.equals(allowedBarks[index])) {
				ownerDogBark = true;
			}
		}
		return ownerDogBark;
	}
	
	
}
