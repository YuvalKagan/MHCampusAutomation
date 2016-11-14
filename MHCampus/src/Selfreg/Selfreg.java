package Selfreg;

import java.io.FileInputStream;
import java.io.IOException;
//import java.util.Calendar;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Selfreg extends SelfRegTC
	{
		public static void main(String[] args) throws IOException, InterruptedException
			{

			
				String path = "C:\\Google Drive\\MH Campus\\tests\\Qualitest Automation\\Selenium\\MHCampus\\src\\Selfreg\\config.properties";
				Properties prop = new Properties();
				FileInputStream fs = new FileInputStream(path);
				prop.load(fs);
				boolean isSuccess = false;

				try{
					Selfreg sr = new Selfreg();
					System.out.println("*****Starting executing*****");
					String instructorUserId = sr.createUserId("Instructor");
					System.out.println("Instructor id: " + instructorUserId);
					String studentUserId = sr.createUserId("Student");
					System.out.println("Student id: " + studentUserId);
					String courseId = sr.createCourseId("Course");
	
					System.out.println("Course id: " + courseId);
					//String id = "" + System.currentTimeMillis();
	
					System.out.println("Creating user......");
					sr.createUser(prop, instructorUserId);
					System.out.println("Creating user......");
					sr.createUser(prop, studentUserId);
					System.out.println("Creating course......");
					sr.createCourse(prop, courseId);
					System.out.println("Enrolling instructor....");
					sr.enrollUser(courseId, instructorUserId, prop);
					System.out.println("Enrolling Student......");
					sr.enrollUser(courseId, studentUserId, prop);
					System.out.println("Unenrolling student");
					sr.unEnrollUser(courseId, instructorUserId, prop);
					System.out.println("Unenrolling instructor");
					sr.unEnrollUser(courseId, studentUserId, prop);
					System.out.println("Deleting course......");
					sr.deleteCourse(prop, courseId);
					System.out.println("Deleting user......");
					sr.deleteUser(prop, instructorUserId);
					System.out.println("Deleting user......");
					sr.deleteUser(prop, studentUserId);
	
					System.out.println("*****Execution finished*****");
					//driver.close();
					isSuccess = true;
					
				}
				catch (Exception e){
					e.printStackTrace();
				}
				if (isSuccess){
					WebDriver driver = new FirefoxDriver();
					driver.manage().window().maximize();
					driver.get("http://giphy.com/gifs/woman-on-freak-12fmPI7m38W4eY/fullscreen");
				}
			}

	}
