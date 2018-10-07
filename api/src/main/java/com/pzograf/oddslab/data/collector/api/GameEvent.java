package com.pzograf.oddslab.data.collector.api;

public class GameEvent {
	
	private String time;
	
	private String type;
	
	private String data;

	public String time() {
		return time;
	}

	public void time(String time) {
		this.time = time;
	}

	public String type() {
		return type;
	}

	public void type(String type) {
		this.type = type;
	}

	public String data() {
		return data;
	}

	public void data(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "GameEvent [time=" + time + ", type=" + type + ", data=" + data + "]";
	}
}
