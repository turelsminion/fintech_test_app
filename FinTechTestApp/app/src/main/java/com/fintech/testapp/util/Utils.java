package com.fintech.testapp.util;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

public class Utils {

	@SuppressLint("SimpleDateFormat")
	public static String formatDate(String date) {

		String formatFrom = "EEE MMM dd HH:mm:ss zzzz yyyy";
		String formatTo = "dd MMMM yyyy HH:mm";
		String result = "";
		SimpleDateFormat sdf;
		SimpleDateFormat sdf1;

		try {
			sdf = new SimpleDateFormat(formatFrom);
			sdf1 = new SimpleDateFormat(formatTo);
			sdf.setTimeZone(TimeZone.getTimeZone(String.valueOf(Calendar.getInstance().getTimeZone())));
			result = sdf1.format(Objects.requireNonNull(sdf.parse(date)));
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		} finally {
			sdf = null;
			sdf1 = null;
		}
		return result;
	}

}
