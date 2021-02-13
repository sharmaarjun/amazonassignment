package com.ecommerce.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AmazonHomePageTest {

	public static void main(String[] args) throws InterruptedException {
		final String siteUrl = "https://www.amazon.in/";
		
		// 1. set selenium properties
		System.setProperty("webdriver.gecko.driver", 
				"C:\\Users\\Arjun Sharma\\eclipse-workspace\\phase1-selenium-scripting\\driver\\geckodriver.exe");
		
		// 2. Instantiating web driver
		WebDriver driver = new FirefoxDriver();
		driver.get(siteUrl);
		
		// 3. perform tests
		if(driver.getTitle().equals("Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in")) {
			System.out.println("Test Passed");
		}else {
			System.out.println("Test failed");
		}
		Thread.sleep(50000);
		driver.close();
	}

}
