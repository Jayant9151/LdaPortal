package com.mentor.jboss.home.common;

import com.mentor.uplc.connectdb.ConnectionToDataBase;
import com.mentor.uplc.utility.ResourceUtil;
import com.mentor.uplc.utility.Utility;
import java.io.PrintStream;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.apache.catalina.connector.RequestFacade;
import org.jboss.portal.portlet.impl.jsr168.api.ActionRequestImpl;
import org.jboss.portal.portlet.impl.jsr168.api.RenderRequestImpl;



public class CommonImpl
{
  public CommonImpl() {}
  
  public static String getSendSmsUser()
  {
    String user = "";
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    try
    {
      String query = "SELECT D.VCH_SMS_USER FROM  PROPERTY.COMMON_EMAIL_ACTION D  ";
      

      conn = ConnectionToDataBase.getConnection2();
      pstmt = conn.prepareStatement(query);
      
      rs = pstmt.executeQuery();
      
      while (rs.next())
      {
        user = rs.getString(1);
      }
      

    }
    catch (Exception e)
    {
      e.printStackTrace();
      


      try
      {
        if (pstmt != null) pstmt.close();
        if (conn != null) { conn.close();
        }
        
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
        if (conn != null) { conn.close();
        }
        
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    
    return user;
  }
  



  public static String getSendSmsUserPassword()
  {
    String user = "";
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    

    try
    {
      String query = "SELECT D.VCH_SMS_PWD FROM  PROPERTY.COMMON_EMAIL_ACTION D  ";
      

      conn = ConnectionToDataBase.getConnection2();
      pstmt = conn.prepareStatement(query);
      
      rs = pstmt.executeQuery();
      
      while (rs.next())
      {
        user = rs.getString(1);
      }
      

    }
    catch (Exception e)
    {
      e.printStackTrace();
      


      try
      {
        if (pstmt != null) pstmt.close();
        if (conn != null) { conn.close();
        }
        
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
        if (conn != null) { conn.close();
        }
        
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    
    return user;
  }
  



  public static String getSendSmsFrom()
  {
    String user = "";
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    

    try
    {
      String query = "SELECT D.VCH_SMS_FROM FROM  PROPERTY.COMMON_EMAIL_ACTION D  ";
      

      conn = ConnectionToDataBase.getConnection2();
      pstmt = conn.prepareStatement(query);
      
      rs = pstmt.executeQuery();
      
      while (rs.next())
      {
        user = rs.getString(1);
      }
      

    }
    catch (Exception e)
    {
      e.printStackTrace();
      


      try
      {
        if (pstmt != null) pstmt.close();
        if (conn != null) { conn.close();
        }
        
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
        if (conn != null) { conn.close();
        }
        
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    
    return user;
  }
  

  public static String serverIpAddressWithPort()
  {
    String url = "";
    RenderRequestImpl requestRR = null;
    ActionRequestImpl requestAR = null;
    RequestFacade requestFacade = null;
    try {
      Object request1 = FacesContext.getCurrentInstance().getExternalContext().getRequest();
      String thisIp = InetAddress.getLocalHost().getHostName();
      
      if ((request1 instanceof RenderRequestImpl))
      {
        requestRR = (RenderRequestImpl)request1;
        
        url = "//" + requestRR.getServerName() + ":" + requestRR.getServerPort() + "/doc";
      } else if ((request1 instanceof ActionRequestImpl))
      {
        requestAR = (ActionRequestImpl)request1;
        
        url = "//" + requestAR.getServerName() + ":" + requestAR.getServerPort() + "/doc";
      }
      else if ((request1 instanceof RequestFacade))
      {
        requestFacade = (RequestFacade)request1;
        url = "//" + requestFacade.getServerName() + ":" + requestFacade.getServerPort() + "/doc";
      }
      else
      {
        url = "/doc";
      }
      

    }
    catch (Exception ex)
    {

      System.out.println("URL Exception");
    }
    
    return url;
  }
  
  public ArrayList getOfficeList() { ArrayList officeList = new ArrayList();
    Connection con = null;
    ResultSet rs = null;
    PreparedStatement pst = null;
    String sql = null;
    SelectItem aSelectItem = null;
    aSelectItem = new SelectItem();
    aSelectItem.setLabel(ResourceUtil.getMessage("choose"));
    aSelectItem.setValue("0");
    officeList.add(aSelectItem);
    try
    {
      String strSql = "SELECT INT_OFFICE_ID_P, VCH_OFFICE_NM    FROM  PAYROLL.ESTB_M_OFFICE  WHERE VCH_FLAG=? ";
      

      con = ConnectionToDataBase.getConnection2();
      pst = con.prepareStatement(strSql);
      pst.setString(1, "O");
      rs = pst.executeQuery();
      while (rs.next()) {
        aSelectItem = new SelectItem();
        aSelectItem.setLabel(rs.getString("VCH_OFFICE_NM"));
        aSelectItem.setValue(rs.getString("INT_OFFICE_ID_P"));
        officeList.add(aSelectItem);
      }
    }
    catch (SQLException e)
    {
      e.printStackTrace();
      
      try
      {
        if (con != null)
        {
          con.close();
        }
      } catch (SQLException e1) {
        System.out.println("sql exception");
      }
    }
    finally
    {
      try
      {
        if (con != null)
        {
          con.close();
        }
      } catch (SQLException e) {
        System.out.println("sql exception");
      }
    }
    return officeList;
  }
  


  public static ArrayList sectionList()
  {
    ArrayList list = new ArrayList();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    SelectItem item = new SelectItem();
    item.setValue("");
    item.setLabel("---SELECT---");
    list.add(item);
    try
    {
      String query = " SELECT INT_SECTION_ID_P, VCH_SECTION_NM    FROM  PAYROLL.ESTB_M_SECTION   WHERE VCH_FLAG=?  ORDER BY VCH_SECTION_NM ";
      


      conn = ConnectionToDataBase.getConnection2();
      pstmt = conn.prepareStatement(query);
      pstmt.setString(1, "O");
      rs = pstmt.executeQuery();
      while (rs.next())
      {
        item = new SelectItem();
        item.setLabel(rs.getString("VCH_SECTION_NM"));
        item.setValue(rs.getString("INT_SECTION_ID_P"));
        list.add(item);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      


      try
      {
        if (pstmt != null) pstmt.close();
        if (conn != null) { conn.close();
        }
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
        if (conn != null) { conn.close();
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    return list;
  }
  
  public static ArrayList moduleList() {
    ArrayList list = new ArrayList();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    SelectItem item = new SelectItem();
    item.setValue("");
    item.setLabel("---SELECT---");
    list.add(item);
    try
    {
      String query = " SELECT DISTINCT A.PK,  A.NAME FROM   JBP_OBJECT_NODE A WHERE A.PARENT_KEY = 1    AND A.CODE <= 126 AND A.PK >100  \tAND A.ICON_IMG is not null ORDER BY A.NAME ASC  ";
      

      conn = ConnectionToDataBase.getConnection();
      pstmt = conn.prepareStatement(query);
      
      rs = pstmt.executeQuery();
      while (rs.next())
      {
        item = new SelectItem();
        item.setLabel(rs.getString("NAME"));
        item.setValue(rs.getString("PK"));
        list.add(item);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      


      try
      {
        if (pstmt != null) pstmt.close();
        if (conn != null) { conn.close();
        }
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
        if (conn != null) { conn.close();
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    return list;
  }
  
  public static ArrayList roleList(String var) {
    ArrayList list = new ArrayList();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    SelectItem item = new SelectItem();
    item.setValue("");
    item.setLabel("---SELECT---");
    list.add(item);
    try
    {
      String query = "SELECT DISTINCT b.ROLE ,c.JBP_RID  FROM  \t JBP_OBJECT_NODE A ,JBP_OBJECT_NODE_SEC b ,JBP_ROLES c        WHERE A.PARENT_KEY = 1  and c.JBP_NAME=b.ROLE  AND A.CODE <= 126 AND A.PK >100 AND A.ICON_IMG is not null    and b.NODE_KEY =A.PK and A.PK = " + 
      
        var + "\tand b.ROLE not in ('Admin','admin','User','ldauser') ORDER BY b.ROLE ASC ";
      

      conn = ConnectionToDataBase.getConnection();
      pstmt = conn.prepareStatement(query);
      
      rs = pstmt.executeQuery();
      while (rs.next())
      {
        item = new SelectItem();
        item.setLabel(rs.getString("ROLE"));
        item.setValue(rs.getString("JBP_RID"));
        list.add(item);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      


      try
      {
        if (pstmt != null) pstmt.close();
        if (conn != null) { conn.close();
        }
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
        if (conn != null) { conn.close();
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    return list;
  }
  
  public static ArrayList employeeList(String sectionID) { ArrayList list = new ArrayList();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    SelectItem item = new SelectItem();
    item.setValue("");
    item.setLabel("---SELECT---");
    list.add(item);
    
    if ((sectionID == null) || (sectionID.trim().length() < 1)) {
      return list;
    }
    






    try
    {
      String query = " SELECT INT_EMPLOYEE_ID_P,VCH_EMPLOYEE_NM  \tFROM PAYROLL.ESTB_M_EMPLOYEE_DETAIL     WHERE INT_CURR_SECTION_ID_F = ? and VCH_EMP_STATUS IN ('A','S') \tORDER BY INT_EMPLOYEE_ID_P ASC ";
      



      conn = ConnectionToDataBase.getConnection2();
      pstmt = conn.prepareStatement(query);
      
      pstmt.setInt(1, Integer.parseInt(sectionID.trim()));
      rs = pstmt.executeQuery();
      while (rs.next())
      {
        item = new SelectItem();
        item.setLabel(rs.getString("INT_EMPLOYEE_ID_P") + " - " + rs.getString("VCH_EMPLOYEE_NM"));
        item.setValue(rs.getString("INT_EMPLOYEE_ID_P"));
        list.add(item);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      


      try
      {
        if (pstmt != null) pstmt.close();
        if (conn != null) conn.close();
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
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    return list;
  }
  
  public static boolean currentStatusMethod(String userID) {
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    if (userID == null) {
      return false;
    }
    try
    {
      String jbp_update = " select jbp_enabled from jbp_users   where jbp_uname = ? ";
      
      conn = ConnectionToDataBase.getConnection();
      pstmt = conn.prepareStatement(jbp_update);
      pstmt.setString(1, userID.trim());
      rs = pstmt.executeQuery();
      if (rs.next()) {
        if ((rs.getString("jbp_enabled") != null) && (rs.getString("jbp_enabled").equalsIgnoreCase("1")))
          return true;
        if ((rs.getString("jbp_enabled") != null) && (rs.getString("jbp_enabled").equalsIgnoreCase("0"))) {}
        return false;
      }
      

    }
    catch (Exception e)
    {

      e.printStackTrace();
    }
    finally
    {
      try
      {
        if (pstmt != null) pstmt.close();
        if (conn != null) { conn.close();
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    try
    {
      if (pstmt != null) pstmt.close();
      if (conn != null) { conn.close();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    
    return false;
  }
  
  public static String encryptMD5(String pass) {
    String passwd = "";
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(pass.getBytes());
      byte[] encodedPassword = md.digest();
      passwd = bytesToHex(encodedPassword);
      System.out.println("####### passwd $$$$$$$ : " + passwd);
    } catch (NoSuchAlgorithmException nsae) {
      System.out.println("NoSuchAlgorithmException : " + nsae);
    }
    catch (Exception e) {
      System.out.println("Exception : " + e);
    }
    
    return passwd;
  }
  
  public static String bytesToHex(byte[] b) {
    char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6', '7', 
      '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    StringBuffer buf = new StringBuffer();
    for (int j = 0; j < b.length; j++) {
      buf.append(hexDigit[(b[j] >> 4 & 0xF)]);
      buf.append(hexDigit[(b[j] & 0xF)]);
    }
    return buf.toString();
  }
  
  public int submitMessage(Connection conn, String module_name, String mob_no, String emp_id, String recp_name, String msg)
  {
    PreparedStatement pstmt = null;
    
    ResultSet rs = null;
    String query = "";
    String msgid = "";
    Connection con = null;
    int status = 0;
    try {
      con = ConnectionToDataBase.getConnection2();
      
      String selectmax = "SELECT ACCOUNTS.INT_MSG_ID_SEQ.NEXTVAL FROM DUAL";
      pstmt = con.prepareStatement(selectmax);
      rs = pstmt.executeQuery();
      if (rs.next())
      {
        msgid = rs.getString(1);
      }
      
      query = 
        "INSERT INTO ACCOUNTS.COMMON_SMS_DB(INT_MSG_ID,VCH_MODULE_NAME,VCH_MOB_NO,VCH_TO_EMP_ID,VCH_TO_NAME,VCH_MSG,DAT_SUBMITION,    VCH_SENT_STATUS,INT_RETRY_NO) VALUES(?,?,?,?,?,?,?,?,?) ";
      


      pstmt = con.prepareStatement(query);
      pstmt.setString(1, msgid);
      pstmt.setString(2, module_name);
      pstmt.setString(3, mob_no);
      pstmt.setString(4, emp_id);
      pstmt.setString(5, recp_name);
      pstmt.setString(6, msg);
      pstmt.setDate(7, Utility.convertUtilDateToSQLDate(new Date()));
      pstmt.setString(8, "F");
      pstmt.setString(9, "0");
      status = pstmt.executeUpdate();

    }
    catch (Exception e)
    {
      e.printStackTrace();
      

      try
      {
        if (con != null) con.close();
        if (pstmt != null) pstmt.close();
        if (rs != null) { rs.close();
        }
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
        if (con != null) con.close();
        if (pstmt != null) pstmt.close();
        if (rs != null) { rs.close();
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    return status;
  }
}