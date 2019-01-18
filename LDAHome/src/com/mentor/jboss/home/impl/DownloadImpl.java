package com.mentor.jboss.home.impl;

import com.mentor.jboss.home.action.DownloadAction;
import com.mentor.uplc.connectdb.ConnectionToDataBase;
import com.mentor.uplc.utility.Constants;
import com.mentor.uplc.utility.ResourceUtil;
import com.mentor.uplc.utility.Utility;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class DownloadImpl
{
  public DownloadImpl() {}
  
  public String pdfImpl(DownloadAction s)
  {
    String pdf = "";
    Connection conn = null;
    PreparedStatement pstm = null;
    ResultSet rs = null;
    




    try
    {
      String query = "select VCH_NAME from ESTABLISHMENT.HOME_DOWNLOADS where INT_SNO  = '" + s.getSrNO1() + "'";
      System.out.print("query= " + query);
      conn = ConnectionToDataBase.getConnection2();
      
      pstm = conn.prepareStatement(query);
      rs = pstm.executeQuery();
      while (rs.next())
      {

        pdf = rs.getString("VCH_NAME");
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
  
  public ArrayList pdfName() {
    ArrayList temp = new ArrayList();
    Connection conn = null;
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    int i = 0;
    
    try
    {
      String query = "select INT_SNO,VCH_NAME , DATE_VALID_DT,VCH_FILE_NM from ESTABLISHMENT.HOME_DOWNLOADS WHERE DATE_VALID_DT>=( select sysdate  from_date from dual)";
      

      conn = ConnectionToDataBase.getConnection2();
      pstmt = conn.prepareStatement(query);
      rs = pstmt.executeQuery();
      
      int k = 1;
      while (rs.next())
      {
        DownloadAction dt = new DownloadAction();
        
        dt.setName(rs.getString("VCH_FILE_NM"));
        dt.setSrNO1(rs.getInt("INT_SNO"));
        dt.setSrno(k++);
        dt.setFilename(rs.getString("VCH_NAME"));
        dt.setSrcMethod(rs.getString("VCH_FILE_NM"));
        dt.setValidDate1(Utility.convertUtilDateToSQLDate(rs.getDate("DATE_VALID_DT")));
        temp.add(dt);
      }
    }
    catch (SQLException se) {
      se.printStackTrace();
      try
      {
        if (pstmt != null) pstmt.close();
        if (conn != null) conn.close();
      }
      catch (SQLException se1) {
        se1.printStackTrace();
      }
    }
    finally
    {
      try
      {
        if (pstmt != null) pstmt.close();
        if (conn != null) conn.close();
      }
      catch (SQLException se) {
        se.printStackTrace();
      }
    }
    return temp;
  }
  
  public void saveUploadedFile(String fileSeq, Date uploaddate, String fileNm, Date validDate, DownloadAction action, String filename) {
    Connection con = null;
    PreparedStatement pstmt = null;
    int saveStatus = 0;
    ResultSet rs = null;
    try {
      con = ConnectionToDataBase.getConnection2();
      
      String newFilePath = Constants.JBOSS_SERVER_PATH + "doc" + File.separator + "LDA" + File.separator + "welcome" + File.separator + "homepage" + File.separator + "DOWNLOADS";
      
      String SQL = 
        "\tselect max(a.INT_SNO) as snom from  ESTABLISHMENT.HOME_DOWNLOADS  a ";
      

      pstmt = con.prepareStatement(SQL);
      rs = pstmt.executeQuery();
      

      if (rs.next())
      {
        int num = rs.getInt("snom");
        String query1 = " insert into  ESTABLISHMENT.HOME_DOWNLOADS  (INT_SNO,VCH_NAME,VCH_FILE_NM,DATE_UPLOAD_DT ,DATE_VALID_DT) values (?,?,?,?,?) ";
        

        pstmt = con.prepareStatement(query1);
        
        pstmt.setInt(1, num + 1);
        pstmt.setString(2, action.getFullfileName());
        pstmt.setDate(4, Utility.convertUtilDateToSQLDate(uploaddate));
        pstmt.setDate(5, Utility.convertUtilDateToSQLDate(validDate));
        pstmt.setString(3, fileNm);
        if ((action.getFilePath() != null) && (action.getFilePath().length() > 0))
        {
          File file = null;
          newFilePath = newFilePath + File.separator + (num + 1);
          if (!new File(newFilePath).exists())
          {
            file = new File(newFilePath);
            file.mkdirs();
          }
          BufferedInputStream in = action.getBufferedinForUploadFile();
          
          in.available();
          
          FileOutputStream out = new FileOutputStream(newFilePath + File.separator + filename);
          
          BufferedOutputStream outb = new BufferedOutputStream(out);
          int z = 0;
          while ((z = in.read()) != -1)
          {
            outb.write(z);
          }
          outb.flush();
          outb.close();
        }
      }
      



      saveStatus = pstmt.executeUpdate();
      if (saveStatus > 0) {
        ResourceUtil.addMessage("upload_success", "upload_success");
        action.setFileNm(null);
        action.setUploadDate(null);
        action.setValidDate(null);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      try
      {
        if (pstmt != null) pstmt.close();
        if (con != null) con.close();
      }
      catch (SQLException se) {
        se.printStackTrace();
      }
    }
    finally
    {
      try
      {
        if (pstmt != null) pstmt.close();
        if (con != null) con.close();
      }
      catch (SQLException se) {
        se.printStackTrace();
      }
    }
  }
  


  public Date getdisplayTill()
  {
    Connection conn = null;
    Date date = null;
    ResultSet rs = null;
    PreparedStatement pd = null;
    String query = "select add_months(sysdate,1)  from_date from dual";
    try
    {
      conn = ConnectionToDataBase.getConnection();
      pd = conn.prepareStatement(query);
      
      rs = pd.executeQuery();
      if (rs.next())
      {
        date = Utility.convertSqlDateToUtilDate(rs.getDate("from_date"));
      }
      
    }
    catch (Exception e)
    {
      System.out.println(e);
      



      try
      {
        if (rs != null) rs.close();
        if (pd != null) { pd.close();
        }
        
        if (conn != null) conn.close();
      }
      catch (Exception e1)
      {
        System.out.println(e1);
      }
    }
    finally
    {
      try
      {
        if (rs != null) rs.close();
        if (pd != null) { pd.close();
        }
        
        if (conn != null) conn.close();
      }
      catch (Exception e)
      {
        System.out.println(e);
      }
    }
    
    return date;
  }
}