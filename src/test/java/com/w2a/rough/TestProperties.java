package com.w2a.rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
	
	public static void main(String[] args) throws IOException {
		
		System.out.println(System.getProperty("user.dir"));		
		Properties config = new Properties();
		//in case of file location only System.getProperty("user.dir") will change.
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
		config.load(fis); //load configuration file.
		Properties OR = new Properties();
		fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
		OR.load(fis); //load OR properties file.
		
		System.out.println(config.getProperty("browser")); //gets the browser type from the config file.
		//driver.findelement(By.scsSelector(Or.getProperty("bmlBtn")- this is how the bmlBtn will be used.
		System.out.println(OR.getProperty("bmlBtn")); //gets the css value of bmlBtn from OR properties.
		
	}

}
