package com.testcases.mhcampusautomation;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateMHCampusCusotmer
	{

		// Initializing some of the needed parameters
		private static String driverPath = System.setProperty("webdriver.gecko.driver",
				"C:\\Google Drive\\MH Campus\\tests\\Qualitest Automation\\Selenium\\Things for eclipse\\geckodriver.exe");
		private static WebDriver driver = new FirefoxDriver();
		private static WebDriverWait wait = new WebDriverWait(driver, 7);
		private static DateFormat formatterForDate = new SimpleDateFormat("ddMMyyyy");
		private static DateFormat formatterForTimee = new SimpleDateFormat("HHmmss");
		private static long milliSeconds = System.currentTimeMillis();
		private String url = "https://login-aws-qa.tegrity.com/Service/login.aspx";
		private static String AdminUserName = "lior3";
		private static String AdminPassword = "87AEE2303027";
		private static String instiutaionName = "Client Services";
		private static String createMHCampusUserLink = ".//*[@id='ctl00_ContentPlaceHolder1_ClientServicesMain1_ClientServicesDashboard1_QuickLinks1_LinkButtonCreateMHCampusCustomer']";
		private static String addNewInstitutaionLink = ".//*[@id='ctl00_ContentPlaceHolder1_ClientServicesMain1_NewCustomer1_LinkButtonAddNewInstitution2']";
		private static String finalSaveButton = "ctl00_ContentPlaceHolder1_ClientServicesMain1_NewCustomer1_ButtonCreateCustomer";

		// Logging to MHCampus's Client Services
		@Test
		public void loginToClientServicesAsAdmin()
			{

				try
					{
						driver.get(url);
						driver.findElement(By.id("TextBoxInstitution")).clear();
						driver.findElement(By.id("TextBoxInstitution")).sendKeys(instiutaionName);
						driver.findElement(By.id("TextBoxUsername")).sendKeys(AdminUserName);
						driver.findElement(By.id("TextBoxPassword")).sendKeys(AdminPassword);
						driver.findElement(By.id("ButtonLogin")).click();
						wait.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath(createMHCampusUserLink)));
					}
				catch (Exception e)
					{
						fail("Could not complete login sequence " + e.getMessage());
					}

			}

		// Clicking on create MHCampus Customer and the the generating process
		@Test
		public void createMHCampusCustomer()
			{
				try
					{
						driver.findElement(By.xpath(createMHCampusUserLink)).click();
						wait.until(ExpectedConditions
								.presenceOfElementLocated(By.xpath(addNewInstitutaionLink)));
					}
				catch (Exception e)
					{
						fail("Unable to go into customer creation " + e.getMessage());
					}
			}

		// Starting to fill out the forms
		@Test
		public void newInstitution()
			{
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(milliSeconds);
				String currentDate = (formatterForDate.format(calendar.getTime()));
				String currentTime = (formatterForTimee.format(calendar.getTime()));
				String newInstitutionCreated = "AutomatedTest" + currentDate + currentTime;

				// Clicking on Add new institution link
				try
					{
						driver.findElement(By.xpath(addNewInstitutaionLink)).click();
						wait.until(ExpectedConditions.presenceOfElementLocated(By.id(
								"ctl00_ContentPlaceHolder1_ClientServicesMain1_NewInstitute_ButtonSaveNewInstitution")));
					}
				catch (Exception e)
					{
						fail("Unable to go into new institution creation" + e.getMessage());
					}
				// filling the new institution name and saving it.
				try
					{
						driver.findElement(By
								.xpath(".//*[@id='ctl00_ContentPlaceHolder1_ClientServicesMain1_NewInstitute_TextBoxNewInstitution']"))
								.sendKeys(newInstitutionCreated);
						driver.findElement(
								By.id("ctl00_ContentPlaceHolder1_ClientServicesMain1_NewInstitute_ButtonSaveNewInstitution"))
								.click();
						wait.until(ExpectedConditions
								.presenceOfElementLocated(By.id(finalSaveButton)));
					}
				catch (Exception e)
					{
						fail("Fail in saving a new institution " + e.getMessage());
					}

				// filling the details in the General Information form
				try
					{
						// filling the institution field
						driver.findElement(By
								.xpath(".//*[@id='ctl00_ContentPlaceHolder1_ClientServicesMain1_NewCustomer1_TextBoxInstitution2']"))
								.sendKeys(newInstitutionCreated);
					}
				catch (Exception e)
					{
						fail("Institution field was not found " + e.getMessage());
					}

				try
					{
						// fiiling the domain field
						driver.findElement(
								By.id("ctl00_ContentPlaceHolder1_ClientServicesMain1_NewCustomer1_TextBoxDomain2"))
								.sendKeys(newInstitutionCreated);
					}
				catch (Exception e)
					{
						e.printStackTrace();
					}
				try
					{
						// selecting account purposes
						WebElement dropDownListBox = driver.findElement(By.id(
								"ctl00_ContentPlaceHolder1_ClientServicesMain1_NewCustomer1_DropDownListAcountPurposeMHC"));
						Select clickThis = new Select(dropDownListBox);
						clickThis.selectByValue("Qa");
						Thread.sleep(5000);
					}
				catch (Exception e)
					{
						fail("Could not find option from dropdown list: " + e.getMessage());
					}

				// Choosing the digital environment
				try
					{
						WebElement dropDownListBoxDigEnv = driver.findElement(By.id(
								"ctl00_ContentPlaceHolder1_ClientServicesMain1_NewCustomer1_DropDownListEMHDigitalEnv"));
						System.out.println("the dropDownListBoxDigEnv element was found");
						Select clickThis = new Select(dropDownListBoxDigEnv);
						System.out.println("the select tag was found");
//						List <WebElement> allOptions = driver.findElements(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_ClientServicesMain1_NewCustomer1_DropDownListEMHDigitalEnv']/option"));
//						for (WebElement webElement : allOptions)
//							{
//								System.out.println(allOptions);
//							}
						clickThis.selectByIndex(2);//("QaStaging");

						
						System.out.println("the qa staging value was found");
						driver.findElement(By.cssSelector("div.row.config-school-selection")).click();
						System.out.println("click");
					}
				catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				// Generating installation ID.
				try
					{
						driver.findElement(
								By.id("ctl00_ContentPlaceHolder1_ClientServicesMain1_NewCustomer1_SchoolMHInfo_CheckBoxGenerate"))
								.click();
						Thread.sleep(5000);
					}
				catch (InterruptedException e1)
					{
						e1.printStackTrace();
					}
				// Choosing a country from the list.
				try
					{
						driver.findElement(By
								.xpath(".//*[@id='ctl00_ContentPlaceHolder1_ClientServicesMain1_NewCustomer1_SchoolMHInfo_RadComboBoxCountry_Input']"))
								.clear();
						driver.findElement(By
								.xpath(".//*[@id='ctl00_ContentPlaceHolder1_ClientServicesMain1_NewCustomer1_SchoolMHInfo_RadComboBoxCountry_Input']"))
								.sendKeys("Belgium");
						driver.findElement(By.cssSelector("div.row.config-school-selection")).click();
						Thread.sleep(5000);
						// driver.findElement(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_ClientServicesMain1_NewCustomer1_SchoolMHInfo_RadComboBoxCountry_DropDown']/div/ul/li[20]")).click();
					}
				catch (Exception e)
					{
						fail("Could not find option from dropdown list: " + e.getMessage());
					}
				// Choosing a university
				try
					{
						WebElement university = driver.findElement(By.id(
								"ctl00_ContentPlaceHolder1_ClientServicesMain1_NewCustomer1_SchoolMHInfo_TextBoxSchools"));
						university.sendKeys("birm");
						Thread.sleep(5000);
						wait.until(ExpectedConditions.presenceOfElementLocated(
								By.id("ctl00_ContentPlaceHolder1_ClientServicesMain1_NewCustomer1_SchoolMHInfo_TextBoxSchoolsAutoCompleteExtender_completionListElem")))
								.click();
						driver.findElement(
								By.id("ctl00_ContentPlaceHolder1_ClientServicesMain1_NewCustomer1_SchoolMHInfo_ButtonLoadInstallationId"))
								.click();
					}
				catch (Exception e)
					{
						fail("Could not find option from dropdown list: " + e.getMessage());
					}

			}

	}
