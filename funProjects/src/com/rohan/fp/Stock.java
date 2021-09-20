package com.rohan.fp;

public class Stock {
	private double stockPrice;
	private String name;
	private String code;
	
	public Stock(String name, String code, double stockPrice) {
		this.stockPrice = stockPrice;
		this.name = name;
		this.code = code;
	}
	
	public double getQuote() {
		return stockPrice;
	}
	
	public String getName() {
		return name;
	}
	
	public String getCode() {
		return code;
	}
	
	
}
