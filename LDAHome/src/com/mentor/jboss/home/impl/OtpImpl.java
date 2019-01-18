package com.mentor.jboss.home.impl;

import com.mentor.jboss.home.action.OTPAction;
import com.mentor.jboss.home.datatable.CustomHomeDataTable;
import com.mentor.uplc.connectdb.ConnectionToDataBase;
import com.mentor.uplc.utility.ResourceUtil;
import com.mentor.uplc.utility.Sender;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OtpImpl
{
  public OtpImpl() {}
  
  public ArrayList<CustomHomeDataTable> displayMenuListotp(OTPAction action)
  {
    ArrayList<CustomHomeDataTable> list = new ArrayList();
    CustomHomeDataTable dto = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;
    Connection conn = ConnectionToDataBase.getConnection();
    
    try
    {
      String contxtURL = null;
      
      String mainContxt = "./auth/portal/";
      String getUserRole = "SELECT DISTINCT B.JBP_NAME FROM JBP_USERS A,   JBP_ROLE_MEMBERSHIP C   LEFT OUTER JOIN JBP_ROLES B ON   B.JBP_RID = C.JBP_RID  WHERE C.JBP_UID = A.JBP_UID  AND A.JBP_UNAME = ? ";
      




      String userRoles = " WHERE A.ROLE IN( ";
      pstm = conn.prepareStatement(getUserRole);
      pstm.setString(1, ResourceUtil.getUserNameAllReq());
      rs = pstm.executeQuery();
      while (rs.next())
        userRoles = userRoles + "'" + rs.getString("JBP_NAME") + "', ";
      ArrayList<CustomHomeDataTable> localArrayList1;
      if ((userRoles != null) && (userRoles.length() > 19)) {
        userRoles = userRoles + "'0')";
      } else {
        userRoles = "";
        System.out.println(" No Role found for this user STEP 1 " + userRoles);
        localArrayList1 = list;return localArrayList1;
      }
      
      String jointSql = " AND A.PK IN(";
      String queryMain = " SELECT DISTINCT A.NODE_KEY FROM  JBP_OBJECT_NODE_SEC A " + 
        userRoles;
      pstm = conn.prepareStatement(queryMain);
      rs = pstm.executeQuery();
      while (rs.next()) {
        jointSql = jointSql + rs.getString("NODE_KEY") + ", ";
      }
      if ((jointSql != null) && (jointSql.length() > 14)) {
        jointSql = jointSql + "0)";
      } else {
        jointSql = "";
        System.out.println(" NO Menu found for this user STEP 2 " + jointSql);
        localArrayList1 = list;return localArrayList1;
      }
      
      String query = " SELECT DISTINCT A.VCH_H_NAME, A.NAME,A.PATH, A.ICON_IMG,A.PK FROM  JBP_OBJECT_NODE A WHERE A.PARENT_KEY = 1   AND A.PK=6212 AND A.ICON_IMG is not null  " + 
      
        jointSql + 
        " ORDER BY A.NAME ASC ";
      pstm = conn.prepareStatement(query.toUpperCase());
      rs = pstm.executeQuery();
      while (rs.next()) {
        dto = new CustomHomeDataTable();
        
        contxtURL = rs.getString("PATH").replace(".", "/");
        contxtURL = contxtURL.replace(" ", "+");
        dto.setCellDisplay1(rs.getString("NAME"));
        dto.setLinkIconotp1("/icons/" + rs.getString("ICON_IMG") + "_E.png");
        
        dto.setLinkURLotp1(mainContxt + contxtURL);
        dto.setCellRenderotp1(true);
        
        list.add(dto);
      }
      action.setOtpflg(true);
    }
    catch (SQLException e) {
      e.printStackTrace();
      

      if (pstm != null)
        try {
          pstm.close();
        }
        catch (SQLException e1) {
          e1.printStackTrace();
        }
      if (rs != null)
        try {
          rs.close();
        }
        catch (SQLException e1) {
          e1.printStackTrace();
        }
      if (conn != null) {
        try {
          conn.close();
        }
        catch (SQLException e1) {
          e1.printStackTrace();
        }
      }
    }
    finally
    {
      if (pstm != null)
        try {
          pstm.close();
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      if (rs != null)
        try {
          rs.close();
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      if (conn != null)
        try {
          conn.close();
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
    }
    System.out.println("List Size : ==>  : " + list.size());
    return list;
  }
  
  public void getAndSendMessage()
  {
    PreparedStatement pstmt = null;PreparedStatement pstmt2 = null;
    Connection conn = null;
    ResultSet rs = null;
    String msgid = "";
    String to_num = "";String to_name = "";
    String msg_str = "";
    int status = 0;
    String otp = "0";
    try
    {
      conn = ConnectionToDataBase.getConnection2();
      String queryList = "SELECT INT_EMPLOYEE_ID_P,VCH_EMPLOYEE_NM,INT_CURR_MOBILE_NO FROM payroll.ESTB_M_EMPLOYEE_DETAIL WHERE INT_EMPLOYEE_ID_P=" + ResourceUtil.getUserNameAllReq();
      
      pstmt = conn.prepareStatement(queryList);
      rs = pstmt.executeQuery();
      if (rs.next())
      {
        msg_str = rs.getString(1);
        to_num = rs.getString(3);
        to_name = rs.getString(2); }
      rs.close();
      String selectmax = "SELECT ACCOUNTS.INT_MSG_ID_SEQ.NEXTVAL FROM DUAL";
      pstmt = conn.prepareStatement(selectmax);
      rs = pstmt.executeQuery();
      if (rs.next())
      {
        msgid = rs.getString(1);
      }
      rs.close();
      otp = password();
      String query = 
        " MERGE INTO ACCOUNTS.COMMON_SMS_DB  T USING (  SELECT A.INT_EMPLOYEE_ID_P  FROM   PAYROLL.ESTB_M_EMPLOYEE_DETAIL  A  WHERE A.INT_EMPLOYEE_ID_P =" + 
        
        msg_str + "  ) S ON (T.VCH_TO_EMP_ID = S.INT_EMPLOYEE_ID_P AND T.VCH_SENT_STATUS='SENT' and T.VCH_MODULE_NAME='PROPERTY') " + 
        " WHEN MATCHED THEN " + 
        " UPDATE  SET T.INT_RETRY_NO ='" + otp + "' ,T.OTPTIME=CURRENT_TIMESTAMP   WHEN NOT MATCHED THEN " + 
        " INSERT  (INT_MSG_ID,VCH_MODULE_NAME,VCH_MOB_NO,VCH_TO_EMP_ID,VCH_TO_NAME,VCH_MSG,DAT_SUBMITION,   " + 
        " VCH_SENT_STATUS,INT_RETRY_NO,OTPTIME) VALUES(?,?,?,?,?,?,?,?,?,CURRENT_TIMESTAMP) ";
      




      pstmt = conn.prepareStatement(query);
      pstmt.setString(1, msgid);
      pstmt.setString(2, "PROPERTY");
      pstmt.setString(3, to_num);
      pstmt.setString(4, msg_str);
      pstmt.setString(5, to_name);
      pstmt.setString(6, "Your OTP for PROPERTY login is :" + otp);
      pstmt.setDate(7, com.mentor.uplc.utility.Utility.convertUtilDateToSQLDate(new java.util.Date()));
      pstmt.setString(8, "SENT");
      pstmt.setString(9, otp);
      status = pstmt.executeUpdate();
      
      if (status > 0) {
        System.out.println("oooooo");
        status = 0;
        String to = to_num.trim();
        
        String temp = "Dear User,";
        Sender s = null;
        
        String msg = "Your OTP for PROPERTY login is :" + otp;
        
        String SMS_USER_ID = "infotechmentor";
        String SMS_USER_PWD = "mentor@1234";
        String SMS_SERVER_ADD = "http://192.0.1.50:8080";
        String SMS_FROM_NAME = "LDAONL";
        String sentstatus = "";
        




        s = new Sender(SMS_SERVER_ADD, SMS_USER_ID, SMS_USER_PWD, to, SMS_FROM_NAME, msg);
        


        sentstatus = s.submitMessage();
      }
      

    }
    catch (Exception e)
    {
      e.printStackTrace();
      


      try
      {
        if (pstmt != null) pstmt.close();
        if (conn != null) conn.close();
        if (rs != null) rs.close();
      }
      catch (Exception e1)
      {
        e1.printStackTrace();
      }
    }
    finally
    {
      try
      {
        if (pstmt != null) pstmt.close();
        if (conn != null) conn.close();
        if (rs != null) rs.close();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }
  
  public boolean validateotp(String ot)
  {
    PreparedStatement pstmt = null;PreparedStatement pstmt2 = null;
    Connection conn = null;
    ResultSet rs = null;
    String msgid = "";
    String to_num = "";String to_name = "";
    String msg_str = "";
    boolean status = false;
    try
    {
      conn = ConnectionToDataBase.getConnection2();
      
      String selectmax = "SELECT distinct INT_RETRY_NO,case when OTPTIME + interval '30' minute <CURRENT_TIMESTAMP   then 1 else 2 end as code from ACCOUNTS.COMMON_SMS_DB where VCH_SENT_STATUS='SENT' and VCH_MODULE_NAME='PROPERTY'  and VCH_TO_EMP_ID=" + 
        ResourceUtil.getUserNameAllReq();
      pstmt = conn.prepareStatement(selectmax);
      rs = pstmt.executeQuery();
      if (rs.next())
      {
        if ((rs.getString(1).equalsIgnoreCase(ot)) && (rs.getInt("code") == 2)) {
          status = true;
        }
      }
      rs.close();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      


      try
      {
        if (pstmt != null) pstmt.close();
        if (conn != null) conn.close();
        if (rs != null) rs.close();
      }
      catch (Exception e1)
      {
        e1.printStackTrace();
      }
    }
    finally
    {
      try
      {
        if (pstmt != null) pstmt.close();
        if (conn != null) conn.close();
        if (rs != null) rs.close();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    return status;
  }
  













  public String password()
  {
    int noOfCAPSAlpha = 0;
    int noOfDigits = 5;
    int noOfSplChars = 0;
    int minLen = 5;
    int maxLen = 6;
    

    char[] pswd = com.mentor.uplc.utility.RandomPasswordGenerator.generatePswd(minLen, maxLen, 
      noOfCAPSAlpha, noOfDigits, noOfSplChars);
    System.out.println("Len = " + pswd.length + ", " + new String(pswd));
    

    return new String(pswd);
  }
}