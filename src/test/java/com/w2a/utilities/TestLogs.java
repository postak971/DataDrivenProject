package com.w2a.utilities;

import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class TestLogs {
	
	public static Logger log = Logger.getLogger(TestLogs.class.getName());
	
	public static void main(String[] args) {
		//to generate a new logfile on each execution
       //Date d = new Date();
		
		//System.out.println(d.toString().replace(":", "_").replace(" ", "_"));
		//System.setProperty("current.date", d.toString().replace(":", "_").replace(" ", "_"));
	PropertyConfigurator.configure("./src/test/resources/properties/log4j.properties");
	
	log.info("This is the information log");
	log.error("Here are the error logs to be printed");
	}

}
