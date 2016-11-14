package Selfreg;

import static org.junit.Assert.fail;

import java.util.Properties;

import org.apache.commons.exec.ExecuteException;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelfRegTC{
		@Before
		public void loginToTegrityAsAdmin(WebDriver driver, String url) throws ExecuteException
			{
				System.out.println("----Login in Tegrity as admin");
				String admin = "mtaadmin1";
				String password = "111";
				try
					{
						driver.get(url);

						driver.findElement(By.id("TextFieldUserName")).clear();
						driver.findElement(By.id("TextFieldUserName")).sendKeys(admin);

						driver.findElement(By.id("TextFieldPassword")).clear();
						driver.findElement(By.id("TextFieldPassword")).sendKeys(password);

						driver.findElement(By.id("ButtonLogin")).click();
						System.out.println("----Login was sucessful");
					}
				catch (Exception e)
					{
						fail("Not yet implemented" + e.getMessage());

					}
			}

		@Test
		public String createUserId(String id)
			{
				try
					{
						id = id + System.currentTimeMillis();
					}
				catch (Exception e)
					{
						fail("Not yet implemented");
					}
				return id;
			}
		
		public String createCourseId(String courseId)
			{
				courseId = courseId + System.currentTimeMillis();
				return courseId;
			}

		public void createUser(Properties prop, String id) throws InterruptedException, ExecuteException
			{

				String fullName, email, password;
				fullName = id.toUpperCase();
				email = id + "@mail.com";
				password = "111";
				try
					{
						
						WebDriver driver = new FirefoxDriver();
						WebDriverWait  wait1= new WebDriverWait(driver, 10);
						
						driver.manage().window().maximize();
						loginToTegrityAsAdmin(driver, prop.getProperty("baseURL"));
						//Thread.sleep(10000);
						wait1.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Manage Ad-hoc Users")));
						System.out.println("element is clikable");
						clickOnManageAdhocUsers(driver);
						//Thread.sleep(10000);
						// need switch to iFrame
						wait1.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
						System.out.println("driver moved to frame");
						//driver.switchTo().frame(0);
						clickOnNewUserLink(driver);
						insertUserDetails(driver, id, fullName, email, password);
						wait1.until(ExpectedConditions.alertIsPresent());
						System.out.println("alert is dispalyed");
						Alert alert = driver.switchTo().alert();
						alert.accept();

						driver.close();
						System.out.println(id + " was created");
					}
				catch (Exception e)
					{
						System.out.println(e.getMessage());
					}

			}

		public void createCourse(Properties prop, String id) throws InterruptedException
			{
				try
					{
						WebDriver driver = new FirefoxDriver();
						WebDriverWait wait = new WebDriverWait(driver, 20);
						driver.manage().window().maximize();
						loginToTegrityAsAdmin(driver, prop.getProperty("baseURL"));
						//Thread.sleep(10000);
						wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Manage Ad-hoc Courses / Enrollments")));
						System.out.println("Manage Ad-hoc Courses / Enrollments is clickable");
						clickOnManageAdhocCourses(driver);
						//Thread.sleep(10000);
						// need switch to iFrame
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
						System.out.println("driver moved to frame");
						//driver.switchTo().frame(0);
						clickOnNewCourseLink(driver);
						insertCourseDetails(driver, id);
						
						System.out.println("Course " + id + " was created");
					}
				catch (Exception e)
					{
						System.out.println(e.getMessage());
					}

			}

		public void deleteUser(Properties prop, String id) throws InterruptedException, ExecuteException
			{
				WebDriver driver = new FirefoxDriver();
				driver.manage().window().maximize();
				try
					{
						WebDriverWait wait = new WebDriverWait(driver, 30);
						loginToTegrityAsAdmin(driver, prop.getProperty("baseURL"));
						String xpath = ".//div[@id='contentDIV']/table/tbody/tr[td//text()[contains(., '" + id
								+ "')]]/td[4]/a[2]";
						//System.out.println(xpath.compareTo(".//div[@id='contentDIV']/table/tbody/tr[td//text()[contains(., // '11111111111111')]]/td[4]/a[2]"));
						//Thread.sleep(10000);
						wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Manage Ad-hoc Users")));
						System.out.println("element is clikable");
						clickOnManageAdhocUsers(driver);
						Thread.sleep(10000);
						// need switch to iFrame
						//driver.switchTo().frame(0);
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
						driver.findElement(By.xpath(xpath)).click();
						Thread.sleep(10000);
						wait.until(ExpectedConditions.alertIsPresent());
						Alert alert = driver.switchTo().alert();

						alert.accept();
						driver.close();
						System.out.println(id + " was deleted");
					}
				catch (Exception e)
					{
						System.out.println(e.getMessage());
					}
			}

		public void deleteCourse(Properties prop, String id) throws InterruptedException, ExecuteException
			{
				WebDriver driver = new FirefoxDriver();
				driver.manage().window().maximize();
				WebDriverWait wait = new WebDriverWait(driver, 30);
				loginToTegrityAsAdmin(driver, prop.getProperty("baseURL"));
				String xpath = ".//div[@id='contentDIV']/table/tbody/tr[td//text()[contains(., '" + id
						+ "')]]/td[3]/a[2]";
				try
					{
						//Thread.sleep(10000);
						wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Manage Ad-hoc Courses / Enrollments")));
						clickOnManageAdhocCourses(driver);
						//Thread.sleep(10000);
						// need switch to iFrame
						
						//driver.switchTo().frame(0);
						wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
						driver.findElement(By.xpath(xpath)).click();
						Thread.sleep(10000);
						wait.until(ExpectedConditions.alertIsPresent());
						Alert alert = driver.switchTo().alert();
						alert.accept();
						driver.close();

						System.out.println(id + " was deleted");
					}
				catch (Exception e)
					{
						System.out.println(e.getMessage());
					}
			}

		public void clickOnManageAdhocUsers(WebDriver driver) throws Exception
			{
				Thread.sleep(3000);
				driver.findElement(By.partialLinkText("Manage Ad-hoc Users")).click();
			}

		public void clickOnManageAdhocCourses(WebDriver driver) throws Exception
			{
				Thread.sleep(3000);
				driver.findElement(By.partialLinkText("Manage Ad-hoc Courses / Enrollments")).click();
			}

		public void clickOnNewUserLink(WebDriver driver)
			{
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_lbNewUser")).click();
			}

		public void clickOnNewCourseLink(WebDriver driver)
			{
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_NewCourseButton")).click();
			}

		public void insertUserDetails(WebDriver driver, String id, String fullName, String email, String password)
				throws InterruptedException
			{

				WebDriverWait wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_ContentPlaceHolder1_ucNewUserControl_ucDialog_btnOK")));
				// user id
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucNewUserControl_ucDialog_UserIDTextBox")).clear();
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucNewUserControl_ucDialog_UserIDTextBox"))
						.sendKeys(id);
				// user's full name
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucNewUserControl_ucDialog_UserNameTextBox"))
						.clear();
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucNewUserControl_ucDialog_UserNameTextBox"))
						.sendKeys(fullName);
				// user's email
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucNewUserControl_ucDialog_EmailTextBox")).clear();
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucNewUserControl_ucDialog_EmailTextBox"))
						.sendKeys(email);
				// user's password
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucNewUserControl_ucDialog_txtPassword")).clear();
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucNewUserControl_ucDialog_txtPassword"))
						.sendKeys(password);
				// password confirmation
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucNewUserControl_ucDialog_txtConfPassword"))
						.clear();
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucNewUserControl_ucDialog_txtConfPassword"))
						.sendKeys(password);
				Thread.sleep(10000);
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucNewUserControl_ucDialog_btnOK")).click();

			}

		public void insertCourseDetails(WebDriver driver, String id) throws InterruptedException
			{
				//Thread.sleep(10000);
			WebDriverWait wait = new WebDriverWait(driver, 30);
			wait.until(ExpectedConditions.elementToBeClickable(By.id("ctl00_ContentPlaceHolder1_ucNewCourseControl_ucDialog_btnOK")));
			System.out.println("The ok button is clickable");
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucNewCourseControl_ucDialog_CourseIDTextBox"))
						.clear();
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucNewCourseControl_ucDialog_CourseIDTextBox"))
						.sendKeys(id);

				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucNewCourseControl_ucDialog_FolderNameTextBox"))
						.clear();
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucNewCourseControl_ucDialog_FolderNameTextBox"))
						.sendKeys(id);
				Thread.sleep(10000);
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucNewCourseControl_ucDialog_btnOK")).click();
				wait.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();

				alert.accept();
				driver.close();
			}

		public void enrollUser(String courseId, String userId, Properties prop)
				throws InterruptedException, ExecuteException
			{

				WebDriver driver = new FirefoxDriver();
				WebDriverWait wait = new WebDriverWait(driver, 30);
				driver.manage().window().maximize();

				loginToTegrityAsAdmin(driver, prop.getProperty("baseURL"));
				System.out.println("----waiting 3 seconds");
				//Thread.sleep(10000);
				wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Manage Ad-hoc Courses / Enrollments")));
				try {
					clickOnManageAdhocCourses(driver);
				} catch (Exception e) {
					e.printStackTrace();
				}
				String xpath = ".//div[@id='contentDIV']/table/tbody/tr[td//text()[contains(., '" + courseId
						+ "')]]/td[3]/a[3]";
				//Thread.sleep(10000);
				System.out.println("----waiting 5 seconds");
				//driver.switchTo().frame(0);
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//div[@id='contentDIV']/table/tbody/tr[td//text()[contains(., '" + courseId
						+ "')]]/td[3]/a[3]")));
				System.out.println("The memberships button is clickable");
				driver.findElement(By.xpath(xpath)).click();
				
				//Thread.sleep(10000);
				//System.out.println("----waiting 3 seconds");
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//select[@id='ctl00_ContentPlaceHolder1_ucAddMemberships_ucDialog_ListBoxAllUsers']/option[text()[contains(., '"
						+ userId + "')]]")));
				String userIdXpath = ".//select[@id='ctl00_ContentPlaceHolder1_ucAddMemberships_ucDialog_ListBoxAllUsers']/option[text()[contains(., '"
						+ userId + "')]]";
				Thread.sleep(5000);
				driver.findElement(By.xpath(userIdXpath)).click();
				if (isInstructor(userId))
					{
						enrollInstructor(driver);
						//Thread.sleep(2000);
					}
				else
					{
						enrollStudent(driver);
						//Thread.sleep(2000);
					}
				//Thread.sleep(10000);
				//System.out.println("----waiting 3 seconds");
				Thread.sleep(10000);
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucAddMemberships_ucDialog_btnOK")).click();
				wait.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				alert.accept();
				driver.switchTo().defaultContent().close();
			}

		public void enrollInstructor(WebDriver driver)
			{
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucAddMemberships_ucDialog_ButtonAddInstructor"))
						.click();
			}

		public void enrollStudent(WebDriver driver)
			{
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucAddMemberships_ucDialog_ButtonAddStudent"))
						.click();
			}

		public void unEnrollUser(String courseId, String userId, Properties prop)
				throws ExecuteException, InterruptedException
			{

				WebDriver driver = new FirefoxDriver();
				WebDriverWait wait = new WebDriverWait(driver, 30);
				driver.manage().window().maximize();
				
				loginToTegrityAsAdmin(driver, prop.getProperty("baseURL"));
				//Thread.sleep(10000);
				//System.out.println("----waiting 3 seconds");
				wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Manage Ad-hoc Courses / Enrollments")));
				try {
					clickOnManageAdhocCourses(driver);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(0));
				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//div[@id='contentDIV']/table/tbody/tr[td//text()[contains(., '" + courseId
						+ "')]]/td[3]/a[3]")));
				String xpath = ".//div[@id='contentDIV']/table/tbody/tr[td//text()[contains(., '" + courseId
						+ "')]]/td[3]/a[3]";
				//Thread.sleep(10000);
				//System.out.println("----waiting 5 seconds");
				//driver.switchTo().frame(0);
				
				driver.findElement(By.xpath(xpath)).click();
				//Thread.sleep(10000);
				//System.out.println("----waiting 3 seconds");
				if (isInstructor(userId))
					{
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_ucAddMemberships_ucDialog_ListBoxInstructors']/option[text()[contains(., '"
							+ userId + "')]]")));
						String userIdXpath = ".//*[@id='ctl00_ContentPlaceHolder1_ucAddMemberships_ucDialog_ListBoxInstructors']/option[text()[contains(., '"
								+ userId + "')]]";
						driver.findElement(By.xpath(userIdXpath)).click();
						unEnrollInstructor(driver);
						System.out.println("The instructor " + userId + " was removed from the " + courseId + ".");
					}
				else
					{
						wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[@id='ctl00_ContentPlaceHolder1_ucAddMemberships_ucDialog_ListBoxStudents']/option[text()[contains(., '"
								+ userId + "')]]")));
						String userIdXpath = ".//*[@id='ctl00_ContentPlaceHolder1_ucAddMemberships_ucDialog_ListBoxStudents']/option[text()[contains(., '"
								+ userId + "')]]";
						driver.findElement(By.xpath(userIdXpath)).click();
						unEnrollStudent(driver);
						System.out.println("The student " + userId + " was removed from the " + courseId + ".");
					}
				System.out.println("----waiting 3 seconds");
				Thread.sleep(3000);
				
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucAddMemberships_ucDialog_btnOK")).click();
				Thread.sleep(10000);
				wait.until(ExpectedConditions.alertIsPresent());
				Alert alert = driver.switchTo().alert();
				alert.accept();
				driver.close();

			}

		public void unEnrollInstructor(WebDriver driver)
			{
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucAddMemberships_ucDialog_ButtonDeleteInstructor"))
						.click();
			}

		public void unEnrollStudent(WebDriver driver)
			{
				driver.findElement(By.id("ctl00_ContentPlaceHolder1_ucAddMemberships_ucDialog_ButtonDeleteStudent"))
						.click();
			}

		public boolean isInstructor(String userId)
			{
				boolean isIsntructor = false;
				if (userId.contains("Instructor"))
					{
						isIsntructor = true;
					}
				return isIsntructor;
			}

	}
