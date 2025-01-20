package ca.bcit.comp2522.lab2;

/**
 * Represents a Date object with year, month, and day fields.
 *
 * <p>This class is designed for date validation and manipulation, ensuring
 * compliance with constraints such as valid ranges for years, months, and days.
 * It provides utilities to calculate leap years, validate user-inputted dates,
 * and determine the day of the week for a given date.</p>
 *
 * <p>Examples:</p>
 * <pre>
 *     Date date = new Date(2025, 10, 31);
 *     System.out.println(date.getDayOfTheWeek()); // "Friday"
 *     System.out.println(date.getYyyyMmDd());    // "2025-10-31"
 * </pre>
 *
 * @author Liam Pickrell
 * @author Ryan Chu
 * @author Ted Ip
 * @version 1.0
 */
public class Date {

    // The earliest year a bank client can input as their birth year.
    private static final int EARLIEST_BIRTH_YEAR = 1800;
    // The current year.
    private static final int CURRENT_YEAR = 2025;
    // The minimum number a month can be.
    private static final int MIN_MONTH = 1;
    // The maximum number a month can be.
    private static final int MAX_MONTH = 12;
    // The minimum number a day can be.
    private static final int FIRST_DAY_OF_MONTH = 1;
    // The number of days February has (barring leap years).
    private static final int FEBRUARY_DAYS = 28;
    // The number of days February has in a leap year.
    private static final int FEBRUARY_LEAP_DAYS = 29;
    // The number of days a month with 30 days has.
    private static final int EVEN_MONTH_DAYS = 30;
    // The number of days a month with 31 days has.
    private static final int ODD_MONTH_DAYS = 31;

    // The months and the order they come in the year.
    private static final int JANUARY = 1;
    private static final int FEBRUARY = 2;
    private static final int MARCH = 3;
    private static final int APRIL = 4;
    private static final int MAY = 5;
    private static final int JUNE = 6;
    private static final int JULY = 7;
    private static final int AUGUST = 8;
    private static final int SEPTEMBER = 9;
    private static final int OCTOBER = 10;
    private static final int NOVEMBER = 11;
    private static final int DECEMBER = 12;

    // The months and their month codes for the 1900s.
    // month codes: 144025036146
    private static final int JAN_CODE = 1;
    private static final int FEB_CODE = 4;
    private static final int MARCH_CODE = 4;
    private static final int APR_CODE = 0;
    private static final int MAY_CODE = 2;
    private static final int JUNE_CODE = 5;
    private static final int JULY_CODE = 0;
    private static final int AUG_CODE = 3;
    private static final int SEPT_CODE = 6;
    private static final int OCT_CODE = 1;
    private static final int NOV_CODE = 4;
    private static final int DEC_CODE = 6;

    // What order the days of the week appear in
    private static final int SATURDAY = 0;
    private static final int SUNDAY = 1;
    private static final int MONDAY = 2;
    private static final int TUESDAY = 3;
    private static final int WEDNESDAY = 4;
    private static final int THURSDAY = 5;
    private static final int FRIDAY = 6;

    private static final int CHECK_IF_EVEN = 0;
    private static final int YEAR_OFFSET_1800 = 2;
    private static final int YEAR_OFFSET_2000 = 6;

    private static final int LEAP_YEAR_CHECK_EVEN = 4;
    private static final int DAYS_IN_A_WEEK = 7;
    private static final int CALCULATE_NO_TWELVES = 12;
    private static final int CANCEL_OUT_CENTURY = 100;
    private static final int FOUR_HUNDRED = 400;
    private static final int CANCEL_OUT_MILLENIUM = 1000;
    private static final int YEAR_EIGHTEEN_NINETY_NINE = 1899;
    private static final int YEAR_NINETEEN_HUNDRED = 1900;
    private static final int YEAR_NINETEEN_NINETY_NINE = 1999;
    private static final int YEAR_TWO_THOUSAND = 2000;
    private static final int YEAR_TWO_THOUSAND_NINETY_NINE = 2099;

    private final int year;
    private final int month;
    private final int day;

    /**
     * Constructs a Date object with the specified year, month, and day.
     *
     * @param year  the year (1800-<current year>)
     * @param month the month (1-12)
     * @param day   the day (1-31, based on month and leap year rules)
     * @throws IllegalArgumentException if the date is invalid
     */
    public Date(final int year, final int month, final int day)
    {
        if (year < EARLIEST_BIRTH_YEAR || year > CURRENT_YEAR ||
                month < MIN_MONTH || month > MAX_MONTH ||
                !validateUserBirthDate(year, month, day))
        {
            throw new IllegalArgumentException("That date is not valid.");
        }
        this.year = year;
        this.month = month;
        this.day = day;
    }

    /**
     * Returns the day of the date as an int.
     *
     * @return day of the month
     */
    public int getDay()
    {
        return day;
    }

    /**
     * Returns the month of the date as an int.
     *
     * @return int the inputted month
     */
    public int getMonth()
    {
        return month;
    }

    /**
     * Returns the year of the date as an int.
     *
     * @return int the inputted year
     */
    public int getYear()
    {
        return year;
    }

    /**
     * Returns the date as a string in ISO-8601 format (YYYY-MM-DD), or, yoda time.
     * This format is commonly used in international contexts
     * and ensures compatibility with standard date parsing libraries.
     *
     * @return the date as a formatted string in YYYY-MM-DD fomrat.
     */
    public String getYyyyMmDd()
    {
        String yodaTime;

        yodaTime = year + "-" + month + "-" + day;

        return yodaTime;
    }

    /**
     * Calculates and returns the day of the week for the date.
     *
     * <p>The calculation is based on Zeller's Congruence algorithm
     * and adjusted for specific centuries (1800s, 1900s, 2000s).</p>
     *
     * To get the day of the week, do the following seven steps for dates in the 1900s:
     * e.g. October 31, 1977:
     * step 1: calculate the number of twelves in 77: 6
     * step 2: calculate the remainder from step 1: 77 - 12*6 = 77 - 72 = 5
     * step 3: calculate the number of fours in step 2: 5/4 = 1.25, so 1
     * step 4: add the day of the month to each step above: 31 + 6 + 5 + 1 = 43
     * step 5: add the month code (for jfmamjjasond: 144025036146): for october it is 1: 43 + 1 = 44
     * step 6: add the previous five numbers: (44) and mod by 7: 44%7 = 2 (44/7 = 6 remainder 2)
     * step 7: sat sun mon tue wed thu fri is 0 1 2 3 4 5 6; our 2 means Oct 31 1977 was monday
     * Extra notes:
     * a)for January/February dates in leap years, add 6 at the start
     * b)for all dates in the 2000s, add 6 at the start
     * c)for all dates in the 1800s, add 2 at the start
     *
     * @return the day of the week as a string (e.g., "Monday")
     * @throws IllegalArgumentException if the date is invalid
     */
    public String getDayOfTheWeek()
    {
        final int lastThreeNumbersOfTheYear;
        final int lastTwoNumbersOfTheYear;
        final int numberOfTwelves;
        final int remainderOfTwelves;
        final int numberOfFours;
        final int sumOfNumbersWithGivenDay;
        final int weekdayNumber;
        final int yearOffSet;
        final int adjustedYear;
        final String weekdayResult;

        Date date = new Date(year, month, day);

        /*
       TODO:
        maybe create a helper method to get the last 2 digits of the year.
        create a helper method to convert the weekday number to the actual weekday name
        these will help with organization and readability
         */

        if ((date.getYear() >= YEAR_TWO_THOUSAND && date.getYear() <= YEAR_TWO_THOUSAND_NINETY_NINE) ||
                (date.getYear() >= EARLIEST_BIRTH_YEAR && date.getYear() <= YEAR_EIGHTEEN_NINETY_NINE) ||
                (date.getYear() >= YEAR_NINETEEN_HUNDRED && date.getYear() <= YEAR_NINETEEN_NINETY_NINE))
        {
            // Adjust year based on the century
            if (date.getYear() >= YEAR_TWO_THOUSAND && date.getYear() <= YEAR_TWO_THOUSAND_NINETY_NINE) {
                yearOffSet = YEAR_OFFSET_2000;
            } else if (date.getYear() >= EARLIEST_BIRTH_YEAR && date.getYear() <= YEAR_EIGHTEEN_NINETY_NINE) {
                yearOffSet = YEAR_OFFSET_1800;
            } else {
                yearOffSet = 0;
            }

            adjustedYear = date.getYear();
            lastThreeNumbersOfTheYear = adjustedYear % CANCEL_OUT_MILLENIUM;
            lastTwoNumbersOfTheYear = lastThreeNumbersOfTheYear % CANCEL_OUT_CENTURY;
            numberOfTwelves = lastTwoNumbersOfTheYear / CALCULATE_NO_TWELVES;
            remainderOfTwelves = lastTwoNumbersOfTheYear % CALCULATE_NO_TWELVES;
            numberOfFours = remainderOfTwelves / LEAP_YEAR_CHECK_EVEN;
            sumOfNumbersWithGivenDay = day + numberOfTwelves + remainderOfTwelves + numberOfFours;
            weekdayNumber = (yearOffSet + sumOfNumbersWithGivenDay + getMonthCode1900(month)) % DAYS_IN_A_WEEK;

            if(weekdayNumber == SATURDAY)
            {
                weekdayResult = "Saturday";
            } else if(weekdayNumber == SUNDAY)
            {
                weekdayResult = "Sunday";
            } else if(weekdayNumber == MONDAY)
            {
                weekdayResult = "Monday";
            } else if(weekdayNumber == TUESDAY)
            {
                weekdayResult = "Tuesday";
            } else if(weekdayNumber == WEDNESDAY)
            {
                weekdayResult = "Wednesday";
            } else if(weekdayNumber == THURSDAY)
            {
                weekdayResult = "Thursday";
            } else if(weekdayNumber == FRIDAY)
            {
                weekdayResult = "Friday";
            } else {
                throw new IllegalArgumentException("That date is not valid.");
            }

        } else {
            throw new IllegalArgumentException("Invalid date format");
        }

        return weekdayResult;
    }

    /**
     * Converts the month number into it's month code for years in the 1900s.
     * Month codes are based on a pre-determined mapping used in date calculations.
     *
     * @param month the user inputted month.
     * @return the month code of an inputted month.
     * @throws IllegalArgumentException if the input month is not valid.
     */
    private int getMonthCode1900(int month)
    {
        switch (month) // month codes: 144025036146
        {
            case JANUARY:
                return JAN_CODE;
            case FEBRUARY:
                return FEB_CODE;
            case MARCH:
                return MARCH_CODE;
            case APRIL:
                return APR_CODE;
            case MAY:
                return MAY_CODE;
            case JUNE:
                return JUNE_CODE;
            case JULY:
                return JULY_CODE;
            case AUGUST:
                return AUG_CODE;
            case SEPTEMBER:
                return SEPT_CODE;
            case OCTOBER:
                return OCT_CODE;
            case NOVEMBER:
                return NOV_CODE;
            case DECEMBER:
                return DEC_CODE;
            default:
                throw new IllegalArgumentException("That date is not valid.");
        }
    }

    /**
     * Validates whether the user inputted year is valid.
     *
     * @param year year the user inputs
     * @param month month the user inputs
     * @param day day the user inputs
     * @return boolean true if day is valid, false if day is not valid
     */
    boolean validateUserBirthDate(final int year, final int month, final int day)
    {
        boolean result = false;

        switch(month)
        {
            case JANUARY, MARCH, MAY, JULY, AUGUST, OCTOBER, DECEMBER: // months with 31 days
                result = day <= ODD_MONTH_DAYS && day >= FIRST_DAY_OF_MONTH;
                break;
            case FEBRUARY: // months with 28 days
                if(isLeapYear(year))
                {
                    result = day <= FEBRUARY_LEAP_DAYS && day >= FIRST_DAY_OF_MONTH; // february during leap year
                    break;
                }
                result = day <= FEBRUARY_DAYS && day >= FIRST_DAY_OF_MONTH;
                break;
            case APRIL, JUNE, SEPTEMBER, NOVEMBER: // months with 30 days
                result = day <= EVEN_MONTH_DAYS && day >= FIRST_DAY_OF_MONTH;
                break;
            default:
                break; // returns false if the inputted month is not valid.
        }
        return result;
    }

    /**
     * Checks the user input year if it's a leap year.
     *
     * @param year the year the user inputs as their birth year.
     * @return boolean true if the year is a leap year, false if the year is not a leap year
     */
    private boolean isLeapYear(final int year)
    {
        boolean isLeapYear;

        isLeapYear = (year % LEAP_YEAR_CHECK_EVEN == CHECK_IF_EVEN &&
                year % CANCEL_OUT_CENTURY != CHECK_IF_EVEN) ||
                (year % FOUR_HUNDRED == CHECK_IF_EVEN);

        return isLeapYear;
    }
}