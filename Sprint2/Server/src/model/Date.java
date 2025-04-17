package model;
import java.io.Serializable;
/**
 * Represents a date with day, month, and year.
 * @author Group 6
 * @version 1.0
 */
public class Date implements Serializable
{
  /**
   * Instance variables.
   */
  private int day;
  private int month;
  private int year;

  /**
   * Three-argument constructor.
   * @param day the day.
   * @param month the month.
   * @param year the year.
   */
  public Date(int day, int month, int year)
  {
    this.year = year;
    this.month = month;
    this.day = day;
  }

  /**
   * Retrieves the day.
   * @return the day.
   */
  public int getDay()
  {
    return day;
  }

  /**
   * Retrieves the month.
   * @return the month.
   */
  public int getMonth()
  {
    return month;
  }

  /**
   * Retrieves the year.
   * @return the year.
   */
  public int getYear()
  {
    return year;
  }

  /**
   * Sets the day, month, and year.
   * @param day,month,year the day, month, and year.
   */
  public void setDayMonthYear(int day, int month, int year)
  {
    this.day = day;
    this.month = month;
    this.year = year;
  }

  /**
   * Checks if the date is before another date.
   * @param date the date to compare with.
   * @return true if this date is before the other date, false otherwise.
   */
  public boolean isBefore(Date date)
  {
    if (year < date.year)
    {
      return true;
    }
    else if (year == date.year)
    {
      if (month < date.month)
      {
        return true;
      }
      else if (month == date.month)
      {
        if (day < date.day)
        {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Checks if the date is after another date.
   * @param date the date to compare with.
   * @return true if this date is after the other date, false otherwise.
   */
  public boolean isAfter(Date date)
  {
    return !isBefore(date) && !equals(date);
  }

  /**
   * Checks if the year is a leap year.
   * @return true if the year is a leap year, false otherwise.
   */
  private boolean isLeapYear()
  {
    if (year % 4 == 0 && year % 100 != 0)
    {
      return true;
    }
    else
      return year % 400 == 0;
  }

  /**
   * Retrieves the number of days in the month.
   * @return the number of days in the month.
   */
  private int daysInMonth()
  {
    if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
        || month == 10 || month == 12)
    {
      return 31;
    }
    else if (month == 2)
    {
      if (isLeapYear())
      {
        return 29;
      }
      else
      {
        return 28;
      }
    }
    else if (month == 4 || month == 6 || month == 9 || month == 11)
    {
      return 30;
    }

    else
    {
      return -1;
    }
  }

  /**
   * Advances the date by one day.
   */
  private void nextDay()
  {
    day++;
    if (day > daysInMonth())
    {
      month++;
      day=1;
      if (month>12)
      {
        year++;
        month=1;
      }
    }
  }

  /**
   * Retrieves the number of days until another date.
   * @param endDate the end date.
   * @return the number of days until the end date.
   */
  public int until(Date endDate)
  {
    Date temp = copy();
    int countDays = 0;
    while (!temp.equals(endDate))
    {
      temp.nextDay();
      countDays++;
    }
    return countDays;
  }

  /**
   * Creates a copy of the date.
   * @return a copy of the date.
   */
  public Date copy()
  {
    return new Date(day, month, year);
  }

  /**
   * Retrieves the date as a string.
   * @return the date as a string.
   */
  public String toString()
  {
    return day + "/" + month + "/" + year;
  }

  /**
   * Checks if the date is equal to another date.
   * @param obj the date to compare with.
   * @return true if the dates are equal, false otherwise.
   */
  public boolean equals(Object obj)
  {
    if (obj == null || this.getClass() != obj.getClass())
    {
      return false;
    }
    Date other = (Date) obj;
    return day == other.day && month == other.month && year == other.year;
  }
}





