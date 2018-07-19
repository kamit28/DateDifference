package amit.codetest.xxxx;

import java.util.Scanner;

public final class DateDifferenceCalculator {
	public static final int[] daysInMonths = { 31, 28, 31, 30, 31, 30, 31, 31,
			30, 31, 30, 31 };
	public static final String DATE_PATTERN = "(\\d{4})-(0[1-9]|1[0-2])-([3][01]|[12][0-9]|0[1-9])$";

	public static final int YEAR_LOWER_RANGE = 1901;
	public static final int YEAR_UPPER_RANGE = 2999;

	/**
	 * Returns true if the dateStr is valid, false otherwise. Date validation
	 * criteria:
	 * <ul>
	 * <li>dateStr is not null and matches the valid date pattern.
	 * <li>year >= 1901 and year <= 2999
	 * <li>if month = 2 and year is leap year then day <= 29
	 * <li>if month = 2 and not a leap year then day <= 28
	 * <li>if month = 4 or 6 or 9 or 11, then day <= 30
	 * </ul>
	 * 
	 * @param dateStr
	 * @return
	 */
	public static boolean isValidDate(String dateStr) {
		if ((dateStr == null) || !dateStr.matches(DATE_PATTERN)) {
			return false;
		}
		String[] dateTokens = dateStr.split("-");
		int year = Integer.parseInt(dateTokens[0]);
		if ((year < YEAR_LOWER_RANGE) || (year > YEAR_UPPER_RANGE)) {
			return false;
		}
		int month = Integer.parseInt(dateTokens[1]);
		int day = Integer.parseInt(dateTokens[2]);
		if (((month == 4) || (month == 6) || (month == 9) || (month == 11))
				&& (day > 30)) {
			return false;
		}
		if (month == 2) {
			if ((isLeapYear(year)) && (day > 29)) {
				return false;
			}
			if ((!isLeapYear(year)) && (day > 28)) {
				return false;
			}
			return true;
		}
		return true;
	}

	/**
	 * Returns true if the given year is a leap year.
	 * 
	 * @param year
	 * @return
	 */
	public static boolean isLeapYear(int year) {
		return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
	}

	/**
	 * Returns number of days in a given year.
	 * 
	 * @param year
	 * @return
	 */
	public static int daysInYear(int year) {
		return isLeapYear(year) ? 366 : 365;
	}

	/**
	 * returns the number of days for the given month and year.
	 * 
	 * @param month
	 * @param year
	 * @return
	 */
	public static int daysInMonth(int month, int year) {
		return (month == 2) && (isLeapYear(year)) ? daysInMonths[(month - 1)] + 1
				: daysInMonths[(month - 1)];
	}

	/**
	 * Calculates number of days between startDate and endDate.
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws Exception
	 */
	public static int daysBetween(String startDate, String endDate)
			throws Exception {
		if ((isValidDate(startDate)) && (isValidDate(endDate))) {
			String[] dateTokens = startDate.split("-");
			int startYear = Integer.parseInt(dateTokens[0]);
			int startMonth = Integer.parseInt(dateTokens[1]);
			int startDay = Integer.parseInt(dateTokens[2]);

			dateTokens = endDate.split("-");
			int endYear = Integer.parseInt(dateTokens[0]);
			int endMonth = Integer.parseInt(dateTokens[1]);
			int endDay = Integer.parseInt(dateTokens[2]);

			if ((endYear < startYear)
					|| (endYear == startYear && endMonth < startMonth)
					|| ((endYear == startYear && endMonth == startMonth && endDay < startDay))) {
				throw new Exception("Start date value was after end date");
			}

			int daysInBetween = 0;

			// for each elapsed full year, calculate total days
			for (int year = startYear + 1; year < endYear; year++) {
				daysInBetween += daysInYear(year);
			}

			// same year case
			if ((daysInBetween == 0) && (startYear == endYear)) {
				for (int month = startMonth + 1; month < endMonth; month++) {
					daysInBetween += daysInMonth(month, startYear);
				}
			} else {
				// not same year
				for (int month = startMonth + 1; month <= 12; month++) {
					daysInBetween += daysInMonth(month, startYear);
				}

				for (int month = 1; month < endMonth; month++) {
					daysInBetween += daysInMonth(month, endYear);
				}
			}

			// Same year and same month case
			if (daysInBetween == 0) {
				daysInBetween = endDay - startDay - 1;
			} else {
				daysInBetween = daysInBetween
						+ (endDay + (daysInMonth(startMonth, startYear)
								- startDay - 1));
			}
			return daysInBetween;
		}
		throw new Exception("Date value was not valid");
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String repeat = "Y";
		while (repeat.equalsIgnoreCase("Y")) {
			System.out
					.println("Program to calculate difference between two dates.");
			System.out
					.println("Please make sure end date is bigger than start date.");
			System.out.print("Please enter start date (yyyy-mm-dd): ");
			String startDateStr = in.nextLine();
			System.out.print("Please enter end date (yyyy-mm-dd): ");
			String endDateStr = in.nextLine();
			try {
				System.out.println("Number of days between " + startDateStr
						+ " and " + endDateStr + " are: "
						+ daysBetween(startDateStr, endDateStr));
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			System.out.print("Want to run again [y/n]? ");
			repeat = in.nextLine();
		}
		in.close();
	}
}
