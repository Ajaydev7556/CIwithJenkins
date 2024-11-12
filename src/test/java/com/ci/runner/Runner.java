package com.ci.runner;

import static org.testng.Assert.assertTrue;

import java.util.Optional;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v129.network.Network;
import org.openqa.selenium.devtools.v129.network.model.RequestId;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.*;

import com.ci.base.BaseTest;
import com.ci.pages.UserDashboard;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ci.pages.Login;

public class Runner extends BaseTest{
	
	Login login;
	UserDashboard user_dashboard;

	
	@Test(priority = 1, description = "Verify the login functionality with valid credentials")
	public void login() throws Exception
	{
		login = new Login(driver);
		user_dashboard = new UserDashboard(driver);
		
		login.enterUsername("testlumino4@yopmail.com");
		login.enterPassword("Test@12345");
		login.loginButtonClick();
		
		Thread.sleep(2000);
		
		Assert.assertTrue(user_dashboard.postNavbarSectionDisplayed());
		Reporter.log("User Logged successfully");
	}

	
	@Test(priority = 2, description = "verify the functionality of create post feature")
 	public void createPost(ITestContext context) throws Exception
	{
		user_dashboard = new UserDashboard(driver);
		
		user_dashboard.createPostSection();
		
		Thread.sleep(1500);
		
		// Create post
		
		if(user_dashboard.postTextAreaDisplayed())
		{
			user_dashboard.enterPostMessage("This is a test message for post");
			user_dashboard.selectImage("https://media.vanityfair.fr/photos/61239db1ec092f6abd562026/16:9/w_1920,c_limit/TCDMIRO_EC036.jpg");
			
			
			// Setup DevTools (ChromeDriver) and target Network Feature to Monitor Requests
			
			devtools = ((ChromeDriver) driver).getDevTools();
			devtools.createSession();
			devtools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
			
			Thread.sleep(1500);
			
			//click on Add Schedule Button
			user_dashboard.addToSchedule();
			
			//Declare Request id to store request id
			final RequestId[] requestId = new RequestId[1];
			
			// Capture Response data from Network Feature (DevTools)
			
			devtools.addListener(Network.responseReceived(), responseConsumer -> {
				
			//if condition capture only API_URL/Create end-point request
			if(responseConsumer.getResponse().getUrl().contains("/create"))
			{
			requestId[0] = responseConsumer.getRequestId();	
			String responseBody = devtools.send(Network.getResponseBody(requestId[0])).getBody();
			
			// extract original_id key value from JSON Response
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode jsonNode;
			
			try {
				jsonNode = objectMapper.readTree(responseBody);
				long originalId = jsonNode.path("data").path("original_id").asInt();
				
				// Convert int data String
				String originalId1 = Long.toString(originalId);

				// Output the original_id fir
				System.out.println("Original ID: " + originalId1);
				
				// Set Value for another Test Case
				context.setAttribute("postId", originalId1);
				}
			catch (JsonProcessingException e){
				// No Need to implement Exception catch code
				}
			}
				});
			
			
			
			Thread.sleep(1500);
			
			assertTrue(!(user_dashboard.postTextAreaDisplayed()));
		}
	}
	
	@Test(priority = 3, description = "verify the functionality of Edit post")
	public void editPost(ITestContext context) throws Exception
	{
		
		user_dashboard = new UserDashboard(driver);
		
		// get data from Test case 2 and set to New String
		String postOriginaId = (String) context.getAttribute("postId");
		
		// Print for debugging
		System.out.println(postOriginaId);
		
		user_dashboard.editPost(postOriginaId);
		user_dashboard.updatePostContent(" Post Edit");
	}
}
