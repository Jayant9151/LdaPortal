package com.mentor.jboss.home.impl;

import com.mentor.jboss.home.datatable.SideMenuDataTable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class SideMenuImpl
{
  public SideMenuImpl() {}
  
  public ArrayList<SideMenuDataTable> displaySubMenuList(com.mentor.jboss.home.action.SideMenuAction action)
  {
    ArrayList<SideMenuDataTable> subMenuList = new ArrayList();
    SideMenuDataTable dto = null;
    java.sql.PreparedStatement pstm = null;
    java.sql.ResultSet rs = null;
    Connection conn = com.mentor.uplc.connectdb.ConnectionToDataBase.getConnection();
    





    try
    {
      String contxtURL = null;
      String mainContxt = "lda/auth/portal/";
      
      String query = " select a.PK,a.ROLE,a.NODE_KEY,b.PARENT_KEY,  b.PATH,b.NAME from jbp_object_node_sec a, jbp_object_node b where a.ROLE=? and b.PARENT_KEY=170   AND a.NODE_KEY=170 AND a.NAME IN('MASTER','PROCESS','REPORT') ";
      



      String querySubMenu = " select a.PK,a.ROLE,a.NODE_KEY,b.PARENT_KEY,  b.PATH,b.NAME from jbp_object_node_sec a, jbp_object_node b where a.ROLE=? and b.PARENT_KEY=170   AND a.NODE_KEY=170 ";
      



      pstm = conn.prepareStatement(query.toUpperCase());
      pstm.setString(1, "Admin");
      rs = pstm.executeQuery();
      while (rs.next()) {
        dto = new SideMenuDataTable();
        contxtURL = rs.getString("PATH").replace(".", "/");
        contxtURL = contxtURL.replace(" ", "+");
        dto.setLinkName("NAME");
        dto.setLinkURL(mainContxt + contxtURL);
        subMenuList.add(dto);
      }
    } catch (Exception e) {
      e.printStackTrace();
      

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
      if (conn != null)
        try {
          conn.close();
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
    }
    return subMenuList;
  }
}