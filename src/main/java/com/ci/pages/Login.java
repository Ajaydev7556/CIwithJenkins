package com.ci.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Login {
	
	WebDriver driver;
	
	public Login(WebDriver driver)
	{
		this.driver = driver;
	}
	
	By username_field =  By.xpath("(//input[@type='text'])[1]");
	By password_field = By.xpath("//input[@type='password']");
	By login_button = By.xpath("//button[normalize-space()='Login']");
	
	public void enterUsername(String username)
	{
		driver.findElement(username_field).sendKeys(username);
	}
	
	public void enterPassword(String password)
	{
		driver.findElement(password_field).sendKeys(password);
	}
	
	public void loginButtonClick()
	{
		driver.findElement(login_button).click();
	}

}
