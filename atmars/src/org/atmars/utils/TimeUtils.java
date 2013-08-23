/*
 * atmars, An Implementation of a Micro Blog
 * Copyright (C) 2013, Cloudzfy
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.atmars.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
	// 返回现在时间
	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	// 将字符串转换为时间

	public static Date strToDateLong(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/**
	 * 两个时间之间的天数
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
		// 转换为标准时间
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
	 * 两个时间之间的小时数
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
		// 转换为标准时间
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
	 * 两个时间之间的分钟数
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
		// 转换为标准时间
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
