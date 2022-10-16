package br.com.investidortools.report.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class DateUtils {

	private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

	private DateUtils() {
		
	}
	
	public static LocalDateTime convertDate(String date) {
		
		DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern(DATE_PATTERN)
				.optionalStart()
				.appendPattern(".")
				.appendFraction(ChronoField.MICRO_OF_SECOND, 1, 9, false)
				.optionalEnd()
				.toFormatter();
		return LocalDateTime.parse((date), formatter);
	}
}
