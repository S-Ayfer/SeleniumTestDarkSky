package Pages;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import BasePage.BasePage;
import Utilities.ElementUtil;
import Utilities.JavaScriptUtil;

public class TimeLineMainPage extends BasePage {
	WebDriver driver;
	ElementUtil elementUtil;
	JavaScriptUtil javaScriptUtil;
	
	
	By searchBox = By.cssSelector("input[type='text']");
	By searchButton = By.cssSelector("a.searchButton");
	By todayBtn = By.xpath("//span[contains(text(),'Today')]");
	By temps = By.cssSelector("div.dayDetails.revealed div.temps span span");


	public TimeLineMainPage(WebDriver driver){
	this.driver = driver;
	elementUtil = new ElementUtil(driver);
	}

	public void TimeLineSection() throws InterruptedException{
	elementUtil.doSendKeys(searchBox, "North Park Street, Reno, NV");
	elementUtil.doClick(searchButton);
	Thread.sleep(500);
	JavaScriptUtil.scrollIntoView(elementUtil.getElement(todayBtn), driver);
	Thread.sleep(500);
	elementUtil.doClick(todayBtn);
	Thread.sleep(500); 
	List<Integer> listOfTemps = getAllTemps(temps);
	Collections.sort(listOfTemps);
	int maxTemp = listOfTemps.get(listOfTemps.size()-1);
	int minTemp = listOfTemps.get(0);
	System.out.println("Temp Of Today --> "+listOfTemps);
	System.out.println("Minimum Temp --> "+minTemp);
	System.out.println("Maximum Temp --> "+maxTemp);

	}
	public List<Integer> getAllTemps(By locator) {
		List<WebElement> allTemps = driver.findElements(locator);
		List<Integer> tempDegree = new ArrayList<Integer>(); 
		for (int i =0; i<allTemps.size(); i++){
		String temp = allTemps.get(i).getText();
		int tempDegreeInt = Integer.parseInt(temp.substring(0,2));
		tempDegree.add(tempDegreeInt);
		}
		return tempDegree;
	}		
}
	



