package com.pzograf.oddslab.data.collector.football;

import com.pzograf.oddslab.data.collector.api.GameData;

public class Controller {

	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver", "D:\\Dev\\tools\\chromedriver.exe");

		PremierLeagueOfficialReader reader = new PremierLeagueOfficialReader();

		try {
			for (int i=38308; i<38338; i++) {
				System.out.println("Reading match with id = "+i);
				GameData stats = reader.read(i+"");
				System.out.println("Last event "+ stats.events().toArray()[stats.events().size()-1]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
