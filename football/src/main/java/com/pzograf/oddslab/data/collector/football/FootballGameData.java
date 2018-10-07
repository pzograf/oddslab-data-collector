package com.pzograf.oddslab.data.collector.football;

import java.util.ArrayList;
import java.util.Collection;

import com.pzograf.oddslab.data.collector.api.GameData;
import com.pzograf.oddslab.data.collector.api.GameEvent;
import com.pzograf.oddslab.data.collector.api.GameStatistic;

public class FootballGameData implements GameData{

	private String league;
	
	private int matchWeek;
	
	private String homeTeam;
	
	private String awayTeam;
	
	private String result;
	
	private Collection<GameEvent> events = new ArrayList<>();
	
	private Collection<GameStatistic> stats = new ArrayList<>();
		
	@Override
	public String league() {
		return league;
	}

	@Override
	public int matchWeek() {
		return matchWeek;
	}

	@Override
	public String homeTeam() {
		return homeTeam;
	}

	@Override
	public String awayTeam() {
		return awayTeam;
	}

	@Override
	public String result() {
		return result;
	}

	@Override
	public Collection<GameEvent> events() {
		return events;
	}

	@Override
	public Collection<GameStatistic> stats() {
		return stats;
	}
	
	public void league(String league) {
		this.league = league;
	}
	
	public void matchWeek(int matchWeek) {
		this.matchWeek = matchWeek;
	}

	public void homeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public void awayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}

	public void result(String result) {
		this.result = result;
	}

	public void addEvent(GameEvent event) {
		this.events.add(event);
	}

	public void addStat(GameStatistic stat) {
		this.stats.add(stat);
	}

	@Override
	public String toString() {
		return "FootballGameData [league=" + league + ", matchWeek=" + matchWeek + ", homeTeam=" + homeTeam
				+ ", awayTeam=" + awayTeam + ", result=" + result + ", events=" + events + ", stats=" + stats + "]";
	}		
}
