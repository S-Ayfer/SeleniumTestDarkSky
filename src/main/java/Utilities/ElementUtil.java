package Utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import Utilities.JavaScriptUtil;

import BasePage.BasePage;

public class ElementUtil extends BasePage {

	WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	public WebElement getElement(By locator) {
		waitForElementPresent(locator);

		WebElement element = null;
		try {
			element = driver.findElement(locator);
			if (flash.equalsIgnoreCase("yes")) {
				JavaScriptUtil.flash(element, driver);
			}
		} catch (Exception e) {
			System.out.println("Some exception occured while creating webelement " + locator);
		}
		return element;
	}

	public void waitForElementPresent(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
	}

	public void doClick(By locator) {
		try {
			getElement(locator).click();
		} catch (Exception e) {
			System.out.println("Some exception occured while click on  webelement " + locator);
		}
	}

	public void doSendKeys(By locator, String value) {
		try {
			getElement(locator).clear();
			getElement(locator).sendKeys(value);
		} catch (Exception e) {
			System.out.println("Some exception occured while sending to  webelement " + locator);
		}
	}

	public String doGetText(By locator) {
		String text = null;
		try {
			text = getElement(locator).getText();
		} catch (Exception e) {
			System.out.println("Some exception occured while sending to  webelement " + locator);
		}
		return text;
	}

	public String waitForGetPageTitle(String title) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}

	public String waitGetText(String title) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.titleContains(title));
		return driver.getTitle();
	}

	public boolean isElementDisplayed(By locator) {
		try {
			return getElement(locator).isDisplayed();
		} catch (Exception e) {
			System.out.println("Some exception occured while checking webelement displayed " + locator);
			return false;
		}
	}

	public void moveToElement(WebDriver driver, By locator) {
		waitForElementPresent(locator);
		try {
			WebElement target = driver.findElement(locator);
			Actions actions = new Actions(driver);
			actions.moveToElement(target).build().perform();
		} catch (Exception e) {
			System.out.println("Some exception occured while move to element " + locator);
		}
	}

	public void selectDropDownValueByValue(By locator, String value) {
		WebElement element = getElement(locator);
		Select select = new Select(element);
		select.selectByValue(value);
	}

	public String selectDropDownValueByIndex(WebDriver driver, By locator, String value) {
		WebElement element = getElement(locator);
		Select select = new Select(element);
		select.selectByValue(value);
		WebElement option = select.getFirstSelectedOption();
		String SelectedText = option.getText();
		return SelectedText;
	}

	public List<String> printResults(By locator1, By locator2) {
		
		List<WebElement> hotelTitleText = driver.findElements(locator1);
		List<WebElement> distanceText = driver.findElements(locator2);
		List<String> milesHotel = new ArrayList<String>();
		int j = 1;
		for (int i = 0; i < distanceText.size(); i++) {
			String fullDistance = distanceText.get(i).getText();
			String hotelTitle = hotelTitleText.get(i).getText();
			String distance = fullDistance.split(" ")[0].trim();
			double dist = Double.parseDouble(distance);
			if (dist < 10 ) {
				milesHotel.add(j + ". " + dist + " Miles " + hotelTitle);
				j++;
			}
		}	
		return milesHotel;
	}

	public List<Integer> getAllTemps(By locator){
		List<WebElement> allTemps = driver.findElements(locator);
		List<Integer> tempDegree = new ArrayList<Integer>(); 
		for (int i =0; i<allTemps.size(); i++){
			String temp = allTemps.get(i).getText();
			int tempDegreeInt = Integer.parseInt(temp.substring(0,2));
			tempDegree.add(tempDegreeInt);
		}
		return tempDegree;
	}
//public static int getMax(By temps){
//		int maxValue = temps[0];
//		for(int i=1; i<temps.length;i++){
//			if(temps[i]>maxValue){
//				maxValue = temps[i];
//			}
//		}
//		return maxValue;
//	}
	public static int getMin(int[] inputArray){
		int minValue = inputArray[0];
		for(int i=1; i<inputArray.length;i++){
			if(inputArray[i]<minValue){
				minValue = inputArray[i];
			}
		}
		return minValue;
	}
}
