package com.leyou.common.utils;

import java.util.Calendar;
import java.util.Scanner;

/**
 * <p>Description: </P>
 *
 * @author Wu
 * @create 2018-11-03-12:45
 */
public class DateUtils
{
	private static Calendar getCalendar()
	{
		return Calendar.getInstance();
	}

	public static String getYear()
	{
		return String.valueOf((getCalendar().get(Calendar.YEAR)));
	}
	public static String getMonth()
	{
		return String.valueOf((getCalendar().get(Calendar.MONTH))+1);
	}
	public static String getDay()
	{
		return String.valueOf((getCalendar().get(Calendar.DAY_OF_MONTH)));
	}

}
