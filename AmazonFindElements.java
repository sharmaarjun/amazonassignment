package com.ecommerce.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;

import junit.framework.Assert;

class AmazonFindElements {
	WebDriver driver;
	
	@BeforeEach
	void setUp() throws Exception {
		// 1. set selenium properties
				System.setProperty("webdriver.gecko.driver", 
						"C:\\Users\\Arjun Sharma\\eclipse-workspace\\phase1-selenium-scripting\\driver\\geckodriver.exe");
				
				// 2. Instantiating web driver
				driver = new FirefoxDriver();
	}

	@AfterEach
	void tearDown() throws Exception {
		driver.close();
	}

	@Test
	public void findElement() {
		String siteUrl = "https://www.amazon.in/";
		driver.get(siteUrl);
		
		// Get the web element by id
		WebElement searchBox1 = driver.findElement(By.id("twotabsearchtextbox"));
		System.out.println("Search tag name "+ searchBox1.getTagName());
		Assert.assertEquals("input", searchBox1.getTagName());
	
	}
	
	@Test
	public void searchPhone() {
		String siteUrl = "https://www.amazon.in/";
		driver.get(siteUrl);
		WebElement searchBox1 = driver.findElement(By.id("twotabsearchtextbox"));
		searchBox1.sendKeys("iPhone 12");
		driver.findElement(By.id("nav-search-submit-button")).click();
	}
	
	@Test
	public void getDetailsOfPhone() {
		String siteUrl = "https://www.amazon.in/";
		driver.get(siteUrl);
		WebElement searchBox1 = driver.findElement(By.id("twotabsearchtextbox"));
		searchBox1.sendKeys("iPhone 12");
		driver.findElement(By.id("nav-search-submit-button")).click();
		WebElement details = driver.findElement(By.cssSelector("#search > div.s-desktop-width-max.s-desktop-content.sg-row > div.sg-col-16-of-20.sg-col.sg-col-8-of-12.sg-col-12-of-16 > div > span:nth-child(4) > div.s-main-slot.s-result-list.s-search-results.sg-row > div:nth-child(2) > div > span > div > div > div > div > div:nth-child(2) > div.sg-col-4-of-12.sg-col-8-of-16.sg-col-12-of-20.sg-col > div > div:nth-child(1) > div > div > div:nth-child(1) > h2 > a > span"));
		WebElement price = driver.findElement(By.className("a-price-whole"));
		System.out.println("Price of the product is :: "+price.getText());
		details.click();
	}
	@Test
	public void addToCart() throws InterruptedException, IOException {
		String siteUrl = "https://www.amazon.in/";
		driver.get(siteUrl);
		WebElement searchBox1 = driver.findElement(By.id("twotabsearchtextbox"));
		searchBox1.sendKeys("iPhone 12");
		driver.findElement(By.id("nav-search-submit-button")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[2]/div/span[3]/div[2]/div[2]/div/span/div/div/div/div/div[2]/div[2]/div/div[1]/div/div/div[1]/h2/a/span")).click();
		Thread.sleep(2000);
		//get window handle
		String mainWindow = driver.getWindowHandle();
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newTab.get(1));
		driver.findElement(By.xpath("//*[@id=\"add-to-cart-button\"]")).click();
		Thread.sleep(2000);
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(src, new File("C:\\Users\\Arjun Sharma\\eclipse-workspace\\phase1-selenium-scripting\\src\\com\\ecommerce\\tests\\Screenshots\\addtocart.png"));
		
		driver.close();
		driver.switchTo().window(newTab.get(0));
		
	}
	
	@Test
	public void viewCart() {
		String siteUrl = "https://www.amazon.in/";
		driver.get(siteUrl);
		WebElement viewCart = driver.findElement(By.id("nav-cart-count"));
		viewCart.click();
	}
	
	@Test
	public void addAddress() throws InterruptedException, IOException {
		String siteUrl = "https://www.amazon.in/";
		driver.get(siteUrl);
		Thread.sleep(5000);
		driver.findElement(By.xpath("//*[@id=\"nav-global-location-popover-link\"]")).click();
		Thread.sleep(5000);
		String mainWindow = driver.getWindowHandle();
		
		// to handle all new open windows
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> itrs = windows.iterator();	
		while (itrs.hasNext()) {
			String childWindow = itrs.next();
			// switch to child window
			driver.switchTo().window(childWindow);
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("#GLUXZipUpdateInput")).sendKeys("134113");
			driver.findElement(By.cssSelector("#GLUXZipUpdate > span > input")).click();
			Thread.sleep(2000);
			TakesScreenshot ts = (TakesScreenshot) driver;
			File src = ts.getScreenshotAs(OutputType.FILE);
			FileHandler.copy(src, new File("C:\\Users\\Arjun Sharma\\eclipse-workspace\\phase1-selenium-scripting\\src\\com\\ecommerce\\tests\\Screenshots\\address.png"));
			
			driver.close();
		}
		
	}
	
	@Test
	public void sideMenu() {
		String siteUrl = "https://www.amazon.in/";
		driver.get(siteUrl);
		driver.findElement(By.id("nav-hamburger-menu")).click();
		driver.findElement(By.linkText("Best Sellers")).click();
		//driver.findElement(By.xpath("//*[@id=\"hmenu-content\"]/ul[1]/li[2]/a")).click();
	}
	@Test
	public void signIn() throws IOException {
		String siteUrl = "https://www.amazon.in/";
		driver.get(siteUrl);
		driver.findElement(By.id("nav-link-accountList")).click();
		driver.findElement(By.id("ap_email")).sendKeys("test@yopmail.com");
		driver.findElement(By.id("continue")).click();
		driver.findElement(By.id("ap_password")).sendKeys("qwerty");
		driver.findElement(By.id("signInSubmit")).click();
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		FileHandler.copy(src, new File("C:\\Users\\Arjun Sharma\\eclipse-workspace\\phase1-selenium-scripting\\src\\com\\ecommerce\\tests\\Screenshots\\signin.png"));
		
	}

}
