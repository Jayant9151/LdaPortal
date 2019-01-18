package com.mentor.uplc.utility;

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Utility
{
  public Utility() {}
  
  public static java.util.Date convertDateAsMDYYYFormat(java.sql.Date date)
  {
    try
    {
      if (date != null) {
        SimpleDateFormat sdf = new SimpleDateFormat("M/d/yy");
        sdf.setLenient(false);
        
        String dateString = sdf.format(date);
        
        java.util.Date dt = sdf.parse(dateString);
        return new java.sql.Date(dt.getTime());
      }
      
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  
  public static int differenceBetweenTwoDate(Calendar d1, Calendar d2) { int days = 0;
    

    try
    {
      if (d1.after(d2)) {
        Calendar swap = d1;
        d1 = d2;
        d2 = swap;
      }
      days = d2.get(6) - 
        d1.get(6);
      int y2 = d2.get(1);
      if (d1.get(1) != y2)
      {
        d1 = (Calendar)d1.clone();
        do {
          days += d1.getActualMaximum(6);
          d1.add(1, 1);
        } while (d1.get(1) != y2);
      }
    }
    catch (Exception e)
    {
      System.out.println("Exception=" + e.getMessage());
    }
    return days;
  }
  







  public static String convertDateAsCalender(java.sql.Date date)
  {
    String dateString = "";
    try {
      SimpleDateFormat formatter = new SimpleDateFormat(
        "MMMM d, yyyy");
      dateString = formatter.format(date);
    } catch (Exception e) {
      System.out.println("Exception=" + e.getMessage());
    }
    return dateString;
  }
  





  public static java.sql.Date convertDateAsDBFormat(String date)
  {
    try
    {
      if (!isNullOrEmpty(date)) {
        SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy");
        sdf.setLenient(false);
        java.util.Date dt = sdf.parse(date);
        return new java.sql.Date(dt.getTime());
      }
      
    }
    catch (ParseException e)
    {
      e.printStackTrace();
    }
    return null;
  }
  






  public static boolean isNullOrEmpty(String text)
  {
    if (text != null) {
      if (text.trim().length() == 0)
        return true;
      return false;
    }
    return true;
  }
  







  public static java.util.Date dateStringToUtilDate(String date)
    throws ParseException
  {
    java.util.Date dt = null;
    if (!isNullOrEmpty(date)) {
      SimpleDateFormat dtFormat = new SimpleDateFormat(
        "MMMM d, yyyy");
      dt = dtFormat.parse(date);
    }
    return dt;
  }
  






  public static boolean isStartDateBeforeEndDate(String startDate, String endDate)
  {
    try
    {
      java.util.Date startTime = dateStringToUtilDate(startDate);
      java.util.Date endTime = dateStringToUtilDate(endDate);
      if (!startTime.before(endTime))
        return false;
    } catch (ParseException pe) {
      System.out.println("ParseException=" + pe.getMessage());
    } catch (Exception e) {
      System.out.println("Exception=" + e.getMessage());
    }
    return true;
  }
  







  public static String getYear(String date)
    throws ParseException
  {
    if (!isNullOrEmpty(date))
    {
      java.sql.Date date1 = convertDateAsDBFormat(date);
      java.util.StringTokenizer st = new java.util.StringTokenizer(date1.toString(), "-");
      return st.nextToken();
    }
    
    return null;
  }
  






  public static boolean isCalendarDate(String strDate, int fyear)
  {
    boolean bool = true;
    try {
      java.util.Date startTime = dateStringToUtilDate(strDate);
      
      java.util.Date fyearStartDate = dateStringToUtilDate("January 1, " + fyear);
      java.util.Date fyearEndDate = dateStringToUtilDate("December 31, " + fyear);
      
      if ((startTime.before(fyearStartDate)) || (startTime.after(fyearEndDate)))
      {
        bool = false;
      }
    }
    catch (ParseException pe)
    {
      System.out.println("ParseException=" + pe.getMessage());
    } catch (Exception e) {
      System.out.println("Exception=" + e.getMessage());
    }
    return bool;
  }
  




  public static java.sql.Date convertUtilDateToSQLDate(java.util.Date date)
  {
    if (date != null) {
      java.sql.Date d1 = new java.sql.Date(date.getTime());
      return d1;
    }
    return null;
  }
  



  public static String getTime()
  {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("MMMM d, yyyy HH:mm:ss");
    return sdf.format(cal.getTime());
  }
  





  public static String convertDateAsCalenderShort(java.sql.Date date)
  {
    String dateString = "";
    try {
      SimpleDateFormat formatter = new SimpleDateFormat(
        "MMM d, yyyy");
      dateString = formatter.format(date);
    } catch (Exception e) {
      System.out.println("Exception=" + e.getMessage());
    }
    return dateString;
  }
  




  public static java.util.Date convertSqlDateToUtilDate(java.sql.Date date)
  {
    try
    {
      if (date != null) {
        return new java.util.Date(date.getTime());
      }
    } catch (Exception e) {
      System.out.println("Exception=" + e.getMessage());
    }
    return null;
  }
  
  public static String convertDateToStringWithddMMYYYFormat(java.sql.Date date)
  {
    try {
      if (date != null) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(convertSqlDateToUtilDate(date));
      }
    }
    catch (Exception e) {
      System.out.println("Exception=" + e.getMessage());
    }
    return null;
  }
  
  public static String convertDateToStringWithddMMYYYFormat(java.util.Date date)
  {
    try {
      if (date != null) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(date);
      }
    }
    catch (Exception e) {
      System.out.println("Exception=" + e.getMessage());
    }
    return null;
  }
}