package com.w2a.testcases;

import org.openqa.selenium.By;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.w2a.base.TestBase;

public class BangkManagerLoginTest extends TestBase {


	@Test
	public void loginAsBankManager() throws InterruptedException {
		//System.setProperty("org.uncommons.reportng.escape-output", "value"); //adds link to the screenshot in html report. Moved to the CustomListeners class.
		log.info("Inside LoginTest");
		driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();
		Thread.sleep(3000);
		log.info("LoginTest successfully executed");	
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn"))), "Login not successful");
		softAssert.assertAll();
		Reporter.log("LoginTest successfully executed");
		//use the three codes below to get the screenshot but it's better used in the Listner class.
		//-------------------------------------
		//Reporter.log("<a target =\" _blank\" href = \"C:\\Users\\posta\\Documents\\QA\\Tetra Project\\images\\issues.png\">Screenshot</a>");
		//Reporter.log("<br />");
		//Reporter.log("<a target =\" _blank\" href = \"C:\\Users\\posta\\Documents\\QA\\Tetra Project\\images\\issues.png\"><img src =\"C:\\Users\\posta\\Documents\\QA\\Tetra Project\\images\\issues.png\" height =\"100\" width = \"150\"></img></a>");
	}

}
