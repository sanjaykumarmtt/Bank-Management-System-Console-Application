package com.zsgs.bankManagement.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class ParseHelper {

	private static final String DATE_PATTERN = "dd-MM-yyyy";
	private static final String DATE_TIME_PATTERN = "dd-MM-yyyy HH:mm";
	private static final String EMPTY_PLACEHOLDER = "-";

	public static Long parseDate(String input) {
		if (input == null || input.trim().isEmpty())
			return null;
		SimpleDateFormat format = new SimpleDateFormat(DATE_PATTERN, Locale.ROOT);
		format.setLenient(false);
		format.setTimeZone(TimeZone.getDefault());
		try {
			return format.parse(input.trim()).getTime();
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static int calculateAgeYears(long dobMillis) {
		Calendar dobCal = Calendar.getInstance();
		dobCal.setTimeInMillis(dobMillis);
		Calendar nowCal = Calendar.getInstance();
		int age = nowCal.get(Calendar.YEAR) - dobCal.get(Calendar.YEAR);
		if (nowCal.get(Calendar.DAY_OF_YEAR) < dobCal.get(Calendar.DAY_OF_YEAR))
			age--;
		return age;
	}
}
