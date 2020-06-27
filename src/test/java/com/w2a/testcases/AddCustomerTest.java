package com.w2a.testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.w2a.base.TestBase;

public class AddCustomerTest extends TestBase {
	
	@Test(dataProvider = "getData")
	public void AddCustomer(String firstName, String lastName, String postCode, String alerttext) throws InterruptedException {
		driver.findElement(By.cssSelector(OR.getProperty("addCustBtn"))).click();
		driver.findElement(By.cssSelector(OR.getProperty("firstName"))).sendKeys(firstName);
		driver.findElement(By.cssSelector(OR.getProperty("lastName"))).sendKeys(lastName);
		driver.findElement(By.cssSelector(OR.getProperty("postCode"))).sendKeys(postCode);
		driver.findElement(By.cssSelector(OR.getProperty("addBtn"))).click();
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		softAssert = new SoftAssert();
		softAssert.assertTrue(alert.getText().contains(alerttext));
		softAssert.assertAll();
		alert.accept();
		//Thread.sleep(2000);
		//driver.switchTo().alert().accept();
		log.info("Customer " + firstName + " " + lastName + " successfully added");
		//Thread.sleep(2000);
		Assert.fail();
		
	}
	@DataProvider
	public Object[][] getData(){
		
		String sheetName = "AddCustomerTest";
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);

		Object[][] data = new Object[rows-1][cols];//rows-1 is because we are leaving the header row.

		for(int rowNum = 2; rowNum <= rows; rowNum++) {//rowNum = 2, because row 1 is the header row.
			for(int colNum = 0; colNum < cols; colNum++) {
				data[rowNum-2][colNum] = excel.getCellData(sheetName, colNum, rowNum); //rowNum-2 is taken to obtain 0

			}
		}

		return data;
	}

}
