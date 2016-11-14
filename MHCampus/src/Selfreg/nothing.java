package Selfreg;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class nothing
	{

		public static void main(String[] args) throws IOException 
			{
				String path = "C:\\Google Drive\\MH Campus\\tests\\Qualitest Automation\\Selenium\\MHCampus\\src\\Selfreg\\config.properties";
				Properties prop = new Properties();
				FileInputStream fs = new FileInputStream(path);
				System.out.println(fs);
				prop.load(fs);
			}
	}
