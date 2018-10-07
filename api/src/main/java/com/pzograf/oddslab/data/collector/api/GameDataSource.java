package com.pzograf.oddslab.data.collector.api;

public interface GameDataSource {
	
	GameData read(String gameId) throws Exception;
}
