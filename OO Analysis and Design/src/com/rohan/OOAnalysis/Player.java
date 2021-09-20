package com.rohan.OOAnalysis;

public class Player {
	private String name;
	private Position position;
	private Team team;
	private double rating;
	
	public Player(String name, Position position, Team team, double rating) {
		this.name = name;
		this.team = team;
		this.position = position;
		this.rating = rating;
	}
	
	public String getName() {
		return name;
	}
	
	public Team getTeam() {
		return team;
	}
	
	public Position getPosition() {
		return position;
	}
	
	public double getRating() {
		return rating;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setTeam(Team team) {
		this.team = team;
	}
	
	public void setPosition(Position position) {
		this.position = position;
	}
	
	public void setRating(double rating) {
		this.rating = rating;
	}
	
	public String toString() {
		String str = name + " of " + team + " who plays at " + position;
		return str;
	}
	
}
