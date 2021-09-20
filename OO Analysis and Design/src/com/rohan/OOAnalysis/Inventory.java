package com.rohan.OOAnalysis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Inventory {
	private List players;
	
	public Inventory() {
		players = new LinkedList();
	}
	
	public void addPlayer(String name, Position position, Team team, double rating) {
		Player player = new Player(name,position,team,rating);
		players.add(player);
	}
	
	public Player getplayer(String name) {
		for(int i = 0; i < players.size(); i++) {
			Player temp = (Player)players.get(i);
			if(temp.getName().equals(name)) {
				return temp;
			}
		}
		return null;
	}
	
	public ArrayList<Player> matchPlayer(Player ideal){
		ArrayList<Player> match = new ArrayList<Player>();
		int x = (int)ideal.getRating();
		for(int index = 0; index < players.size(); index++) {
			Player temp = (Player)players.get(index);
			int y = (int)temp.getRating();
			boolean added = false;
			if(temp.getName().equals(ideal.getName()) || temp.getPosition().equals(ideal.getPosition()) || 
					temp.getTeam().equals(ideal.getTeam())){
				match.add(temp);
				added = true;
			}
			int[] range = {x-2,x-1,x,x+1,x+2,x+3,x+4,x+5,x+6};
			boolean a = false;
			for(int i = 0; i < 5; i++) {
				if(y == range[i]) {
					a = true;
				}
			}
			if(a && !added) {
				match.add(temp);
			}
			
		}
		return match;
	}
	
}


enum Position{
	QB,HB,WR,TE;
	public String toString() {
		switch(this) {
		case QB:
			return "qb";
		case HB:
			return "hb";
		case WR:
			return "wr";
		case TE:
			return "te";
		}
		return null;
	}
}

enum Team{
	BALTIMORE_RAVENS,BUFFALO_BILLS,CLEVELAND_BROWNS,GREEN_BAY_PACKERS,KANSAS_CITY_CHIEFS,PITTSBURGH_STEELERS,
	SEATTLE_SEAHAWKS,TAMPA_BAY_BUCCANEERS;
	public String toString() {
		switch(this){
		case BALTIMORE_RAVENS:
			return "baltimore ravens";
		case BUFFALO_BILLS:
			return "buffalo bills";
		case CLEVELAND_BROWNS:
			return "cleveland browns";
		case GREEN_BAY_PACKERS:
			return "green bay packers";
		case KANSAS_CITY_CHIEFS:
			return "kansas city chiefs";
		case PITTSBURGH_STEELERS:
			return "pittsburgh steelers";
		case SEATTLE_SEAHAWKS:
			return "seattle seahawks";
		case TAMPA_BAY_BUCCANEERS:
			return "tampa bay buccaneers";
		}
		return null;
	}
}











