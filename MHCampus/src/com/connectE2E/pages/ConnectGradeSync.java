package com.connectE2E.pages;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.eclipse.jdt.internal.compiler.lookup.UpdatedMethodBinding;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ConnectGradeSync {

	static WebDriver driver;
	static WebDriverWait wait;

	WebElement usernameBox;
	WebElement passwordBox;
	WebElement loginButton;
	WebElement courseButton;
	WebElement gradeSection;
	WebElement scoreBox;
	WebElement totalPoints;
	WebElement courseHomeLink;
	WebElement mhcLink;
	WebElement connectButton;
	WebElement viewReportButton;
	WebElement reportScoreButton;
	WebElement newScoreBox;
	WebElement adjustPointsButton;
	WebElement gradebookSyncLink;
	WebElement checkBoxButton;
	WebElement syncButton;
	WebElement dropDownAttempt;
	WebElement closeButton;
	WebElement syncStatusBox;
	
	
	

	String instUsername = "automationIns";
	String instPassword = "123456";
	static String currentScore;
	String pointsPossible;
	String adjustedPoints;
	String connectHomeScreen = "http://connectqastg.mheducation.com/connect/hmInstructorSectionHomePortal.do?sectionId=515796941";

	int originalScore;

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver, 10);
		driver.get("https://mcgraw-hill2.instructure.com/login/canvas");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		driver.close();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void aLoginToCanvasAsInstructor() {

		usernameBox = driver.findElement(By.id("pseudonym_session_unique_id"));
		usernameBox.clear();
		passwordBox = driver.findElement(By.id("pseudonym_session_password"));
		passwordBox.clear();
		loginButton = driver.findElement(By.xpath(".//*[@id='login_form']/div[3]/div[2]/button"));

		try {
			usernameBox.sendKeys(instUsername);
			passwordBox.sendKeys(instPassword);
			loginButton.click();
			System.out.println("Login successfull!");
		} catch (Exception e) {
			fail("Login failed");
			e.printStackTrace();
		}

	}
	
	@Test
	public void bGoToCourse() {
	courseButton = driver.findElement(By.xpath(".//*[@id='DashboardCard_Container']/div/div[1]/div[1]/div[1]"));
	wait.until(ExpectedConditions.elementToBeClickable(courseButton));
	try {
		courseButton.click();
	} catch (Exception e) {
		fail("Course page is not found");
		e.printStackTrace();
	}
	}
	
	@Test
	public void cGoToGradesSection() throws Exception{
		goToGradeBookPage(driver);
		gradeSection.click();
		Thread.sleep(3000);
		//scoreBox = driver.findElement(By.xpath(".//*[@id='gradebook_grid']/div[3]/div[4]/div/div/div[1]/div"));
		//totalPoints = driver.findElement(By.cssSelector(".assignment-points-possible"));
		
		currentScore = getCurrentScoreFromCanvas(driver);
		pointsPossible = getPointsPossibleFromCanvas(driver);
		
		System.out.println("Current score is: " + currentScore + " " + pointsPossible);
		Thread.sleep(1000);
		System.out.println("Going back to course home screen");
		courseHomeLink = driver.findElement(By.xpath(".//*[@id='breadcrumbs']/ul/li[2]/a/span"));
		courseHomeLink.click();
		
	}
	
	@Test
	public void dGoToConnect() throws Exception{
		mhcLink = driver.findElement(By.xpath(".//*[@id='section-tabs']/li[15]/a"));
		System.out.println("Clicking on Campus link");
		mhcLink.click();
		Thread.sleep(6000);
		driver.switchTo().frame(1);
		connectButton = driver.findElement(By.xpath(".//*[@id='ContentPlaceHolder1_UserClasses1_RepeaterCourses_UserClassesCommon_0_AddedServices2_0_connectImg_0']"));
		System.out.println("Clicking on Connect Tool button");
		try {
			connectButton.click();
		} catch (Exception e) {
			fail("Connect button was not found");
			e.printStackTrace();
		}
		ArrayList<String> tabs =new ArrayList<>( driver.getWindowHandles());
		System.out.println(tabs.size());
		driver.switchTo().window((String) tabs.get(1));
	}
	
	@Test
	public void eConnectInstrucorAtcion() throws Exception {
		Thread.sleep(5000);
		System.out.println("Going to performence page");
		driver.get("http://connectqastg.mheducation.com/connect/assignmentResultReport/show.htm?sectionId=515796941");
		wait.until(ExpectedConditions.elementToBeClickable(By.id("view_report_btn_e")));
		Thread.sleep(1000);
		viewReportButton = driver.findElement(By.id("view_report_btn_e"));
		System.out.println("Going to assignment report page");
		try {
			viewReportButton.click();
		} catch (Exception e) {
			fail("View report button was not found.");
			e.printStackTrace();
		}
		Thread.sleep(3500);
		reportScoreButton = driver.findElement(By.xpath(".//*[@id='grade_report_cntr_e']/ul[2]/li[2]/span"));
		try {
			reportScoreButton.click();
		} catch (Exception e) {
			fail("Failed to find reportScoreButton");
			e.printStackTrace();
		}
		try {
			driver.switchTo().frame(0);
		} catch (Exception e) {
			System.out.println("looking for frame 2");
			driver.switchTo().frame(2);
			e.printStackTrace();
		}
		Thread.sleep(3000);
		newScoreBox = driver.findElement(By.id("Q_13570164006588463_score"));
		adjustPointsButton = driver.findElement(By.id("Q_13570164006588463_save"));
		System.out.println("Clearing current score");
		newScoreBox.clear();
		System.out.println("Sendning new score");
		double convertFromString = Double.parseDouble(currentScore);
		System.out.println(convertFromString+0.1);
		adjustedPoints = Double.toString(convertFromString+0.1);
		newScoreBox.sendKeys(adjustedPoints);
		System.out.println("Adjusting score to: " +adjustedPoints);
		try {
			adjustPointsButton.click();
		} catch (Exception e) {
			System.out.println("unable to adjust score");
			e.printStackTrace();
		}
		Thread.sleep(2000);
		driver.get(connectHomeScreen);
	}
	
	@Test
	public void fGradeSyncProcess() throws Exception{
		gradebookSyncLink = driver.findElement(By.className("grade-book-syn-list"));
		gradebookSyncLink.click();
		Thread.sleep(2500);
		checkBoxButton = driver.findElement(By.id("12131214"));
		try {
			checkBoxButton.click();
			System.out.println("Check box checked!");
		} catch (Exception e) {
			System.out.println("Check box was not found");
			e.printStackTrace();
		}
		
		//TODO: select 'last' from dropdown list
		try {
			System.out.println("Selecting 'Best' from drop down list");
			Select dropDownAttempt = new Select(driver.findElement(By.id("mySelect")));
			dropDownAttempt.selectByValue("Best");
		} catch (Exception e1) {
			System.out.println("Selecting 'Best' from drop down list unsuccesfful");
			e1.printStackTrace();
		}
		
		syncButton = driver.findElement(By.id("syncaction"));
		try {
			syncButton.click();
			System.out.println("Sync button clicked");
		} catch (Exception e) {
			System.out.println("Sync button was not found");
			e.printStackTrace();
		}
		
		try {
			closeButton = driver.findElement(By.xpath(".//*[@id='modalCtr']/div[2]/div[2]/a"));
			System.out.println("clicking on close button in the popup");
			closeButton.click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Close button was not found");
			e.printStackTrace();
		}
		
		try {
			syncStatusBox = driver.findElement(By.xpath(".//*[@id='12131214_status']/div/span[2]"));
			String syncStatusText = syncStatusBox.getText();
			while (syncStatusText.contains("progress")){
				System.out.println("Grade sync in progress,Waiting 30 seconds");
				Thread.sleep(30000);
				driver.navigate().refresh();
				syncStatusBox = driver.findElement(By.xpath(".//*[@id='12131214_status']/div/span[2]"));
				syncStatusText = syncStatusBox.getText();
				
			}
			if (syncStatusText.contains("failed")){
				fail();
			}
			System.out.println("The reuslt of the grade sync is: " + syncStatusText);
		} catch (Exception e) {
			System.out.println("Cannot find Sync Staues Box");
			e.printStackTrace();
			Thread.sleep(3000);
		}
		ArrayList<String> tabs =new ArrayList<>( driver.getWindowHandles());
		driver.close();
		driver.switchTo().window((String) tabs.get(0));
	}
	@Test
	public void gGradesCompare() throws Exception{
		String updatedPoints;
		
		goToGradeBookPage(driver);
		gradeSection.click();
		Thread.sleep(3000);
		updatedPoints= getCurrentScoreFromCanvas(driver);
		//double doubleUpdatePoints = Double.parseDouble(updatedPoints);
		if (!((Double.parseDouble(updatedPoints) == Double.parseDouble(currentScore)+0.1))){
			fail();
		}
		else{
			driver.get("https://vanamgram.files.wordpress.com/2012/05/success.jpg");
			Thread.sleep(60000);
		}
	}
	
	private void goToGradeBookPage(WebDriver driver){
		gradeSection = driver.findElement(By.xpath(".//*[@id='section-tabs']/li[5]/a"));
		wait.until(ExpectedConditions.elementToBeClickable(gradeSection));
	}
	private String getCurrentScoreFromCanvas(WebDriver driver){
		String updatedPoints;
		
		scoreBox = driver.findElement(By.xpath(".//*[@id='gradebook_grid']/div[3]/div[4]/div/div/div[1]/div"));
		updatedPoints = scoreBox.getText();
		return updatedPoints;
		
	}
	private String getPointsPossibleFromCanvas(WebDriver driver){
		totalPoints = driver.findElement(By.cssSelector(".assignment-points-possible"));
		pointsPossible = totalPoints.getText();
		return pointsPossible;
	}
}
