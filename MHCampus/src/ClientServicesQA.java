import java.math.BigInteger;
import java.util.Calendar;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base32OutputStream;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ClientServicesQA {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String id ="LNWS-AM38-12M9";
		id= id.replace("-", "");
		System.out.println(id);
		
		byte[] bytes ={};
		BigInteger big = new BigInteger(id, 36);
		System.out.println(big);		
		
		
		
/*		ClientServicesQA cs = new ClientServicesQA();
		WebDriver driver;
		
		Calendar cal = Calendar.getInstance();
		int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		
		
		
		driver = new FirefoxDriver();
		String baseURL  = "https://login-aws-qa.tegrity.com/Service/login.aspx";
//		cs.loginToClientServices(baseURL, driver);
//		cs.createNewTegrityCustomer(driver);
		cs.createNewInstitution(driver, dayOfMonth, month, year);
		
//		System.out.println("The current day of the month is: " + dayOfMonth);
//		System.out.println("The current month is: " + month);
*/		
		
		
		
		
		
	}
	
	public void loginToClientServices (String url, WebDriver driver) throws InterruptedException{
		String institutionName = "Client Services";
		String userName ="MHCampusAutomationAdmin";
		String password = "12345678";
		
		driver.manage().window().maximize();
		driver.get(url);
		
		driver.findElement(By.id("TextBoxInstitution")).clear(); // clearing the institution text box
		driver.findElement(By.id("TextBoxInstitution")).sendKeys(institutionName); // enter institution name
		driver.findElement(By.id("TextBoxUsername")).clear(); // enter user name
		driver.findElement(By.id("TextBoxUsername")).sendKeys(userName);// clearing the user name text box
		driver.findElement(By.id("TextBoxPassword")).clear(); // enter password
		driver.findElement(By.id("TextBoxPassword")).sendKeys(password);// clearing the password text box
		
		driver.findElement(By.id("ButtonLogin")).click(); //Clicking on the Login button
		
		
		Thread.sleep(2000);
		//driver.findElement(By.partialLinkText("Convert")).click(); // find element by partial text
		//driver.findElement(By.id("ctl00_LoginStatus1")).click();
		
		driver.close();
	}
	public void createNewTegrityCustomer(WebDriver driver) throws Exception{
		driver.findElement(By.linkText("Create Tegrity customer")).click();
		Thread.sleep(2000);
		driver.close();
	}
	public void createNewInstitution (WebDriver driver, int day, int month, int year){
		String institutionName;
		
		if (month < 10){
			institutionName = "AutomationCustomer" + day+"0"+month + year;
		}
		else{
			institutionName = "AutomationCustomer" + day+month + year;
		}
		 
		 System.out.println(institutionName);
		
//		driver.findElement(By.id("ctl00_ContentPlaceHolder1_ClientServicesMain1_NewCustomer1_ButtonAddNewInstitution")).click();
//		driver.findElement(By.id("ctl00_ContentPlaceHolder1_ClientServicesMain1_NewInstitute_TextBoxNewInstitution")).clear();
//		driver.findElement(By.id("ctl00_ContentPlaceHolder1_ClientServicesMain1_NewInstitute_TextBoxNewInstitution")).sendKeys(institutionName);
//		driver.findElement(By.id("ctl00_ContentPlaceHolder1_ClientServicesMain1_NewInstitute_ButtonSaveNewInstitution")).click();
//		
//		driver.findElement(By.id("ctl00_ContentPlaceHolder1_ClientServicesMain1_NewCustomer1_TextBoxInstitution")).sendKeys("institutionName");
//		driver.findElement(By.id("ctl00_ContentPlaceHolder1_ClientServicesMain1_NewCustomer1_TextBoxDomain")).sendKeys(institutionName.toLowerCase());
//		driver.findElement(By.id("ctl00_ContentPlaceHolder1_ClientServicesMain1_NewCustomer1_TextBoxAcctLicenseType")).sendKeys("QA");
		
//		// ****not to continue with drop down list handling****
		
		
		
		
		
	}

}
