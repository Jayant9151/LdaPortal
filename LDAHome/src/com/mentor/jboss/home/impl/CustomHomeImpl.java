

package com.mentor.jboss.home.impl;

import com.mentor.jboss.home.action.CustomHomeAction;
import com.mentor.jboss.home.datatable.CustomHomeDataTable;
import com.mentor.uplc.connectdb.ConnectionToDataBase;
import com.mentor.uplc.utility.ResourceUtil;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.portlet.GenericPortlet;

import org.jboss.portal.core.model.portal.Portal;

public class CustomHomeImpl
{
  public CustomHomeImpl() {}
  
  public static int uploadSeq()
  {
    int fileid = 1;
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    boolean flag = false;
    
    conn = ConnectionToDataBase.getConnection2();
    try
    {
      String queryList = "SELECT max(INT_UPLOAD_SEQ) as max from ESTABLISHMENT.ESTB_M_UPLOAD_MST where VCH_UPLOAD_FOR='U'";
      
      pstmt = conn.prepareStatement(queryList);
      rs = pstmt.executeQuery();
      if (rs.next())
      {
        fileid = rs.getInt("max");
      }
    } catch (SQLException se) {
      se.printStackTrace();
      try
      {
        if (pstmt != null) { pstmt.close();
        }
        if (flag) {
          conn.close();
          flag = false;
        }
        conn.close();
      } catch (SQLException se1) {
        se1.printStackTrace();
      }
    }
    finally
    {
      try
      {
        if (pstmt != null) { pstmt.close();
        }
        if (flag) {
          conn.close();
          flag = false;
        }
        conn.close();
      } catch (SQLException se) {
        se.printStackTrace();
      }
    }
    return fileid;
  }
  
  public static boolean flgeauc() { int fileid = 1;
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    boolean flag = false;
    
    conn = ConnectionToDataBase.getConnection2();
    try
    {
      String queryList = "SELECT flg from Property.Home_Flg where VCH_UPLOAD_FOR='EAUC'";
      
      pstmt = conn.prepareStatement(queryList);
      rs = pstmt.executeQuery();
      if (rs.next())
      {
        if (rs.getString("flg").equalsIgnoreCase("Y")) {
          flag = true;
        } else {
          flag = false;
        }
      } else {
        flag = false;
      }
    } catch (SQLException se) {
      se.printStackTrace();
      try
      {
        if (pstmt != null) { pstmt.close();
        }
        


        conn.close();
      } catch (SQLException se1) {
        se1.printStackTrace();
      }
    }
    finally
    {
      try
      {
        if (pstmt != null) { pstmt.close();
        }
        


        conn.close();
      } catch (SQLException se) {
        se.printStackTrace();
      }
    }
    return flag;
  }
  
  public static boolean flgreg() { int fileid = 1;
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    boolean flag = false;
    
    conn = ConnectionToDataBase.getConnection2();
    try
    {
      String queryList = "SELECT flg from Property.Home_Flg where VCH_UPLOAD_FOR='REG'";
      
      pstmt = conn.prepareStatement(queryList);
      rs = pstmt.executeQuery();
      if (rs.next())
      {
        if (rs.getString("flg").equalsIgnoreCase("Y")) {
          flag = true;
        } else {
          flag = false;
        }
      } else {
        flag = false;
      }
    } catch (SQLException se) {
      se.printStackTrace();
      try
      {
        if (pstmt != null) { pstmt.close();
        }
        


        conn.close();
      } catch (SQLException se1) {
        se1.printStackTrace();
      }
    }
    finally
    {
      try
      {
        if (pstmt != null) { pstmt.close();
        }
        


        conn.close();
      } catch (SQLException se) {
        se.printStackTrace();
      }
    }
    return flag;
  }
  
  public ArrayList<CustomHomeDataTable> displayMenuList(CustomHomeAction action) { ArrayList<CustomHomeDataTable> list = new ArrayList();
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
     
      
      String query = " SELECT DISTINCT A.VCH_H_NAME, A.NAME,A.PATH, A.ICON_IMG,A.PK FROM  JBP_OBJECT_NODE A WHERE A.PARENT_KEY = 1   AND A.PK >100 AND A.ICON_IMG is not null  " + 
      
        jointSql + 
        " ORDER BY A.NAME ASC ";
      pstm = conn.prepareStatement(query.toUpperCase());
      rs = pstm.executeQuery();
      while (rs.next()) {
        dto = new CustomHomeDataTable();
        action.setColumnRender1(true);
        contxtURL = rs.getString("PATH").replace(".", "/");
        contxtURL = contxtURL.replace(" ", "+");
        dto.setCellDisplay1(rs.getString("NAME"));
        dto.setLinkIcon1("/icons/" + rs.getString("ICON_IMG") + "_E.png");
        if (rs.getInt("PK") == 6212) {
          dto.setLinkURL1("auth/portal/lda/otp");
        } else
          dto.setLinkURL1(mainContxt + contxtURL);
        dto.setCellRender1(true);
        if (rs.next()) {
          action.setColumnRender2(true);
          contxtURL = rs.getString("PATH").replace(".", "/");
          contxtURL = contxtURL.replace(" ", "+");
          dto.setCellDisplay2(rs.getString("NAME"));
          dto.setLinkIcon2("/icons/" + rs.getString("ICON_IMG") + "_E.png");
          if (rs.getInt("PK") == 6212) {
            dto.setLinkURL2("auth/portal/lda/otp");
          } else
            dto.setLinkURL2(mainContxt + contxtURL);
          dto.setCellRender2(true);
        }
        
        if (rs.next()) {
          action.setColumnRender3(true);
          contxtURL = rs.getString("PATH").replace(".", "/");
          contxtURL = contxtURL.replace(" ", "+");
          dto.setCellDisplay3(rs.getString("NAME"));
          dto.setLinkIcon3("/icons/" + rs.getString("ICON_IMG") + "_E.png");
          if (rs.getInt("PK") == 6212) {
            dto.setLinkURL3("auth/portal/lda/otp");
          } else
            dto.setLinkURL3(mainContxt + contxtURL);
          dto.setCellRender3(true);
        }
        
        if (rs.next()) {
          action.setColumnRender4(true);
          contxtURL = rs.getString("PATH").replace(".", "/");
          contxtURL = contxtURL.replace(" ", "+");
          dto.setCellDisplay4(rs.getString("NAME"));
          dto.setLinkIcon4("/icons/" + rs.getString("ICON_IMG") + "_E.png");
          if (rs.getInt("PK") == 6212) {
            dto.setLinkURL4("auth/portal/lda/otp");
          } else {
            dto.setLinkURL4(mainContxt + contxtURL);
          }
          dto.setCellRender4(true);
        }
        if (rs.next()) {
          action.setColumnRender5(true);
          contxtURL = rs.getString("PATH").replace(".", "/");
          contxtURL = contxtURL.replace(" ", "+");
          dto.setCellDisplay5(rs.getString("NAME"));
          dto.setLinkIcon5("/icons/" + rs.getString("ICON_IMG") + "_E.png");
          if (rs.getInt("PK") == 6212) {
            dto.setLinkURL5("auth/portal/lda/otp");
          } else
            dto.setLinkURL5(mainContxt + contxtURL);
          dto.setCellRender5(true);
        }
        if (rs.next()) {
          action.setColumnRender6(true);
          contxtURL = rs.getString("PATH").replace(".", "/");
          contxtURL = contxtURL.replace(" ", "+");
          dto.setCellDisplay6(rs.getString("NAME"));
          dto.setLinkIcon6("/icons/" + rs.getString("ICON_IMG") + "_E.png");
          if (rs.getInt("PK") == 6212) {
            dto.setLinkURL6("auth/portal/lda/otp");
          } else
            dto.setLinkURL6(mainContxt + contxtURL);
          dto.setCellRender6(true);
        }
        if (rs.next()) {
          action.setColumnRender7(true);
          contxtURL = rs.getString("PATH").replace(".", "/");
          contxtURL = contxtURL.replace(" ", "+");
          dto.setCellDisplay7(rs.getString("NAME"));
          dto.setLinkIcon7("/icons/" + rs.getString("ICON_IMG") + "_E.png");
          if (rs.getInt("PK") == 6212) {
            dto.setLinkURL7("auth/portal/lda/otp");
          } else
            dto.setLinkURL7(mainContxt + contxtURL);
          dto.setCellRender7(true);
        }
        
        list.add(dto);
      }
      System.out.println("STEP 3");
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
  

  public String newsText(Connection conn)
  {
    String displaytextB = "";
    
    String finalTextDisplay = "";
    PreparedStatement pstm = null;
    ResultSet rs = null;
    
    if (conn == null) {
      conn = ConnectionToDataBase.getConnection();
    }
    

    displaytextB = " * ";
    
    String SQL = "       SELECT  VCH_NEWS_TITLE, VCH_NEWS_TEXT, VCH_FILE_NM FROM LDAADMIN.JBP_PORTAL_NEWS WHERE (trunc(SYSDATE) BETWEEN trunc(DAT_PUBLISH_FRM_DT) AND trunc(DAT_PUBLISH_TO_DT) ) AND VCH_ACTIVE_FLAG = 'O' ";
    

    try
    {
      pstm = conn.prepareStatement(SQL);
      
      rs = pstm.executeQuery();
      while (rs.next()) {
        displaytextB = displaytextB + "  <a style=\"color:maroon; font-size:10px;\" href=\"/doc/newsfiles/" + rs.getString("VCH_FILE_NM") + "\" target=\"_blank\"> " + rs.getString("VCH_NEWS_TEXT") + " </a>  *    ";
      }
      
      finalTextDisplay = displaytextB;
    }
    catch (SQLException e) {
      e.printStackTrace();
      

      if (rs != null) {
        try {
          rs.close();
        }
        catch (SQLException e1) {
          e1.printStackTrace();
        }
      }
      if (pstm != null) {
        try {
          pstm.close();
        }
        catch (SQLException e1) {
          e1.printStackTrace();
        }
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
      if (rs != null) {
        try {
          rs.close();
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (pstm != null) {
        try {
          pstm.close();
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (conn != null) {
        try {
          conn.close();
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
    return finalTextDisplay;
  }
  
  public String pdfImpl(String s)
  {
    String pdf = "";
    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;
    




    try
    {
      String query = "select * from PAYROLL.ESTB_M_LDAHOME where VCH_ID  = '" + s + "'";
      System.out.print("query= " + query);
      conn = ConnectionToDataBase.getConnection2();
      
      pstm = conn.prepareStatement(query);
      rs = pstm.executeQuery();
      while (rs.next())
      {

        pdf = rs.getString("VCH_PDFNAME");
        System.out.println(pdf);
      }
      
    }
    catch (Exception e)
    {
      e.printStackTrace();
      


      if (rs != null) {
        try {
          rs.close();
        }
        catch (SQLException e1) {
          e1.printStackTrace();
        }
      }
      if (pstm != null) {
        try {
          pstm.close();
        }
        catch (SQLException e1) {
          e1.printStackTrace();
        }
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
      if (rs != null) {
        try {
          rs.close();
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (pstm != null) {
        try {
          pstm.close();
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (conn != null) {
        try {
          conn.close();
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
    System.out.println("pdf name" + pdf);
    return pdf;
  }
}

