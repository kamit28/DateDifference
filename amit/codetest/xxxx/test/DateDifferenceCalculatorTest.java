package amit.codetest.xxxx.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import amit.codetest.xxxx.DateDifferenceCalculator;

public class DateDifferenceCalculatorTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public final void testIsValidDateWhenPatternIsWrong() {
		String dateStr = "1992-14-23";
		assertFalse(DateDifferenceCalculator.isValidDate(dateStr));

		dateStr = "992-04-23";
		assertFalse(DateDifferenceCalculator.isValidDate(dateStr));

		dateStr = "1992-11-32";
		assertFalse(DateDifferenceCalculator.isValidDate(dateStr));

		dateStr = "1992-4-23";
		assertFalse(DateDifferenceCalculator.isValidDate(dateStr));
	}

	@Test
	public final void testIsValidDateWhenDateStrIsNull() {
		String dateStr = null;

		assertFalse(DateDifferenceCalculator.isValidDate(dateStr));
	}

	@Test
	public final void testIsValidDateWhenYearIsOutOfRange() {
		String dateStr = "1892-11-32";
		assertFalse(DateDifferenceCalculator.isValidDate(dateStr));

		dateStr = "3892-11-32";
		assertFalse(DateDifferenceCalculator.isValidDate(dateStr));
	}

	@Test
	public final void testIsValidDateWhenDaysOutOfRangeForMonth() {
		String dateStr = "1992-11-31";
		assertFalse(DateDifferenceCalculator.isValidDate(dateStr));

		dateStr = "2892-04-31";
		assertFalse(DateDifferenceCalculator.isValidDate(dateStr));

		dateStr = "1942-02-29";
		assertFalse(DateDifferenceCalculator.isValidDate(dateStr));
	}

	@Test
	public final void testIsValidDateWhenDatePatternIsCorrect() {
		String dateStr = "1992-11-30";
		assertTrue(DateDifferenceCalculator.isValidDate(dateStr));
	}

	@Test
	public final void testIsLeapYear() {
		assertFalse(DateDifferenceCalculator.isLeapYear(1979));
		assertTrue(DateDifferenceCalculator.isLeapYear(2000));
		assertFalse(DateDifferenceCalculator.isLeapYear(2100));
		assertTrue(DateDifferenceCalculator.isLeapYear(1988));
	}

	@Test
	public final void testDaysInYear() {
		assertEquals(365, DateDifferenceCalculator.daysInYear(1979));
		assertEquals(365, DateDifferenceCalculator.daysInYear(2001));
		assertEquals(366, DateDifferenceCalculator.daysInYear(2004));
		assertEquals(365, DateDifferenceCalculator.daysInYear(2200));
		assertEquals(366, DateDifferenceCalculator.daysInYear(2400));
	}

	@Test
	public final void testDaysInMonth() {
		assertEquals(31, DateDifferenceCalculator.daysInMonth(1, 1979));
		assertEquals(31, DateDifferenceCalculator.daysInMonth(1, 2000));
		assertEquals(29, DateDifferenceCalculator.daysInMonth(2, 2004));
		assertEquals(28, DateDifferenceCalculator.daysInMonth(2, 2200));
		assertEquals(29, DateDifferenceCalculator.daysInMonth(2, 2400));
		assertEquals(31, DateDifferenceCalculator.daysInMonth(10, 1211));
		assertEquals(30, DateDifferenceCalculator.daysInMonth(11, 1999));
		assertEquals(30, DateDifferenceCalculator.daysInMonth(4, 1992));
		assertEquals(31, DateDifferenceCalculator.daysInMonth(12, 1980));
	}

	@Test
	public final void testDaysBetweenWhenDatesAreValid() throws Exception {
		String startDateStr = "1992-11-30";
		String endDateStr = "1993-11-30";
		assertEquals(364,
				DateDifferenceCalculator.daysBetween(startDateStr, endDateStr));

		startDateStr = "2001-11-09";
		endDateStr = "2001-11-10";
		assertEquals(0,
				DateDifferenceCalculator.daysBetween(startDateStr, endDateStr));

		startDateStr = "1921-03-31";
		endDateStr = "2001-03-31";
		assertEquals(29219,
				DateDifferenceCalculator.daysBetween(startDateStr, endDateStr));

		startDateStr = "1984-07-04";
		endDateStr = "1984-12-25";
		assertEquals(173,
				DateDifferenceCalculator.daysBetween(startDateStr, endDateStr));

		startDateStr = "1983-06-02";
		endDateStr = "1983-06-22";
		assertEquals(19,
				DateDifferenceCalculator.daysBetween(startDateStr, endDateStr));
	}

	@Test(expected = Exception.class)
	public final void testDaysBetweenWhenDatesAreInValid() throws Exception {
		String startDateStr = "1892-11-30";
		String endDateStr = "3993-11-30";
		DateDifferenceCalculator.daysBetween(startDateStr, endDateStr);
	}

	@Test(expected = Exception.class)
	public final void testDaysBetweenWhenEndDateIsBeforeStartDate()
			throws Exception {
		String startDateStr = "1992-11-30";
		String endDateStr = "1991-11-30";
		DateDifferenceCalculator.daysBetween(startDateStr, endDateStr);
	}

}
