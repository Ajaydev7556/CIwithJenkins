package com.ci.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
	
	public WebDriver driver;
	public DevTools devtools;
	
	@BeforeSuite
	public void runSetup()
	{
		driver = new ChromeDriver();
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		
		driver.get("https://dev-app.mixbloom.com/login");
	}

}
