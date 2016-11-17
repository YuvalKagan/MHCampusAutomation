package com.testcases.mhcampusautomation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Tests
	{

		public static void main(String[] args)
			{

				DateFormat formatterForDate = new SimpleDateFormat("ddMMyyyy");
				DateFormat formatterForTimee = new SimpleDateFormat("HHmmss");
				long milliSeconds= System.currentTimeMillis();
				System.out.println(milliSeconds);
				Calendar calendar = Calendar.getInstance();
				calendar.setTimeInMillis(milliSeconds);
				String currentDate = (formatterForDate.format(calendar.getTime()));
				String currentTime = (formatterForTimee.format(calendar.getTime()));
				System.out.println(currentDate);
				System.out.println(currentTime);

			}

	}
