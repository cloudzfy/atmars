package com.weibo.mars.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
	// 杩斿洖鐜板湪鏃堕棿
	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	// 灏嗗瓧绗︿覆杞崲涓烘椂闂�

	public static Date strToDateLong(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 涓や釜鏃堕棿涔嬮棿鐨勫ぉ鏁�
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */

	public static long getDays(String date1, String date2) {
		if (date1 == null || date1.equals(""))
			return 0;
		if (date2 == null || date2.equals(""))
			return 0;
		// 杞崲涓烘爣鍑嗘椂闂�
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		java.util.Date mydate = null;
		try {
			date = myFormatter.parse(date1);
			mydate = myFormatter.parse(date2);
		} catch (Exception e) {
		}
		long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		return day;
	}

	/**
	 * 涓や釜鏃堕棿涔嬮棿鐨勫皬鏃舵暟
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */

	public static long getHours(String hour1, String hour2) {
		if (hour1 == null || hour1.equals(""))
			return 0;
		if (hour2 == null || hour2.equals(""))
			return 0;
		// 杞崲涓烘爣鍑嗘椂闂�
		SimpleDateFormat myFormatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		java.util.Date hour = null;
		java.util.Date myhour = null;
		try {
			hour = myFormatter.parse(hour1);
			myhour = myFormatter.parse(hour2);
		} catch (Exception e) {
		}
		long returnHours = hour.getTime() - myhour.getTime();
		returnHours = returnHours  / (60 * 60 * 1000);
		return returnHours;
	}

	/**
	 * 涓や釜鏃堕棿涔嬮棿鐨勫垎閽熸暟
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */

	public static long getMinutes(String minute1, String minute2) {
		if (minute1 == null || minute1.equals(""))
			return 0;
		if (minute2 == null || minute2.equals(""))
			return 0;
		// 杞崲涓烘爣鍑嗘椂闂�
		SimpleDateFormat myFormatter = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		java.util.Date minute = null;
		java.util.Date myminute = null;
		try {
			minute = myFormatter.parse(minute1);
			myminute = myFormatter.parse(minute2);
		} catch (Exception e) {
		}
		long returnMinute = minute.getTime()-myminute.getTime();
		returnMinute = returnMinute  / (60 * 1000);
		return returnMinute;
	}

	public static String getTimeDelay(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String publishTime = formatter.format(date);
		String currentTime = getStringDate();

		long day = getDays(currentTime, publishTime);
		if (day > 3) {
			return publishTime;
		} else if (day >= 1) {
			return day + " days before";
		}
		long hour = getHours(currentTime, publishTime);
		if (hour >= 1) {
			return hour + " hours before";
		}
		long minute = getMinutes(currentTime, publishTime);
		if (minute > 1) {
			return minute + " minutes before";
		} else if (minute == 1) {
			return minute + " minute before";
		} else {
			return "just now";
		}
	}
}
