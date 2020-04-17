package Tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import BasePage.BasePage;
import Pages.TimeLineMainPage;
import Pages.TimeMachinePage;

public class TimeMachineTestPage {
	WebDriver driver;
	Properties prop;
	BasePage basePage;
	TimeLineMainPage timeLineMainPage;
	TimeMachinePage timeMachinePage;
	
	@BeforeMethod
	public void setUp() throws InterruptedException{
		basePage = new BasePage();
		prop = basePage.initialize_properties();
		driver = basePage.initialize_driver(prop);
		timeLineMainPage = new TimeLineMainPage(driver);
		timeMachinePage = new TimeMachinePage(driver);
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	@Test
	public void timeMachine() throws InterruptedException{
		timeMachinePage.userSelections();
		timeMachinePage.tempOfFourAm();
		
	}

	

	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	

}
