package Tests;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.How;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.common.base.Converter;

import BasePage.BasePage;
import Pages.TimeLineMainPage;

public class TimeLineMainTestPage {
	WebDriver driver;
	Properties prop;
	BasePage basePage;
	TimeLineMainPage timeLineMainPage;
	
	@BeforeMethod
	public void setUp(){
		basePage = new BasePage();
		prop = basePage.initialize_properties();
		driver = basePage.initialize_driver(prop);
		timeLineMainPage = new TimeLineMainPage(driver);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	
	}
	@Test(priority=1)
	public void timeLine() throws InterruptedException{
		timeLineMainPage.TimeLineSection();
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
	

}
