package com.pzograf.oddslab.data.collector.football;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.pzograf.oddslab.data.collector.api.GameData;
import com.pzograf.oddslab.data.collector.api.GameDataSource;
import com.pzograf.oddslab.data.collector.api.GameEvent;
import com.pzograf.oddslab.data.collector.api.GameStatistic;

public class PremierLeagueOfficialReader implements GameDataSource {

	private final String domain;

	public PremierLeagueOfficialReader() {
		domain = "https://www.premierleague.com";
	}

	public GameData read(String gameId) throws Exception {

		FootballGameData outcome = new FootballGameData();
		outcome.league("Premier League");

		WebDriver driver = new ChromeDriver();
		driver.get(domain.concat("/match/").concat(gameId));
		
		Thread.sleep(2000); // Let the user actually see something!

		updateMatchWeek(driver,outcome);
		updateHomeTeam(driver,outcome);
		updateAwayTeam(driver,outcome);
		updateResult(driver,outcome);
				
		updateEvents(driver,outcome);

		scrollToTop(driver);

		navigateToStatsTab(driver);
		
		Thread.sleep(2000); // Let the user actually see something!
		updateStats(driver, outcome);
		
		
		driver.quit();

		return outcome;
	}
	
	private void updateMatchWeek(WebDriver driver, FootballGameData outcome) {
		WebElement matchWeekContainer = driver.findElement(By.xpath("//*[starts-with(text(), 'Matchweek')]"));
		String matchWeekText = matchWeekContainer.getText().replaceAll("Matchweek ", "").trim();
		outcome.matchWeek(Integer.parseInt(matchWeekText));
	}
	
	private void updateResult(WebDriver driver, FootballGameData outcome) {
		WebElement scoreContainer = driver.findElement(By.cssSelector(".score.fullTime"));
		outcome.result(scoreContainer.getText());
	}
	
	private void updateHomeTeam(WebDriver driver, FootballGameData outcome) {
		WebElement homeTeamContainer = driver.findElement(By.cssSelector(".team.home"));
		outcome.homeTeam(homeTeamContainer.getText());
	}

	private void updateAwayTeam(WebDriver driver, FootballGameData outcome) {
		WebElement awayTeamContainer = driver.findElement(By.cssSelector(".team.away"));
		outcome.awayTeam(awayTeamContainer.getText());
	}
	
	private void updateEvents(WebDriver driver, FootballGameData outcome) throws InterruptedException {
				
		scrollPageDownGradually(driver, ".liveMatchContainer");
		
        List<WebElement> rows = driver.findElements(By.cssSelector("ul.commentaryContainer li"));

        rows.stream()
	    	.map(row -> eventFromListElement(row))
	    	.forEach(event -> outcome.addEvent(event));
	}
	
	private void navigateToStatsTab(WebDriver driver) {
		WebElement statsTab = driver.findElement(By.cssSelector("li[data-tab-index=\"2\"]"));
		statsTab.click();
	}
	
	private void updateStats(WebDriver driver, FootballGameData outcome) {
	
        List<WebElement> rows = driver.findElements(By.cssSelector(".matchCentreStatsContainer tr"));
                
        rows.stream()
        	.map(row -> statisticFromTableRow(row))
	    	.forEach(stat -> outcome.addStat(stat));
	}
	
	private GameEvent eventFromListElement(WebElement row) {		

		WebElement eventTimeContainer = row.findElement(By.cssSelector(".cardMeta"));
		
		WebElement eventHeaderContainer = row.findElement(By.cssSelector(".cardContent .innerContent h6"));
		WebElement eventTextContainer = row.findElement(By.cssSelector(".cardContent .innerContent p"));
		
		GameEvent event = new GameEvent();
		event.time(eventTimeContainer.getText());
		event.type(eventHeaderContainer.getText());
		event.data(eventTextContainer.getText());
		
		return event;
	}
	
	private GameStatistic statisticFromTableRow(WebElement row) {		
		
		List<WebElement> cells = row.findElements(By.cssSelector("td p"));
		
		String sName = cells.get(1).getText();
		String sValueHome = cells.get(0).getText();
		String sValueAway = cells.get(2).getText();

		GameStatistic stat = new GameStatistic();
		stat.name(sName);
		stat.valueHome(sValueHome);
		stat.valueAway(sValueAway);

		return stat;
	}

	public void scrollPageDownGradually(WebDriver driver, String cssSelector) throws InterruptedException {
		
		WebElement elementToScroll = driver.findElement(By.cssSelector(cssSelector));
		int scrollStart = elementToScroll.getLocation().y;
		int scrollEnd = elementToScroll.getLocation().y + elementToScroll.getSize().height;
		
		JavascriptExecutor js = (JavascriptExecutor)driver;
				
		int scrollStep = 200;
		int scrollPoint = scrollStart+scrollStep;
		boolean elementEnded = false;
		while (!elementEnded) {
			
			js.executeScript("window.scrollTo(0,"+scrollPoint+")");
			Thread.sleep(200); 
			
			scrollPoint = scrollPoint+scrollStep;
			
			elementToScroll = driver.findElement(By.cssSelector(cssSelector));
			scrollEnd = elementToScroll.getLocation().y + elementToScroll.getSize().height;
			
			elementEnded = scrollPoint>scrollEnd;
		}
	}
	
	public void scrollToTop(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("window.scrollTo(0,0)");
	}
}