package BasePage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	public WebDriver driver;
	public Properties prop;
	public static String flash;
	
	/**
	 * This mthod is used to initialize the driver on basis of given browser
	 * @return this methods returns webdriver instance
	 * @throws InterruptedException 
	 */
	public WebDriver initialize_driver(Properties prop){
		
      //String browser = "chrome";
	 String browser=  prop.getProperty("browser");	
	 String headless = prop.getProperty("headless");
	 flash = prop.getProperty("elementflash");
	 
      if(browser.equalsIgnoreCase("chrome")){
    	  //WebDriverManager.chromedriver().setup();
    	  System.setProperty("webdriver.chrome.driver","/Users/ayferyildiz/Downloads/chromedriver");
    	     if(headless.equalsIgnoreCase("yes")){
    	    	 ChromeOptions co = new ChromeOptions();
    	    	 co.addArguments("--headless"); 
    	    	 driver = new ChromeDriver(co);
    	     }else{
    	    	 driver = new ChromeDriver(); 
    	     }
      }else if(browser.equalsIgnoreCase("firefox")){
    	  WebDriverManager.firefoxdriver().setup();
    	  if(headless.equalsIgnoreCase("yes")){
 	    	 FirefoxOptions fo = new FirefoxOptions();
 	    	 fo.addArguments("--headless"); 
 	    	 driver = new FirefoxDriver(fo);
 	     }else{
 	    	driver = new FirefoxDriver();
 	     }
      }
      driver.manage().window().fullscreen();
      driver.manage().deleteAllCookies();
      driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
      driver.get(prop.getProperty("url"));
      
      return driver;
     	
	}
	
	/** 
	 * This method is used to define the properties
	 * @return this method returns properties prop reference
	 */
	public Properties initialize_properties(){
		
	 prop = new Properties();
	 
	 try{
	 FileInputStream ip = new FileInputStream(System.getProperty("user.dir")
	 		+ "/src/main/java/config/config.properties");
	    prop.load(ip);
	 }catch (FileNotFoundException e) {
		e.printStackTrace();
	 } catch (IOException e) {
		e.printStackTrace();
	 }
		
	 return prop;
   }
   
	public void quitBrowser(){
		try{
		driver.quit();
		}catch(Exception e){
			System.out.println("Some exception occured while quitting the browser");
		}
	}
	
	public void closeBrowser(){
		try{
		driver.close();
		}catch(Exception e){
			System.out.println("some exception occured while closing the browser");
		}
	}	

}
