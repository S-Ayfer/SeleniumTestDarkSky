package Pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import BasePage.BasePage;
import Utilities.ElementUtil;

public class TimeZoneMainPage extends BasePage {
	
	WebDriver driver;
	ElementUtil elementUtil;
	
	By hours1 = By.cssSelector("div.hours span.hour");
	By searchBox = By.cssSelector("input[type='text']");
	By searchButton = By.cssSelector("a.searchButton");	
	public TimeZoneMainPage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}
	
	public void timeZone() throws InterruptedException{
		elementUtil.doSendKeys(searchBox, "North Park Street, Reno, NV");
		elementUtil.doClick(searchButton);
		Thread.sleep(4000);
		
		List<WebElement> hours = driver.findElements(hours1);
		List<String> ListHours = new ArrayList<String>();
		
		for (int i = 0; i < hours.size(); i++) {
			String h = hours.get(i).getText();
			if (!h.isEmpty()) {
			ListHours.add(h);
	}
	}
		System.out.println(ListHours);
		String secondTime = ListHours.get(1);
		String  timeCity = secondTime.substring(0, secondTime.length() - 2); // Time without am or pm
		int timeCityint = Integer.parseInt(timeCity); 
		
		if (timeCityint == 1) {
			if (secondTime.contains("am")) {
			System.out.println("DARKSKY current time is --> 11am");
			}
			else {
			System.out.println("DARKSKY current time is --> 11pm");
			}
			}
			else if (timeCityint == 2) {
			if (secondTime.contains("am")) {
			System.out.println("DARKSKY  time is --> 12am");
			}
			else {
			System.out.println("DARKSKY  time is --> 12pm");
			}
			}
			else if (timeCityint == 12) {
			if (secondTime.contains("am")) {
			System.out.println("DARKSKY  time is --> 10pm");
			}
			else {
			System.out.println("DARKSKY  time is --> 10am");
			}
			}
			else {
			System.out.println("DARKSKY  time is --> " + (timeCityint - 2) + secondTime.substring(secondTime.length()-2));
			}

			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("haa");
			String finalDate = sdf.format(cal.getTime());
			System.out.println("RENO time is --> " + finalDate.toLowerCase());
		
	}
}
