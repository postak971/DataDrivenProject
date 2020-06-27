package com.w2a.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.asserts.SoftAssert;

import com.w2a.utilities.ExcelReader;
import com.w2a.utilities.TestLogs;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	
	
	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	//public static Logger log = Logger.getLogger("devpinoyLogger");
	public static Logger log = Logger.getLogger(TestLogs.class.getName());
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public static SoftAssert softAssert;
	
	
	
	@BeforeSuite
	public void setUp() {
		if(driver == null) {
			//use the below 3 lines if you want to generate the new log for each execution.
			//Date d = new Date();			
			//System.out.println(d.toString().replace(":", "_").replace(" ", "_"));
			//System.setProperty("current.date", d.toString().replace(":", "_").replace(" ", "_"));
			
			PropertyConfigurator.configure("./src/test/resources/properties/log4j.properties");
			//PropertyConfigurator.configure(System.getProperty("user.dir")+ "\\src\\test\\java\\log4j.properties");
			
			//load configuration file.
			
			try {
				fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.info("Config file loaded!!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			//load OR properties file.
			try {
				fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.info("OR file loaded!!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			//load browser
			if(config.getProperty("browser").equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				log.info("Firefox launched!!!");
				
			}
			else if (config.getProperty("browser").equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				log.info("Chrome launched!!!");
			
			}
			else if (config.getProperty("browser").equals("ie")) {
				WebDriverManager.iedriver().setup();
				driver = new InternetExplorerDriver();
				log.info("IE launched!!!");
				}
			else if (config.getProperty("browser").equals("edge")) {
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
				log.info("Edge launched");
			}
			//load the application under test.
			driver.get(config.getProperty("testsiteurl"));
			log.info("Navigated to: " + config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
				//implicit wait takes only numeric values where as the config file lists it as string. So, we need it to parse it to the 
			//integer. Integer.parseInt(config.getProperty("implicit.wait")
			wait = new WebDriverWait(driver, Integer.parseInt(config.getProperty("explicit.wait")));
			
		}	
	}
	public boolean isElementPresent(By by) {
		try{
			driver.findElement(by);
			return true;
		}
		catch(NoSuchElementException e) {
			
		return false;
		}
		
	}
	@AfterSuite
	public void tearDown() {
		if(driver != null) {
		driver.quit();
		}
		log.info("Test execution completed!!!");
	}
	

}
