package com.ci.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserDashboard {
	
	
	WebDriver driver;
	
	public UserDashboard(WebDriver driver)
	{
		this.driver = driver;
	}
	
	// Nav-bar after login
	
	By post_navbar_section = By.xpath("//div[@class='list']");
	
	public boolean postNavbarSectionDisplayed()
	{
		boolean isPostNavbarSectionDisplayed = driver.findElement(post_navbar_section).isDisplayed();
		return isPostNavbarSectionDisplayed;
	}
	
	// Create new post
	By create_post_button = By.xpath("//button[normalize-space()='Create Post']");
	By post_textarea = By.xpath("(//table[@class='table createSocialPostsTable'])[6]/tr/td/textarea");
	By upload_image_button = By.xpath("(//div[@class='form-group'])[23]/div/button");
	By web_address_button = By.xpath("//span[normalize-space()='Web Address']");
	By url_field = By.xpath("//input[@data-test='search-input-box']");
	By url_search_butotn = By.xpath("//button[@data-test='upload-from-link-btn']");
	By add_to_schedule_button = By.xpath("//button[normalize-space()='Add to Schedule']");
	
	public void createPostSection()
	{
		driver.findElement(create_post_button).click();
	}
	
	public boolean postTextAreaDisplayed()
	{
		boolean isPostTextAreaDisplayed = driver.findElement(post_textarea).isDisplayed();
		return isPostTextAreaDisplayed;
	}
	
	public void enterPostMessage(String enterPostMsg)
	{
		driver.findElement(post_textarea).sendKeys(enterPostMsg);
	}
	
	public void selectImage(String imageUrl) throws Exception
	{ 
		driver.findElement(upload_image_button).click();
		Thread.sleep(1500);
		driver.switchTo().frame(2);
		Thread.sleep(1500);
		driver.findElement(web_address_button).click();
		driver.findElement(url_field).sendKeys(imageUrl);
		driver.findElement(url_search_butotn).click();
		Thread.sleep(1500);
		driver.switchTo().defaultContent();
	}
	
	public void addToSchedule() throws Exception
	{
		WebElement webElement = driver.findElement(add_to_schedule_button);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", webElement);
		Thread.sleep(1000);
		webElement.click();		
	}
	
	// Edit Post
	
	By update_text_area = By.xpath("(//table[@class='table createSocialPostsTable'])[1]/tr/td/textarea");
	By update_button = By.xpath("//button[normalize-space()='Update']");
	
	public void editPost(String postOrginalId) throws Exception
	{
		
		By edit_post_locator = By.xpath("//div[@id='"+postOrginalId+"']/div[2]/div[2]/div[1]/div[4]/span");
		
		WebElement webElement = driver.findElement(edit_post_locator);
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", webElement);
		Thread.sleep(1000);
		webElement.click();	
	}
	
	public void updatePostContent(String postUpdatedContent)
	{
		driver.findElement(update_text_area).sendKeys(postUpdatedContent);
		driver.findElement(update_button).click();
	}
	
	
	

}
