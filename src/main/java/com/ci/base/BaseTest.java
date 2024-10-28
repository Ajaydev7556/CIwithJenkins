package com.ci.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
	
	public WebDriver driver;
	
	@BeforeSuite
	public void runSetup()
	{
		driver = new ChromeDriver();
		driver.get("https://www.google.com");
	}

}
