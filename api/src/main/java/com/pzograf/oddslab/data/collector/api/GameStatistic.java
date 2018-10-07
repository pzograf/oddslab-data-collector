package com.pzograf.oddslab.data.collector.api;

public class GameStatistic {
	
	private String name;
	
	private String valueHome;
	
	private String valueAway;

	public String name() {
		return name;
	}

	public void name(String name) {
		this.name = name;
	}

	public String valueHome() {
		return valueHome;
	}

	public void valueHome(String valueHome) {
		this.valueHome = valueHome;
	}
	
	public String valueAway() {
		return valueAway;
	}

	public void valueAway(String valueAway) {
		this.valueAway = valueAway;
	}
}