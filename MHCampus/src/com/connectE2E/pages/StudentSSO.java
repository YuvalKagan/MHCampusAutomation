package com.connectE2E.pages;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StudentSSO {

	static WebDriver driver;
	static WebDriverWait wait;
	
	WebElement userNameBox; 
	WebElement passwordBox;
	WebElement loginButton;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 60);
		driver.get("https://mcgraw-hill2.instructure.com/login/canvas");	
		}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void aLoginToCanvas() {
		
		userNameBox = driver.findElement(By.id("pseudonym_session_unique_id"));
		passwordBox = driver.findElement(By.id("pseudonym_session_password"));
		loginButton = driver.findElement(By.xpath(".//form[@id='login_form']//button[contains(text(),'Log In')]"));
		
		wait.until(ExpectedConditions.elementToBeClickable(loginButton));
		userNameBox.sendKeys("automationStu");
		passwordBox.sendKeys("123456");
		loginButton.click();
		
		driver.get("https://mcgraw-hill2.instructure.com/courses/2049514/external_tools/275936");
		driver.switchTo().frame("tool_content");
		WebElement connectButton = driver.findElement(By.id("ContentPlaceHolder1_UserClasses1_RepeaterCourses_UserClassesCommon_0_AddedServices2_0_connectLink_0"));
		connectButton.click();
		ArrayList<String> tabs =new ArrayList<>( driver.getWindowHandles());
		System.out.println(tabs.size());
		driver.switchTo().window((String) tabs.get(1));
		System.out.println(driver.getCurrentUrl());
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
		System.out.println("Frame is visible");
		
		try{
			Thread.sleep(7500);
			driver.switchTo().frame(0);
		}
		catch (Exception e ){
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='relative-block']//header/span")));
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='515796941~12120476~92026']/div[2]")));
		//System.out.println("The element is clickable");
		//driver.findElement(By.xpath(".//*[@class='quick-launch-action-ie-fix']"));
		
		String courseName = driver.findElement(By.xpath(".//*[@class='relative-block']//header/span")).getText();
		System.out.println(courseName);
		if (!(courseName.contains("AUTOMATIONCOURSE"))){
			fail();
		}
		driver.close();
		driver.switchTo().window((String) tabs.get(0));
		driver.close();
		System.out.println("SUCCESS");
	}

}