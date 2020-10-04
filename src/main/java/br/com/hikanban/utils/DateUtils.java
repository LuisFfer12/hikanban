package br.com.hikanban.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * The Class DateUtils.
 */
public class DateUtils {

	/** The Constant UTC. */
	public static final TimeZone UTC = TimeZone.getTimeZone("UTC");

	/**
	 * Instantiates a new date utils.
	 */
	private DateUtils() {
	}

	/**
	 * Format to UTC.
	 *
	 * @param date the date
	 * @param pattern the pattern
	 * @return the string
	 */
	public static String formatToUTC(Date date, String pattern) {
		return format(date, pattern, UTC);
	}

	/**
	 * Format.
	 *
	 * @param date the date
	 * @param pattern the pattern
	 * @param tz the tz
	 * @return the string
	 */
	public static String format(Date date, String pattern, TimeZone tz) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sdf.setTimeZone(tz);

		return sdf.format(date);
	}
}
