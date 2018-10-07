package com.pzograf.oddslab.data.collector.api;

import java.util.Collection;

public interface GameData {
		
	String league();
	
	int matchWeek();
	
	String homeTeam();
	
	String awayTeam();
	
	String result();
	
	Collection<GameEvent> events();
	
	Collection<GameStatistic> stats();
		
}