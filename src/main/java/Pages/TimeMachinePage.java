package Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import BasePage.BasePage;
import Utilities.ElementUtil;
import Utilities.JavaScriptUtil;

public class TimeMachinePage extends BasePage {
	WebDriver driver;
	ElementUtil elementUtil;
	JavaScriptUtil javaScriptUtil;

	By searchBox = By.cssSelector("input[type='text']");
	By searchButton = By.cssSelector("a.searchButton");
	By timeMachine = By.cssSelector("div.buttonContainer a.button");
	By timeMachineDate = By.cssSelector("button[data-pika-day='27']");
	By allHours = By.cssSelector("div.hours span.hour");
	By allTemp = By.cssSelector("div.temps span span");

	public TimeMachinePage(WebDriver driver) {
		this.driver = driver;
		elementUtil = new ElementUtil(driver);
	}

	public void userSelections() throws InterruptedException {

	elementUtil.doSendKeys(searchBox, "North Park Street, Reno, NV");
	elementUtil.doClick(searchButton);
	Thread.sleep(3000);
	JavaScriptUtil.scrollBy(driver);
	Thread.sleep(6000);
	elementUtil.doClick(timeMachine);
	Thread.sleep(4000);
	elementUtil.doClick(timeMachineDate);
	
	}
	
	public int tempOfFourAm(){
	
		List<WebElement> hours = driver.findElements(allHours);
		List<WebElement> temps = driver.findElements(allTemp);
	
		List<String> ListHours = new ArrayList<String>();
		List<Integer> ListTemps = new ArrayList<Integer>();
	
	Integer temp4am = null;
	
	for (int i = 0; i < hours.size(); i++) {
		String h = hours.get(i).getText();
		if (!h.isEmpty()) {
			ListHours.add(h);
	}
	}
	
		for (int i = 0; i < temps.size(); i++) {
			Integer t = Integer.parseInt(temps.get(i).getText().substring(0,2)); 
			ListTemps.add(t);
	}
	
		System.out.println(ListHours);
		System.out.println(ListTemps);
	
		for (int i = 0; i < temps.size(); i++) {
			if (ListHours.get(i).equals("4am")) {
	System.out.println("Time is " + ListHours.get(i) + " and the temperature is " + ListTemps.get(i));
	temp4am= ListTemps.get(i);
		break;
	}
	}
	return temp4am;
	}
}