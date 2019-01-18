package com.mentor.uplc.utility;

import java.io.PrintStream;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate
{
  public Validate() {}
  
  public static boolean compareRowDates(int i, String aDateName, Date aDate, Date bDate)
  {
    boolean bool = true;
    
    if (aDate.before(bDate)) {
      ResourceUtil.addRowErrorMessage(i, aDateName, "notGraterThanCurrentDate");bool = false;return bool;
    }
    


    return bool;
  }
  
  public static boolean compareRowDatesConfirm(int i, String aDateName, Date aDate, Date bDate)
  {
    boolean bool = true;
    
    if (aDate.before(bDate)) {
      ResourceUtil.addRowErrorMessage(i, aDateName, "confirmDateValid");bool = false;return bool;
    }
    


    return bool;
  }
  
  public static boolean compareRowGreaterDates(int i, String aDateName, Date aDate, Date bDate, String bDateName)
  {
    boolean bool = true;
    String[] params = { ResourceUtil.getMessage(aDateName), ResourceUtil.getMessage(bDateName) };
    if (!aDate.before(bDate)) {
      ResourceUtil.addRowErrorMessage(i, "notGraterThanDate", params);bool = false;return bool;
    }
    


    return bool;
  }
  

  public static boolean compareRowLesserDates(int i, String aDateName, Date aDate, Date bDate, String bDateName)
  {
    boolean bool = true;
    String[] params = { ResourceUtil.getMessage(aDateName), ResourceUtil.getMessage(bDateName) };
    if (!aDate.after(bDate)) {
      ResourceUtil.addRowErrorMessage(i, "notLesserThanDate", params);bool = false;return bool;
    }
    


    return bool;
  }
  

  public static boolean validatePhoneLen(int strLength, String nameToCheck, String stringToCheck)
  {
    boolean bool = true;
    if ((stringToCheck.length() > strLength) || (stringToCheck.length() < 10))
    {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.length_exceed");bool = false;return bool;
    }
    
    return bool;
  }
  
  public static boolean validateCompareCurrentDates(String aDateName, Date aDate)
  {
    boolean bool = true;
    Date bDate = new Date();
    if (aDate != null)
    {
      if (aDate.after(bDate))
      {
        ResourceUtil.addErrorMessage(aDateName, "javax.faces.component.UIInput.compared_date");bool = false;return bool;
      }
    }
    return bool;
  }
  
  public static boolean validateAmount(String nameToCheck, double a, double amount)
  {
    boolean bool = false;
    
    if (amount < a) {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.NOTLESSTHANPERCENT");bool = true;return bool;
    }
    return bool;
  }
  

  public static boolean validateEmpApplicable(String nameToCheck, int a, int b)
  {
    boolean bool = false;
    
    if (b >= a) {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.NOTAPPLICABLEFORGPF");bool = true;return bool;
    }
    return bool;
  }
  

  public static boolean validateAmount1(String nameToCheck, double a, double amount)
  {
    boolean bool = false;
    
    if (amount > a) {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.NOTGREATERTHANBASIC");bool = true;return bool;
    }
    

    return bool;
  }
  

  public static boolean differenceDates(String aDateName, int year)
  {
    boolean bool = false;
    if (year < 3) {
      ResourceUtil.addErrorMessage(aDateName, "javax.faces.component.UIInput.YEARNOTLESSTHAN3");bool = true;return bool;
    }
    return bool;
  }
  

  public static boolean validateAdvanceAmount(String nameToCheck, double a, double amount)
  {
    boolean bool = false;
    
    if (amount < a) {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.NOTAPPLICABLEFORGPF");bool = true;return bool;
    }
    return bool;
  }
  
  public static boolean validateNoOfMonth(String id, int value)
  {
    if (value == 0) {
      ResourceUtil.addErrorMessage(id, "javax.faces.component.UIInput.REQUIRED");
      return true;
    }
    if (value > 36) {
      ResourceUtil.addErrorMessage(id, "javax.faces.component.UIInput.NOOFMONTH");
      return true;
    }
    return false;
  }
  
  public static boolean validateStrReq1(int rowNumber, String nameToCheck, String stringToCheck)
  {
    boolean bool = true;
    if ((stringToCheck == null) || (stringToCheck.trim() == "") || (stringToCheck.trim().length() == 0)) {
      ResourceUtil.addRowErrorMessage1(rowNumber, nameToCheck, "javax.faces.component.UIInput.REQUIRED");bool = false;return bool; }
    return bool;
  }
  

  public static boolean validateCombo1(int rowNumber, String nameToCheck, String stringToCheck)
  {
    boolean bool = true;
    if ((stringToCheck == null) || (stringToCheck.trim() == "") || (stringToCheck.trim().length() == 0)) {
      ResourceUtil.addRowErrorMessage1(rowNumber, nameToCheck, "javax.faces.component.UIInput.REQUIRED");bool = false;return bool; }
    return bool;
  }
  














  public static boolean validateAddRowRequiredDouble(int rowNumber, String id, Double value)
  {
    if (value == null)
    {
      ResourceUtil.addErrorMessage(id, "javax.faces.component.UIInput.REQUIRED");
      return false;
    }
    String str = value.toString();
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if (((c >= '0') && (c <= '9')) || (c == '.'))
      {
        if (value.doubleValue() <= 0.0D) {
          ResourceUtil.addErrorMessage(id, "javax.faces.component.UIInput.REQUIRED");
          return false;
        }
      }
    }
    return true;
  }
  


  public static boolean validateNoSpecialQualification(String nameToCheck, String stringToCheck)
  {
    boolean bool = true;
    

    if (checkSpecialQualification(stringToCheck)) {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.special");bool = false;return bool;
    }
    



    return bool;
  }
  

  private static boolean checkSpecialQualification(String categoryName)
  {
    boolean flag = false;
    for (int i = 0; i < categoryName.length(); i++)
    {
      char c = categoryName.charAt(i);
      if (((c > ' ') && (c <= '-')) || ((c >= ':') && (c <= '@')) || ((c >= '[') && (c <= '`')) || ((c >= '|') && (c <= 'ÿ')) || (c == '/')) flag = true;
    }
    return flag;
  }
  



  public static boolean validateDateAddRow(int rowNumber, String nameToCheck, Date aDate)
  {
    boolean bool = true;
    
    if (aDate == null) {
      ResourceUtil.addRowErrorMessage1(rowNumber, nameToCheck, "javax.faces.component.UIInput.REQUIRED_date");bool = false;return bool;
    }
    return bool;
  }
  




  public static boolean validateNoSpecialCharOneRow(int rowNumber, String nameToCheck, String stringToCheck)
  {
    boolean bool = true;
    

    if (checkSpecial(stringToCheck)) {
      ResourceUtil.addRowErrorMessage1(rowNumber, nameToCheck, "javax.faces.component.UIInput.special");bool = false;return bool;
    }
    



    return bool;
  }
  








  public static boolean validateNumberLen(int strLength, String nameToCheck, String stringToCheck)
  {
    boolean bool = true;
    if (stringToCheck.length() < strLength) {
      ResourceUtil.addErrorMessageSec(strLength, nameToCheck, "javax.faces.component.UIInput.length_less");bool = false;return bool; }
    if (stringToCheck.length() > strLength) {
      ResourceUtil.addErrorMessageSec(strLength, nameToCheck, "javax.faces.component.UIInput.length_exceed");bool = false;return bool;
    }
    return bool;
  }
  


  public static boolean validateStringLenInt(int strLength, String nameToCheck, int stringToCheck)
  {
    boolean bool = true;
    String pin = String.valueOf(stringToCheck);
    



    if (pin.length() > strLength) {
      ResourceUtil.addErrorMessageSec(strLength, nameToCheck, "javax.faces.component.UIInput.length_exceed");bool = false;return bool;
    }
    return bool;
  }
  




  public static boolean validateOnlyDouble(String nameToCheck, String stringToCheck)
  {
    boolean bool = true;
    
    if (!onlyDouble(stringToCheck)) {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.double");bool = false;return bool;
    }
    return bool;
  }
  

  private static boolean onlyDouble(String categoryName)
  {
    boolean flag = true;
    
    for (int i = 0; i < categoryName.length(); i++)
    {
      char c = categoryName.charAt(i);
      if (((c < '0') || (c > '9')) && (c != '.'))
      {

        flag = false; }
    }
    return flag;
  }
  






  public static boolean validateStringLen(int strLength, String nameToCheck, String stringToCheck)
  {
    boolean bool = true;
    



    if (stringToCheck.length() > strLength) {
      ResourceUtil.addErrorMessageSec(strLength, nameToCheck, "javax.faces.component.UIInput.length_exceed");bool = false;return bool;
    }
    return bool;
  }
  
  public static boolean validateStringLenMinimum(int strLength, String nameToCheck, String stringToCheck)
  {
    boolean bool = true;
    



    if (stringToCheck.length() < strLength) {
      ResourceUtil.addErrorMessageSec(strLength, nameToCheck, "javax.faces.component.UIInput.length_should_be");bool = false;return bool;
    }
    return bool;
  }
  

  public static boolean validateStrReq(String nameToCheck, String stringToCheck)
  {
    boolean bool = true;
    if ((stringToCheck == null) || (stringToCheck.trim() == "") || (stringToCheck.trim().length() == 0)) {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.REQUIRED");bool = false;return bool; }
    return bool;
  }
  

  public static boolean validateInteger(String nameToCheck, int stringToCheck)
  {
    boolean bool = true;
    if (stringToCheck == 0) {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.REQUIRED");bool = false;return bool; }
    return bool;
  }
  

  public static boolean validateText1(String nameToCheck, String stringToCheck)
  {
    boolean bool = true;
    

    if (stringToCheck.length() <= 2) {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.length_less");bool = false;return bool; }
    if (stringToCheck.length() > 200) {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.length_exceed");bool = false;return bool;
    }
    return bool;
  }
  












  public static boolean validateCombo(String nameToCheck, String stringToCheck)
  {
    boolean bool = true;
    if ((stringToCheck == null) || (stringToCheck.trim() == "") || (stringToCheck.trim().length() == 0)) {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.SELECTBOX");bool = false;return bool; }
    return bool;
  }
  









  public static boolean validateText(String nameToCheck, String stringToCheck)
  {
    boolean bool = true;
    if ((stringToCheck == null) || (stringToCheck.trim() == "") || (stringToCheck.trim().length() == 0)) {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.REQUIRED");bool = false;return bool;
    }
    if (stringToCheck.length() <= 2) {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.length_less");bool = false;return bool; }
    if (stringToCheck.length() > 200) {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.length_exceed_text");bool = false;return bool;
    }
    return bool;
  }
  









  public static boolean validateOnlyInt(String nameToCheck, String stringToCheck)
  {
    boolean bool = true;
    
    if (!onlyInteger(stringToCheck)) {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.int");bool = false;return bool;
    }
    return bool;
  }
  











  public static boolean validateNoSpecialChar(String nameToCheck, String stringToCheck)
  {
    boolean bool = true;
    


    if (checkSpecial(stringToCheck)) {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.special");bool = false;return bool;
    }
    




    return bool;
  }
  

  public static boolean validateNoSpecialCharNew(String nameToCheck, String stringToCheck)
  {
    boolean bool = true;
    


    if (checkSpecialNew(stringToCheck)) {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.special");bool = false;return bool;
    }
    




    return bool;
  }
  






  public static boolean validateDate(String nameToCheck, Date aDate)
  {
    boolean bool = true;
    
    if (aDate == null) {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.REQUIRED_date");bool = false;return bool;
    }
    return bool;
  }
  







  private static boolean checkNumber(String categoryName)
  {
    boolean flag = false;
    for (int i = 0; i < categoryName.length(); i++)
    {
      char c = categoryName.charAt(i);
      if ((c >= '0') && (c <= '9')) flag = true;
    }
    return flag;
  }
  
















  private static boolean checkSpecial(String categoryName)
  {
    boolean flag = false;
    for (int i = 0; i < categoryName.length(); i++)
    {
      char c = categoryName.charAt(i);
      
      if (((Character.getType(c) != 23) && (c > ' ') && (c <= '/')) || (Character.isWhitespace(c)) || ((Character.getType(c) != 23) && (c >= ':') && (c <= '@')) || ((Character.getType(c) != 23) && (c >= '[') && (c <= '`')) || ((Character.getType(c) != 23) && (c >= '|') && (c <= 'ÿ'))) flag = true;
    }
    return flag;
  }
  
  private static boolean checkSpecialNew(String categoryName) {
    boolean flag = false;
    for (int i = 0; i < categoryName.length(); i++)
    {
      char c = categoryName.charAt(i);
      
      if (((Character.getType(c) != 23) && (c > ' ') && (c <= '-')) || ((Character.getType(c) == 46) && (c != '/')) || ((Character.getType(c) != 23) && (c >= ':') && (c <= '@')) || ((Character.getType(c) != 23) && (c >= '[') && (c <= '`')) || ((Character.getType(c) != 23) && (c >= '|') && (c <= 'ÿ'))) flag = true;
    }
    return flag;
  }
  







  public static boolean onlyInteger(String categoryName)
  {
    boolean flag = true;
    for (int i = 0; i < categoryName.length(); i++)
    {
      char c = categoryName.charAt(i);
      if ((c < '0') || (c > '9')) flag = false;
    }
    return flag;
  }
  






  public static void validateCheckbox()
  {
    ResourceUtil.addErrorMessage("Checkbox", "javax.faces.component.UIInput.checkbox");
  }
  
  public static boolean validateOnlyInt(String nameToCheck, int a)
  {
    boolean bool = true;
    if (a == 0) {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.int");bool = false;return bool; }
    if (a <= 0) {
      ResourceUtil.addErrorMessage(nameToCheck, "onlyPositive");
      bool = false;
      return bool;
    }
    return bool;
  }
  
  public static boolean validateRequired(String id, String value)
  {
    if ((value == null) || (value.trim() == null) || (value.length() == 0)) {
      ResourceUtil.addErrorMessage(id, "javax.faces.component.UIInput.REQUIRED");
      return true;
    }
    return false;
  }
  
  public static boolean validatedouble(String id, Double value) {
    if (value == null) {
      ResourceUtil.addErrorMessage(id, "javax.faces.component.UIInput.REQUIRED");
      return true;
    }
    if (value.doubleValue() <= 0.0D) {
      ResourceUtil.addErrorMessage(id, "javax.faces.component.UIInput.REQUIRED");
      return true;
    }
    return false;
  }
  

  public static boolean validateSelectBox(String nameToCheck, String stringToCheck)
  {
    boolean bool = true;
    if (stringToCheck.equals("")) {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.SELECTBOX");bool = false;return bool; }
    return bool;
  }
  


  public static boolean validateDouble(String nameToCheck, double bookPrice)
  {
    boolean bool = true;
    if (bookPrice == 0.0D) {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.REQUIRED");
      bool = false;
      return bool;
    }
    return bool;
  }
  
  public static boolean validateString1(String nameToCheck, String stringToCheck)
  {
    boolean bool = true;
    if ((stringToCheck == null) || (stringToCheck.trim() == "") || (stringToCheck.trim().length() == 0)) {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.REQUIRED");bool = false;return bool;
    }
    return bool;
  }
  
  public static boolean validateRequired1(String id, int value)
  {
    if (value == 0) {
      ResourceUtil.addErrorMessage(id, "javax.faces.component.UIInput.REQUIRED");
      return false;
    }
    return true;
  }
  
  public static boolean validateCompareDates(String aDateName, Date aDate, String bDateName, Date bDate)
  {
    boolean bool = true;
    
    String[] params = { ResourceUtil.getMessage(aDateName), ResourceUtil.getMessage(bDateName) };
    

    if (aDate.after(bDate)) {
      ResourceUtil.addErrorMessage(aDateName, "javax.faces.component.UIInput.compared_date_dynamic", params);
      bool = false;
      return bool;
    }
    return bool;
  }
  
  public static boolean validateCompareDatesAddRow(int i, String aDateName, Date aDate, String bDateName, Date bDate)
  {
    boolean bool = true;
    
    String[] params = { ResourceUtil.getMessage(aDateName), ResourceUtil.getMessage(bDateName) };
    

    if (aDate.after(bDate)) {
      ResourceUtil.addErrorMessageRow(i, aDateName, "javax.faces.component.UIInput.compared_time", params);
      bool = false;
      return bool;
    }
    return bool;
  }
  
  public static boolean validateDouble(String nameToCheck, Double a)
  {
    boolean bool = true;
    if (a == null) {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.REQUIRED");bool = true;return bool;
    }
    if (a.doubleValue() <= 0.0D) {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.REQUIRED");bool = true;return bool;
    }
    return bool;
  }
  

  public static boolean validateOnlyInt(String nameToCheck, Integer a)
  {
    boolean bool = true;
    if (a == null) {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.REQUIRED");bool = false;return bool;
    }
    
    if (a.intValue() <= 0) {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.REQUIRED");bool = false;return bool; }
    return bool;
  }
  

  public static boolean validateDate1(String nameToCheck, Date stringToCheck)
  {
    boolean bool = true;
    
    if (stringToCheck == null) {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.REQUIRED_date");bool = false;return bool;
    }
    return bool;
  }
  

  public static boolean validatePercentage(String str, double rate)
  {
    boolean boll = true;
    




    if (rate == 0.0D) {
      ResourceUtil.addErrorMessage(str, "javax.faces.component.UIInput.REQUIRED");
      boll = false;
    }
    return boll;
  }
  
  public static boolean validateMaxLength(String nameToCheck, String stringToCheck, int length)
  {
    boolean bool = true;
    if (stringToCheck.length() > length) {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.length_exceed");bool = false;return bool; }
    return bool;
  }
  




  public static boolean validateTextChar(String stringToCheck, String categoryName)
  {
    boolean flag = false;
    int countChar = 0;
    for (int i = 0; i < categoryName.length(); i++)
    {
      char c = categoryName.charAt(i);
      if ((c >= '0') && (c <= '9')) {
        countChar++;
        flag = false;
      }
    }
    


    if (countChar > 0)
    {
      ResourceUtil.addErrorMessage(stringToCheck, "javax.faces.component.UIInput.only_text");
    }
    
    if (countChar == 0)
    {
      flag = true;
    }
    

    return flag;
  }
  




  public static boolean compareDate(String aDateName, Date aDate, String bDateName, Date bDate)
  {
    boolean bool = true;
    String[] params = { ResourceUtil.getMessage(aDateName), ResourceUtil.getMessage(bDateName) };
    
    try
    {
      if (aDate.after(bDate)) {
        ResourceUtil.addErrorMessage(aDateName, "javax.faces.component.UIInput.compared_date", params);return false;
      }
    } catch (Exception e) { e.printStackTrace(); }
    return bool;
  }
  
  public static boolean compareDateFrom(String aDateName, Date aDate, String bDateName, Date bDate)
  {
    boolean bool = true;
    int aYear = aDate.getYear();
    int bYear = bDate.getYear();
    Date frmDate = new Date(aYear, 3, 1);
    Date toDate = new Date(bYear, 2, 31);
    
    System.out.println("frmDate ::" + frmDate + " :: " + toDate);
    try {
      if ((!aDate.equals(frmDate)) && (!bDate.equals(toDate))) {
        ResourceUtil.addErrorMessage(aDateName, "financialYear");return false;
      }
    } catch (Exception e) {
      e.printStackTrace(); }
    return bool;
  }
  

















  public static boolean compareCurrentYear(int i, String nameToCheck, String aYear)
  {
    boolean bool = true;
    Date bDate = new Date();
    
    int c = Integer.parseInt(aYear);
    Calendar cal = Calendar.getInstance();
    


    cal.setTime(bDate);
    

    int iYear = cal.get(1);
    





    return bool;
  }
  
  public static boolean validateStrEdit(String nameToCheck, String stringToCheck)
  {
    boolean bool = true;
    if ((stringToCheck == null) || (stringToCheck.trim() == "") || (stringToCheck.trim().length() == 0)) {
      ResourceUtil.addErrorMessage(nameToCheck, "editMode");bool = false;return bool; }
    return bool;
  }
  

  public static boolean compareYearNumber(String nameToCheck, int year)
  {
    boolean bool = true;
    if (year == 0)
    {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.int");bool = false;return bool;
    }
    
    if (year <= 0) {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.int");bool = false;return bool;
    }
    if (year < 2000)
    {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.trainingCompYear");
      bool = false;
      return bool;
    }
    
    return bool;
  }
  




  public static boolean validateStrNotInteger(String nameToCheck, String stringToCheck)
  {
    boolean bool = true;
    if (checkNumber(stringToCheck)) {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.digit");bool = false;return bool; }
    return bool;
  }
  
  public static boolean validateNoSpecialCharOne(String nameToCheck, String stringToCheck) {
    boolean bool = true;
    

    if (checkSpecial(stringToCheck)) {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.special");bool = false;return bool;
    }
    



    return bool;
  }
  
  public static boolean validateStrReqRow(int i, String nameToCheck, String stringToCheck)
  {
    boolean bool = true;
    if ((stringToCheck == null) || (stringToCheck.trim() == "") || (stringToCheck.trim().length() == 0)) {
      ResourceUtil.addRowErrorMessage(i, nameToCheck, "javax.faces.component.UIInput.REQUIRED");bool = false;return bool; }
    return bool;
  }
  
  public static boolean validateStringLenRow(int i, int strLength, String nameToCheck, String stringToCheck) {
    boolean bool = true;
    
    if (stringToCheck.length() > strLength) {
      ResourceUtil.addRowErrorMessageSec(i, strLength, nameToCheck, "javax.faces.component.UIInput.length_exceed");bool = false;return bool;
    }
    return bool;
  }
  

  public static boolean validateDateRow(int i, String nameToCheck, Date aDate)
  {
    boolean bool = true;
    
    if (aDate == null) {
      ResourceUtil.addRowErrorMessage(i, nameToCheck, "javax.faces.component.UIInput.REQUIRED_date");bool = false;return bool;
    }
    return bool;
  }
  

  public static boolean validateStrReqnSpec(String nameToCheck, String stringToCheck)
  {
    boolean bool = true;
    if ((stringToCheck == null) || (stringToCheck.trim() == "") || (stringToCheck.trim().length() == 0))
    {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.REQUIRED");bool = false;return bool;
    }
    
    if (checkSpecial(stringToCheck))
    {
      ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.special");bool = false;return bool;
    }
    return bool;
  }
  


















  public static boolean compareDatesToday(String aDateName, Date aDate)
  {
    boolean bool = true;
    Date bDate = new Date();
    if (aDate.after(bDate)) {
      ResourceUtil.addErrorMessage(aDateName, "javax.faces.component.UIInput.compared_date");bool = false;return bool;
    }
    


    return bool;
  }
  

  public static boolean validateComboRow(int i, String nameToCheck, String stringToCheck)
  {
    boolean bool = true;
    if ((stringToCheck == null) || (stringToCheck.trim() == "") || (stringToCheck.trim().length() == 0)) {
      ResourceUtil.addRowErrorMessage(i, nameToCheck, "javax.faces.component.UIInput.SELECTBOX");bool = false;return bool; }
    return bool;
  }
  
  public static boolean currentCompareDatesOnly(String aDateName, Date aDate)
  {
    boolean bool = true;
    
    Date bDate = new Date();
    










    return bool;
  }
  

  public static boolean emailFieldValidate(String str)
  {
    Pattern pattern = null;
    Matcher matcher = null;
    boolean flag = true;
    
    pattern = Pattern.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    
    String email = str;
    
    matcher = pattern.matcher(email);
    



    return matcher.matches();
  }
  
  public static boolean validateStrReqEmail(String nameToCheck, String stringToCheck)
  {
    boolean bool = true;
    if (!emailFieldValidate(stringToCheck))
    {
      ResourceUtil.addErrorMessage(nameToCheck, "inValidEmail");bool = false;return bool;
    }
    return bool;
  }
  

  public static boolean validateDateOfBirth(String nameToCheck, Date aDate)
  {
    boolean bool = true;
    

    Date birthDay = null;
    Date now = new Date();
    try {
      birthDay = aDate;
      
      Calendar c1 = Calendar.getInstance();
      Calendar c2 = Calendar.getInstance();
      c1.setTime(now);
      c2.setTime(now);
      c1.add(1, -50);
      c2.add(1, -10);
      if (c1.getTime().getTime() >= birthDay.getTime())
      {
        ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.birthdayValidate");return false;
      }
      if (c2.getTime().getTime() <= birthDay.getTime())
      {

        ResourceUtil.addErrorMessage(nameToCheck, "javax.faces.component.UIInput.birthdayValidate");return false;
      }
    }
    catch (Exception pe) {
      System.out.println("Error parsing date");
    }
    return bool;
  }
}