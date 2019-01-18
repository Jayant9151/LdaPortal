package com.mentor.jboss.home.impl;

import com.mentor.jboss.home.action.UserManagmentAction;
import com.mentor.jboss.home.common.CommonImpl;
import com.mentor.jboss.home.datatable.UserManagmentDataTable;
import com.mentor.uplc.connectdb.ConnectionToDataBase;
import com.mentor.uplc.utility.RandomPasswordGenerator;
import com.mentor.uplc.utility.ResourceUtil;
import com.mentor.uplc.utility.Utility;
import jAPI.client;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.model.SelectItem;






public class UserManagmentImpl
{
  public UserManagmentImpl() {}
  
  public int usrcode(UserManagmentAction action)
  {
    PreparedStatement pstm = null;
    
    ResultSet rs = null;
    int id = 0;
    Connection conn = ConnectionToDataBase.getConnection();
    try
    {
      System.out.println("action.getEmpIDForMgmt()-----------" + action.getEmpIDForMgmt());
      String query = " SELECT DISTINCT JBP_UID FROM  \t JBP_USERS where JBP_UNAME='" + 
        action.getEmpIDForMgmt() + "'";
      pstm = conn.prepareStatement(query);
      rs = pstm.executeQuery();
      if (rs.next()) {
        id = rs.getInt("JBP_UID");
      }
    }
    catch (SQLException e)
    {
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
      if (conn != null) {
        try {
          conn.close();
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
    return id;
  }
  
  public int usrcode1(String idi) {
    PreparedStatement pstm = null;
    
    ResultSet rs = null;
    int id = 0;
    Connection conn = ConnectionToDataBase.getConnection();
    
    try
    {
      String query = " SELECT DISTINCT JBP_UID FROM  \t JBP_USERS where JBP_UNAME='" + 
        idi + "'";
      pstm = conn.prepareStatement(query);
      rs = pstm.executeQuery();
      if (rs.next()) {
        id = rs.getInt("JBP_UID");
      }
    }
    catch (SQLException e)
    {
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
      if (conn != null) {
        try {
          conn.close();
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
    return id;
  }
  
  public ArrayList<UserManagmentDataTable> displayMenuList(UserManagmentAction action) { ArrayList<UserManagmentDataTable> list = new ArrayList();
    UserManagmentDataTable dto = null;
    PreparedStatement pstm = null;
    PreparedStatement pstm77 = null;
    ResultSet rs = null;
    ResultSet rs77 = null;
    Connection conn = ConnectionToDataBase.getConnection();
    int sno = 1;
    try
    {
      String query = " SELECT DISTINCT A.PK, A.VCH_H_NAME, A.NAME,A.PATH, A.ICON_IMG, a.CODE FROM  JBP_OBJECT_NODE A WHERE A.PARENT_KEY = 1   AND A.CODE <= 126 AND A.PK >100 AND A.ICON_IMG is not null   ORDER BY A.NAME ASC ";
      


      pstm = conn.prepareStatement(query.toUpperCase());
      rs = pstm.executeQuery();
      while (rs.next()) {
        dto = new UserManagmentDataTable();
        dto.setSerailNo(sno);
        dto.setModuleID(rs.getString("PK"));
        dto.setModuleName(rs.getString("NAME"));
        dto.setModuleCode(rs.getInt("CODE"));
        

        list.add(dto);
        sno++;
      }
    }
    catch (SQLException e)
    {
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
  
  public ArrayList<UserManagmentDataTable> rolewiseEmpMenuList(UserManagmentAction action)
  {
    ArrayList<UserManagmentDataTable> list = new ArrayList();
    UserManagmentDataTable dto = null;
    PreparedStatement pstm = null;
    PreparedStatement pstm77 = null;
    ResultSet rs = null;
    ResultSet rs77 = null;
    Connection conn = ConnectionToDataBase.getConnection();
    int sno = 1;
    try
    {
      String query = " SELECT DISTINCT a.jbp_uname,a.JBP_GIVENNAME,a.JBP_UID FROM  \t JBP_USERS a, JBP_ROLE_MEMBERSHIP b   WHERE b.jbp_rid=" + action.getRoleid() + " and b.jbp_uid=a.jbp_uid " + 
        " ORDER BY A.JBP_GIVENNAME ASC ";
      pstm = conn.prepareStatement(query.toUpperCase());
      rs = pstm.executeQuery();
      while (rs.next()) {
        dto = new UserManagmentDataTable();
        dto.setSerailNo(sno);
        dto.setUsrnm(rs.getString("JBP_GIVENNAME"));
        dto.setUid(rs.getString("JBP_UID"));
        dto.setUsrcheck(true);
        

        list.add(dto);
        sno++;
      }
    }
    catch (SQLException e)
    {
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
  
  public ArrayList<UserManagmentDataTable> displayRoleList(String var, int id) { ArrayList<UserManagmentDataTable> list = new ArrayList();
    UserManagmentDataTable dto = null;
    PreparedStatement pstm = null;
    PreparedStatement pstm77 = null;
    ResultSet rs = null;
    ResultSet rs77 = null;
    Connection conn = ConnectionToDataBase.getConnection();
    int sno = 1;
    try
    {
      String query = " SELECT DISTINCT b.ROLE ,c.JBP_RID,d.JBP_UID  FROM  \t JBP_OBJECT_NODE A ,JBP_OBJECT_NODE_SEC b ,JBP_ROLES c left outer join JBP_ROLE_MEMBERSHIP d on d.JBP_RID=c.JBP_RID and d.JBP_UID=" + 
        id + 
        "       WHERE A.PARENT_KEY = 1  and c.JBP_NAME=b.ROLE" + 
        "  AND A.CODE <= 126 AND A.PK >100 AND A.ICON_IMG is not null    and b.NODE_KEY =A.PK and A.PK in (" + var + "0)\tand b.ROLE not in ('Admin','admin','User','ldauser') ORDER BY b.ROLE ASC ";
      pstm = conn.prepareStatement(query);
      System.out.println("query------" + query);
      rs = pstm.executeQuery();
      while (rs.next()) {
        dto = new UserManagmentDataTable();
        dto.setSerailNorole(sno);
        dto.setRoleid(rs.getInt("JBP_RID"));
        
        dto.setRoleName(rs.getString("ROLE"));
        if ((rs.getString("JBP_UID") != null) && (rs.getString("JBP_UID").length() > 0) && (rs.getInt("JBP_UID") != 0))
        {
          dto.setRolecheck(true);
        }
        
        list.add(dto);
        sno++;
      }
    }
    catch (SQLException e)
    {
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
  
  public ArrayList<UserManagmentDataTable> displayMenuListold(UserManagmentAction action) { ArrayList<UserManagmentDataTable> list = new ArrayList();
    UserManagmentDataTable dto = null;
    PreparedStatement pstm = null;
    PreparedStatement pstm77 = null;
    ResultSet rs = null;
    ResultSet rs77 = null;
    Connection conn = ConnectionToDataBase.getConnection();
    int sno = 1;
    try {
      String getUserRole = "SELECT DISTINCT B.JBP_NAME FROM JBP_USERS A,   JBP_ROLE_MEMBERSHIP C   LEFT OUTER JOIN JBP_ROLES B ON   B.JBP_RID = C.JBP_RID  WHERE C.JBP_UID = A.JBP_UID  AND A.JBP_UNAME = ? ";
      




      String userRoles = " WHERE A.ROLE IN( ";
      pstm = conn.prepareStatement(getUserRole);
      pstm.setString(1, ResourceUtil.getUserNameAllReq());
      rs = pstm.executeQuery();
      while (rs.next())
        userRoles = userRoles + "'" + rs.getString("JBP_NAME") + "', ";
      ArrayList<UserManagmentDataTable> localArrayList1;
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
      String query = " SELECT DISTINCT A.PK, A.VCH_H_NAME, A.NAME,A.PATH, A.ICON_IMG, a.CODE FROM  JBP_OBJECT_NODE A WHERE A.PARENT_KEY = 1   AND A.CODE <= 126 AND A.PK >100 AND A.ICON_IMG is not null  " + 
      
        jointSql + 
        " ORDER BY A.NAME ASC ";
      pstm = conn.prepareStatement(query.toUpperCase());
      rs = pstm.executeQuery();
      while (rs.next()) {
        dto = new UserManagmentDataTable();
        dto.setSerailNo(sno);
        dto.setModuleID(rs.getString("PK"));
        dto.setModuleName(rs.getString("NAME"));
        dto.setModuleCode(rs.getInt("CODE"));
        pstm77 = conn.prepareStatement("SELECT A.JBP_UID , A.JBP_UNAME, B.JBP_RID  FROM JBP_USERS  A LEFT OUTER JOIN JBP_ROLE_MEMBERSHIP B ON A.JBP_UID = B.JBP_UID  WHERE  A.JBP_UNAME = ? ");
        


        pstm77.setString(1, action.getEmpIDForMgmt());
        rs77 = pstm77.executeQuery();
        while (rs77.next())
        {
          if (rs.getInt("CODE") == 101) {
            if (rs77.getInt("JBP_RID") == 45) {
              dto.setCheckAdmin(true);
            }
            if (rs77.getInt("JBP_RID") == 46) {
              dto.setCheckUser(true);
            }
          }
          if (rs.getInt("CODE") == 102)
          {
            if (rs77.getInt("JBP_RID") == 135) {
              dto.setCheckAdmin(true);
            }
            if (rs77.getInt("JBP_RID") == 136)
            {
              dto.setCheckUser(true);
            }
          }
          if (rs.getInt("CODE") == 103) {
            if (rs77.getInt("JBP_RID") == 105) {
              dto.setCheckAdmin(true);
            }
            if (rs77.getInt("JBP_RID") == 106)
            {
              dto.setCheckUser(true);
            }
          }
          if (rs.getInt("CODE") == 104) {
            if (rs77.getInt("JBP_RID") == 109) {
              dto.setCheckAdmin(true);
            }
            if (rs77.getInt("JBP_RID") == 110)
            {
              dto.setCheckUser(true);
            }
          }
          if (rs.getInt("CODE") == 120) {
            if (rs77.getInt("JBP_RID") == 4343) {
              dto.setCheckAdmin(true);
            }
            if (rs77.getInt("JBP_RID") == 4344)
            {
              dto.setCheckUser(true);
            }
          }
          if (rs.getInt("CODE") == 105) {
            if (rs77.getInt("JBP_RID") == 99) {
              dto.setCheckAdmin(true);
            }
            if (rs77.getInt("JBP_RID") == 100)
            {
              dto.setCheckUser(true);
            }
          }
          
          if (rs.getInt("CODE") == 106) {
            if (rs77.getInt("JBP_RID") == 107) {
              dto.setCheckAdmin(true);
            }
            if (rs77.getInt("JBP_RID") == 108)
            {
              dto.setCheckUser(true);
            }
          }
          
          System.out.println(rs.getInt("CODE") + " AND " + 107);
          
          if (rs.getInt("CODE") == 107) {
            if (rs77.getInt("JBP_RID") == 97) {
              dto.setCheckAdmin(true);
            }
            if (rs77.getInt("JBP_RID") == 98)
            {
              dto.setCheckUser(true);
            }
          }
          if (rs.getInt("CODE") == 108) {
            if (rs77.getInt("JBP_RID") == 137) {
              dto.setCheckAdmin(true);
            }
            if (rs77.getInt("JBP_RID") == 138)
            {
              dto.setCheckUser(true);
            }
          }
          if (rs.getInt("CODE") == 109) {
            if (rs77.getInt("JBP_RID") == 103) {
              dto.setCheckAdmin(true);
            }
            if (rs77.getInt("JBP_RID") == 104)
            {
              dto.setCheckUser(true);
            }
          }
          if (rs.getInt("CODE") == 110) {
            if (rs77.getInt("JBP_RID") == 49) {
              dto.setCheckAdmin(true);
            }
            if (rs77.getInt("JBP_RID") == 50) {
              dto.setCheckUser(true);
            }
          }
          if (rs.getInt("CODE") == 111) {
            if (rs77.getInt("JBP_RID") == 47) {
              dto.setCheckAdmin(true);
            }
            if (rs77.getInt("JBP_RID") == 48)
            {
              dto.setCheckUser(true);
            }
          }
          if (rs.getInt("CODE") == 112) {
            if (rs77.getInt("JBP_RID") == 95) {
              dto.setCheckAdmin(true);
            }
            if (rs77.getInt("JBP_RID") == 96) {
              dto.setCheckUser(true);
            }
          }
          if (rs.getInt("CODE") == 113) {
            if (rs77.getInt("JBP_RID") == 43) {
              dto.setCheckAdmin(true);
            }
            if (rs77.getInt("JBP_RID") == 44) {
              dto.setCheckUser(true);
            }
          }
          if (rs.getInt("CODE") == 114) {
            if (rs77.getInt("JBP_RID") == 111) {
              dto.setCheckAdmin(true);
            }
            if (rs77.getInt("JBP_RID") == 112)
            {
              dto.setCheckUser(true);
            }
          }
          if (rs.getInt("CODE") == 115) {
            if (rs77.getInt("JBP_RID") == 113) {
              dto.setCheckAdmin(true);
            }
            if (rs77.getInt("JBP_RID") == 114) {
              dto.setCheckUser(true);
            }
          }
          if (rs.getInt("CODE") == 116) {
            if (rs77.getInt("JBP_RID") == 101) {
              dto.setCheckAdmin(true);
            }
            if (rs77.getInt("JBP_RID") == 102)
            {
              dto.setCheckUser(true);
            }
          }
          if (rs.getInt("CODE") == 117)
          {
            if (rs77.getInt("JBP_RID") == 139) {
              dto.setCheckAdmin(true);
            }
            if (rs77.getInt("JBP_RID") == 140) {
              dto.setCheckUser(true);
            }
          }
          if (rs.getInt("CODE") == 118) {
            if (rs77.getInt("JBP_RID") == 41) {
              dto.setCheckAdmin(true);
            }
            if (rs77.getInt("JBP_RID") == 42) {
              dto.setCheckUser(true);
            }
          }
          if (rs.getInt("CODE") == 119) {
            if (rs77.getInt("JBP_RID") == 4286) {
              dto.setCheckAdmin(true);
            }
            if (rs77.getInt("JBP_RID") == 4387) {
              dto.setCheckUser(true);
            }
          }
          if (rs.getInt("CODE") == 123)
          {
            if (rs77.getInt("JBP_RID") == 4388) {
              dto.setCheckAdmin(true);
            }
            if (rs77.getInt("JBP_RID") == 4389) {
              dto.setCheckUser(true);
            }
          }
          if (rs.getInt("CODE") == 124) {
            if (rs77.getInt("JBP_RID") == 4390) {
              dto.setCheckAdmin(true);
            }
            if (rs77.getInt("JBP_RID") == 4391) {
              dto.setCheckUser(true);
            }
          }
          if (rs.getInt("CODE") == 125) {
            if (rs77.getInt("JBP_RID") == 4394) {
              dto.setCheckAdmin(true);
            }
            if (rs77.getInt("JBP_RID") == 4395) {
              dto.setCheckUser(true);
            }
          }
          if (rs.getInt("CODE") == 126) {
            if (rs77.getInt("JBP_RID") == 4392) {
              dto.setCheckAdmin(true);
            }
            if (rs77.getInt("JBP_RID") == 4393) {
              dto.setCheckUser(true);
            }
          }
          if (rs.getInt("CODE") == 122) {
            if (rs77.getInt("JBP_RID") == 4202) {
              dto.setCheckAdmin(true);
            }
            if (rs77.getInt("JBP_RID") == 4382) {
              dto.setCheckUser(true);
            }
          }
          
          if (rs.getInt("CODE") == 121) {
            if (rs77.getInt("JBP_RID") == 4383) {
              dto.setCheckAdmin(true);
            }
            if (rs77.getInt("JBP_RID") == 4384) {
              dto.setCheckUser(true);
            }
          }
        }
        

        list.add(dto);
        sno++;
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
  
  public void deActivationMethod(String userID, boolean status, UserManagmentAction action)
  {
    Connection conn = null;
    Connection conn1 = null;
    PreparedStatement pstmt = null;
    int saveStatus = 0;
    ResultSet rs1 = null;
    ResultSet rs = null;
    int id = 0;
    String mob = "";
    String name = "";
    String status1 = "";
    try
    {
      String jbp_update = " update jbp_users set  jbp_enabled =?  where jbp_uname = ? ";
      

      conn1 = ConnectionToDataBase.getConnection();
      conn = ConnectionToDataBase.getConnection2();
      conn1.setAutoCommit(false);
      conn.setAutoCommit(false);
      pstmt = conn1.prepareStatement(jbp_update);
      if (status) {
        pstmt.setString(1, "0");
      } else {
        pstmt.setString(1, "1");
      }
      pstmt.setString(2, userID.trim());
      saveStatus = pstmt.executeUpdate();
      
      if (saveStatus > 0) {
        saveStatus = 0;
        
        if (status) {
          action.setUserCurrentStatus("Currently Selected User is De-active");
          action.setUserActivateDeActivateStatus("Activate this Account");
          action.setUserStatus(false);
          status1 = "Currently De-activated";
        } else {
          action.setUserCurrentStatus("Currently Selected User is Active");
          action.setUserActivateDeActivateStatus("Deactivate this Account");
          action.setUserStatus(true);
          status1 = "Currently Activated";
        }
        
        String queryList = "SELECT ACCOUNTS.MST_MESSAGE_MASTER_SEQ.nextval FROM dual";
        pstmt = conn.prepareStatement(queryList);
        rs = pstmt.executeQuery();
        while (rs.next())
        {
          id = rs.getInt(1);
        }
        
        String queryListFrNo = "SELECT INT_CURR_MOBILE_NO,VCH_EMPLOYEE_NM FROM PAYROLL.ESTB_M_EMPLOYEE_DETAIL where INT_EMPLOYEE_ID_P=" + action.getEmpIDForAct() + 
          " and INT_CURR_SECTION_ID_F=" + action.getSectionIDForAct();
        pstmt = conn.prepareStatement(queryListFrNo);
        rs1 = pstmt.executeQuery();
        while (rs1.next())
        {
          mob = rs1.getString("INT_CURR_MOBILE_NO");
          name = rs1.getString("VCH_EMPLOYEE_NM");
        }
        
        String staus = "";
        String to = mob.trim();
        String from = CommonImpl.getSendSmsFrom();
        
        String msg = name + ",Your LDA user account  has been " + status1;
        client jClient = new client();
        client.user = CommonImpl.getSendSmsUser();
        client.password = CommonImpl.getSendSmsUserPassword();
        


        String s = client.send(from, to, msg);
        
        String Statussave = client.get_status(s, to);
        

        System.out.println(s);
        

        if (!s.equals(""))
        {

          String query = "INSERT INTO PROPERTY.COMMON_MSG_ACTION  (VCH_MOB_NUM,DT_ACTION_DATE,VCH_ACTION,VCH_MSG ,VCH_MODULE_NM ,VCH_SEND_STATUS ,VCH_STATUS_TYPE )  VALUES(?,?,?,?,?,?,?)";
          



          pstmt = conn.prepareStatement(query);
          pstmt.setString(1, mob.trim());
          pstmt.setDate(2, Utility.convertUtilDateToSQLDate(new Date()));
          pstmt.setString(3, "N");
          pstmt.setString(4, msg);
          pstmt.setString(5, "HOME");
          pstmt.setString(6, Statussave);
          if ((Statussave != null) && ((Statussave.equalsIgnoreCase("Outgoing Queue")) || (Statussave.equalsIgnoreCase("Sent")) || (Statussave.equalsIgnoreCase("Pending"))))
          {
            pstmt.setString(7, "S");
          }
          else
          {
            pstmt.setString(7, "U");
          }
          saveStatus = pstmt.executeUpdate();
        }
      }
      



      if (saveStatus > 0) {
        conn1.commit();
        conn.commit();
        ResourceUtil.addMessage("accountUpdate", "accountUpdate");

      }
      else
      {
        conn1.rollback();
        conn.rollback();
        ResourceUtil.addErrorMessage("accountNotUpdate", "accountNotUpdate");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      


      try
      {
        if (pstmt != null) pstmt.close();
        if (conn != null) conn.close();
        if (conn1 != null) { conn1.close();
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
        if (conn != null) conn.close();
        if (conn1 != null) { conn1.close();
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }
  














  public String password()
  {
    int noOfCAPSAlpha = 1;
    int noOfDigits = 1;
    int noOfSplChars = 1;
    int minLen = 8;
    int maxLen = 12;
    

    char[] pswd = RandomPasswordGenerator.generatePswd(minLen, maxLen, 
      noOfCAPSAlpha, noOfDigits, noOfSplChars);
    


    return new String(pswd);
  }
  
  public void updatePassword(String userID, UserManagmentAction action) {
    Connection conn = null;
    Connection conn1 = null;
    PreparedStatement pstmt = null;
    int saveStatus = 0;
    ResultSet rs = null;
    ResultSet rs1 = null;
    String password = "";
    String mob = "";
    String name = "";
    int id = 0;
    
    try
    {
      String jbp_update = " update jbp_users set  JBP_PASSWORD =?  where jbp_uname = ? ";
      


      conn = ConnectionToDataBase.getConnection2();
      conn1 = ConnectionToDataBase.getConnection();
      conn.setAutoCommit(false);
      conn1.setAutoCommit(false);
      pstmt = conn1.prepareStatement(jbp_update);
      password = password().trim();
      pstmt.setString(1, ResourceUtil.encryptMD5(password));
      
      pstmt.setString(2, userID.trim());
      saveStatus = pstmt.executeUpdate();
      
      if (saveStatus > 0) {
        System.out.println("1111111111111");
        saveStatus = 0;
        String queryList = "SELECT ACCOUNTS.MST_MESSAGE_MASTER_SEQ.nextval FROM dual";
        pstmt = conn.prepareStatement(queryList);
        rs = pstmt.executeQuery();
        while (rs.next())
        {
          id = rs.getInt(1);
        }
        String queryListFrNo = "SELECT INT_CURR_MOBILE_NO,VCH_EMPLOYEE_NM FROM PAYROLL.ESTB_M_EMPLOYEE_DETAIL where INT_EMPLOYEE_ID_P=" + action.getEmpIDForReset() + 
          " and INT_CURR_SECTION_ID_F=" + action.getSectionIDForReset();
        pstmt = conn.prepareStatement(queryListFrNo);
        rs1 = pstmt.executeQuery();
        while (rs1.next())
        {
          mob = rs1.getString("INT_CURR_MOBILE_NO");
          name = rs1.getString("VCH_EMPLOYEE_NM");
        }
        
        String staus = "";
        String to = mob.trim();
        String from = CommonImpl.getSendSmsFrom();
        
        String msg = " Dear Mr./Miss/Mrs. " + name + ",Your password for LDA account has been changed. Your new password is: " + password;
        
        saveStatus = new CommonImpl().submitMessage(null, "LDAHOME", to, action.getEmpIDForReset(), name, msg);
      }
      













































































      if (saveStatus > 0)
      {
        conn1.commit();
        conn.commit();
        ResourceUtil.addMessage("PasswrdSuccessfully", "PasswrdSuccessfully");
      } else {
        conn1.rollback();
        conn.rollback();
        ResourceUtil.addMessage("record.Notsave", "record.Notsave");
      }
    }
    catch (Exception e) {
      e.printStackTrace();
      


      try
      {
        if (pstmt != null) pstmt.close();
        if (conn != null) conn.close();
        if (conn1 != null) { conn1.close();
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
        if (conn != null) conn.close();
        if (conn1 != null) { conn1.close();
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }
  
  public void saveRoleData(UserManagmentAction action)
  {
    UserManagmentDataTable dto = new UserManagmentDataTable();
    Connection conn = null;
    ArrayList list = new ArrayList();
    conn = ConnectionToDataBase.getConnection();
    PreparedStatement pstm = null;
    try { conn.setAutoCommit(false);
      int id = 0;int sstatus = 0;
      String querySelect = 
        "\tselect jbp_uid from jbp_users \twhere jbp_uname = ?";
      
      pstm = conn.prepareStatement(querySelect);
      pstm.setString(1, action.getEmpIDForMgmt().trim());
      ResultSet rs = pstm.executeQuery();
      if (rs.next()) {
        String deleteExistRole = " DELETE FROM jbp_role_membership  where jbp_uid =? and jbp_rid!=4384 ";
        
        pstm = conn.prepareStatement(deleteExistRole);
        pstm.setInt(1, rs.getInt("jbp_uid"));
        id = rs.getInt("jbp_uid");
        int status = pstm.executeUpdate();
        
        if (status > 0) {
          System.out.println(" DELETE STATUS : " + status + " RECORDS ARE DELETED.");
        }
      } else {
        action.setMsgPanel(true);
        

        return;
      }
      





      for (int i = 0; i < action.getRoleList().size(); i++)
      {
        UserManagmentDataTable dt = (UserManagmentDataTable)action.getRoleList().get(i);
        
        if (dt.isRolecheck())
        {
          String query1 = "INSERT INTO LDAADMIN.JBP_ROLE_MEMBERSHIP(JBP_UID,JBP_RID)  VALUES(?,?)";
          

          pstm = conn.prepareStatement(query1);
          
          pstm.setInt(1, id);
          pstm.setInt(2, dt.getRoleid());
          sstatus = pstm.executeUpdate();
        }
      }
      


      if ((conn != null) && (sstatus > 0)) {
        conn.commit();
        ResourceUtil.addMessage("SavedSuccessfully", "SavedSuccessfully");
      }
    }
    catch (Exception e) {
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
  }
  
  public void saveUsrData(UserManagmentAction action) {
    UserManagmentDataTable dto = new UserManagmentDataTable();
    Connection conn = null;
    ArrayList list = new ArrayList();
    conn = ConnectionToDataBase.getConnection();
    PreparedStatement pstm = null;
    try { conn.setAutoCommit(false);
      int id = 0;int sstatus = 0;
      
      ResultSet rs = null;
      System.out.println("--------------" + action.getRoleid());
      String deleteExistRole = " DELETE FROM jbp_role_membership  where jbp_rid=" + 
        action.getRoleid();
      pstm = conn.prepareStatement(deleteExistRole);
      
      int status = pstm.executeUpdate();
      
      if (status > 0) {
        status = 0;
        for (int i = 0; i < action.getEmprolList().size(); i++)
        {
          UserManagmentDataTable dt = (UserManagmentDataTable)action.getEmprolList().get(i);
          
          if (dt.isUsrcheck())
          {
            String query1 = "INSERT INTO LDAADMIN.JBP_ROLE_MEMBERSHIP(JBP_UID,JBP_RID)  VALUES(?,?)";
            

            pstm = conn.prepareStatement(query1);
            
            pstm.setString(1, dt.getUid());
            pstm.setString(2, action.getRoleid());
            status = pstm.executeUpdate();
          }
        }
      }
      

      if (status > 0) {
        conn.commit();
        ResourceUtil.addMessage("SavedSuccessfully", "SavedSuccessfully");
      } else {
        conn.rollback();
      }
    }
    catch (Exception e) {
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
  }
  
  public void saveUsrData2(UserManagmentAction action, int id) {
    UserManagmentDataTable dto = new UserManagmentDataTable();
    Connection conn = null;
    ArrayList list = new ArrayList();
    conn = ConnectionToDataBase.getConnection();
    PreparedStatement pstm = null;
    try { conn.setAutoCommit(false);
      int sstatus = 0;
      
      ResultSet rs = null;
      
      int status = 0;
      
      String query1 = "INSERT INTO LDAADMIN.JBP_ROLE_MEMBERSHIP(JBP_UID,JBP_RID)  VALUES(?,?)";
      

      pstm = conn.prepareStatement(query1);
      
      pstm.setInt(1, id);
      
      pstm.setString(2, action.getRoleid());
      status = pstm.executeUpdate();
      



      if (status > 0) {
        conn.commit();
        ResourceUtil.addMessage("SavedSuccessfully", "SavedSuccessfully");
      } else {
        conn.rollback();
      }
    }
    catch (Exception e) {
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
  }
  
  public void saveRoleDataold(UserManagmentAction action) {
    UserManagmentDataTable dto = new UserManagmentDataTable();
    Connection conn = null;
    ArrayList list = new ArrayList();
    conn = ConnectionToDataBase.getConnection();
    PreparedStatement pstm = null;
    try
    {
      String querySelect = 
        "\tselect jbp_uid from jbp_users \twhere jbp_uname = ?";
      
      pstm = conn.prepareStatement(querySelect);
      pstm.setString(1, action.getEmpIDForMgmt().trim());
      ResultSet rs = pstm.executeQuery();
      if (rs.next()) {
        String deleteExistRole = " DELETE FROM jbp_role_membership  where jbp_uid =? and jbp_rid!=51 ";
        
        pstm = conn.prepareStatement(deleteExistRole);
        pstm.setInt(1, rs.getInt("jbp_uid"));
        int status = pstm.executeUpdate();
        
        if (status > 0) {
          System.out.println(" DELETE STATUS : " + status + " RECORDS ARE DELETED.");
        }
      } else {
        action.setMsgPanel(true);
        

        return;
      }
      




      conn.setAutoCommit(false);
      for (int i = 0; i < action.getModuleList().size(); i++) {
        dto = (UserManagmentDataTable)action.getModuleList().get(i);
        



        if (dto.getModuleCode() == 101) {
          if (dto.isCheckAdmin()) {
            saveRole(action.getEmpIDForMgmt().trim(), 45, conn);
          }
          if (dto.isCheckUser()) {
            saveRole(action.getEmpIDForMgmt().trim(), 46, conn);
          }
        }
        


        if (dto.getModuleCode() == 103) {
          if (dto.isCheckAdmin()) {
            saveRole(action.getEmpIDForMgmt().trim(), 105, conn);
          }
          if (dto.isCheckUser()) {
            saveRole(action.getEmpIDForMgmt().trim(), 106, conn);
          }
        }
        



        if (dto.getModuleCode() == 104) {
          if (dto.isCheckAdmin()) {
            saveRole(action.getEmpIDForMgmt().trim(), 109, conn);
          }
          if (dto.isCheckUser()) {
            saveRole(action.getEmpIDForMgmt().trim(), 110, conn);
          }
        }
        



        if (dto.getModuleCode() == 102) {
          if (dto.isCheckAdmin()) {
            saveRole(action.getEmpIDForMgmt().trim(), 135, conn);
          }
          if (dto.isCheckUser()) {
            saveRole(action.getEmpIDForMgmt().trim(), 136, conn);
          }
        }
        if (dto.getModuleCode() == 120) {
          if (dto.isCheckAdmin()) {
            saveRole(action.getEmpIDForMgmt().trim(), 4343, conn);
          }
          if (dto.isCheckUser()) {
            saveRole(action.getEmpIDForMgmt().trim(), 4344, conn);
          }
        }
        



        if (dto.getModuleCode() == 105) {
          if (dto.isCheckAdmin()) {
            saveRole(action.getEmpIDForMgmt().trim(), 99, conn);
          }
          if (dto.isCheckUser()) {
            saveRole(action.getEmpIDForMgmt().trim(), 100, conn);
          }
        }
        


        if (dto.getModuleCode() == 106) {
          if (dto.isCheckAdmin()) {
            saveRole(action.getEmpIDForMgmt().trim(), 107, conn);
          }
          if (dto.isCheckUser()) {
            saveRole(action.getEmpIDForMgmt().trim(), 108, conn);
          }
        }
        


        if (dto.getModuleCode() == 107) {
          if (dto.isCheckAdmin()) {
            saveRole(action.getEmpIDForMgmt().trim(), 97, conn);
          }
          if (dto.isCheckUser()) {
            saveRole(action.getEmpIDForMgmt().trim(), 98, conn);
          }
        }
        




        if (dto.getModuleCode() == 108) {
          if (dto.isCheckAdmin()) {
            saveRole(action.getEmpIDForMgmt().trim(), 137, conn);
          }
          if (dto.isCheckUser()) {
            saveRole(action.getEmpIDForMgmt().trim(), 138, conn);
          }
        }
        


        if (dto.getModuleCode() == 109) {
          if (dto.isCheckAdmin()) {
            saveRole(action.getEmpIDForMgmt().trim(), 103, conn);
          }
          if (dto.isCheckUser()) {
            saveRole(action.getEmpIDForMgmt().trim(), 104, conn);
          }
        }
        


        if (dto.getModuleCode() == 110) {
          if (dto.isCheckAdmin()) {
            saveRole(action.getEmpIDForMgmt().trim(), 49, conn);
          }
          if (dto.isCheckUser()) {
            saveRole(action.getEmpIDForMgmt().trim(), 50, conn);
          }
        }
        



        if (dto.getModuleCode() == 111) {
          if (dto.isCheckAdmin()) {
            saveRole(action.getEmpIDForMgmt().trim(), 47, conn);
          }
          if (dto.isCheckUser()) {
            saveRole(action.getEmpIDForMgmt().trim(), 48, conn);
          }
        }
        



        if (dto.getModuleCode() == 112) {
          if (dto.isCheckAdmin()) {
            saveRole(action.getEmpIDForMgmt().trim(), 95, conn);
          }
          if (dto.isCheckUser()) {
            saveRole(action.getEmpIDForMgmt().trim(), 96, conn);
          }
        }
        


        if (dto.getModuleCode() == 113) {
          if (dto.isCheckAdmin()) {
            saveRole(action.getEmpIDForMgmt().trim(), 43, conn);
          }
          if (dto.isCheckUser()) {
            saveRole(action.getEmpIDForMgmt().trim(), 44, conn);
          }
        }
        


        if (dto.getModuleCode() == 114) {
          if (dto.isCheckAdmin()) {
            saveRole(action.getEmpIDForMgmt().trim(), 111, conn);
          }
          if (dto.isCheckUser()) {
            saveRole(action.getEmpIDForMgmt().trim(), 112, conn);
          }
        }
        


        if (dto.getModuleCode() == 115) {
          if (dto.isCheckAdmin()) {
            saveRole(action.getEmpIDForMgmt().trim(), 113, conn);
          }
          if (dto.isCheckUser()) {
            saveRole(action.getEmpIDForMgmt().trim(), 114, conn);
          }
        }
        


        if (dto.getModuleCode() == 116) {
          if (dto.isCheckAdmin()) {
            saveRole(action.getEmpIDForMgmt().trim(), 101, conn);
          }
          if (dto.isCheckUser()) {
            saveRole(action.getEmpIDForMgmt().trim(), 102, conn);
          }
        }
        



        if (dto.getModuleCode() == 117) {
          if (dto.isCheckAdmin()) {
            saveRole(action.getEmpIDForMgmt().trim(), 139, conn);
          }
          if (dto.isCheckUser()) {
            saveRole(action.getEmpIDForMgmt().trim(), 140, conn);
          }
        }
        


        if (dto.getModuleCode() == 118) {
          if (dto.isCheckAdmin()) {
            saveRole(action.getEmpIDForMgmt().trim(), 41, conn);
          }
          if (dto.isCheckUser()) {
            saveRole(action.getEmpIDForMgmt().trim(), 42, conn);
          }
        }
        


        if (dto.getModuleCode() == 119) {
          if (dto.isCheckAdmin()) {
            saveRole(action.getEmpIDForMgmt().trim(), 4286, conn);
          }
          if (dto.isCheckUser()) {
            saveRole(action.getEmpIDForMgmt().trim(), 4387, conn);
          }
        }
        


        if (dto.getModuleCode() == 122) {
          if (dto.isCheckAdmin()) {
            saveRole(action.getEmpIDForMgmt().trim(), 4202, conn);
          }
          if (dto.isCheckUser()) {
            saveRole(action.getEmpIDForMgmt().trim(), 4382, conn);
          }
        }
        


        if (dto.getModuleCode() == 121) {
          if (dto.isCheckAdmin()) {
            saveRole(action.getEmpIDForMgmt().trim(), 4383, conn);
          }
          if (dto.isCheckUser()) {
            saveRole(action.getEmpIDForMgmt().trim(), 4384, conn);
          }
        }
        


        if (dto.getModuleCode() == 123) {
          if (dto.isCheckAdmin()) {
            saveRole(action.getEmpIDForMgmt().trim(), 4388, conn);
          }
          if (dto.isCheckUser()) {
            saveRole(action.getEmpIDForMgmt().trim(), 4389, conn);
          }
        }
        


        if (dto.getModuleCode() == 126) {
          if (dto.isCheckAdmin()) {
            saveRole(action.getEmpIDForMgmt().trim(), 4392, conn);
          }
          if (dto.isCheckUser()) {
            saveRole(action.getEmpIDForMgmt().trim(), 4393, conn);
          }
        }
        


        if (dto.getModuleCode() == 124) {
          if (dto.isCheckAdmin()) {
            saveRole(action.getEmpIDForMgmt().trim(), 4390, conn);
          }
          if (dto.isCheckUser()) {
            saveRole(action.getEmpIDForMgmt().trim(), 4391, conn);
          }
        }
        


        if (dto.getModuleCode() == 125) {
          if (dto.isCheckAdmin()) {
            saveRole(action.getEmpIDForMgmt().trim(), 4394, conn);
          }
          if (dto.isCheckUser()) {
            saveRole(action.getEmpIDForMgmt().trim(), 4395, conn);
          }
        }
      }
      

      int status = 0;
      String temp = "";
      String temp1 = "";
      String querySelect2 = 
        "select JBP_UID from ldaadmin.JBP_USERS where JBP_UNAME = ?";
      pstm = conn.prepareStatement(querySelect2);
      pstm.setString(1, action.getEmpIDForMgmt().trim());
      rs = pstm.executeQuery();
      while (rs.next()) {
        temp1 = rs.getString("JBP_UID");
        list.add(temp1);
      }
      

      String querySelect1 = 
        "select jbp_rid from ldaadmin.jbp_role_membership where JBP_UID = ?";
      
      pstm = conn.prepareStatement(querySelect1);
      pstm.setString(1, temp1);
      rs = pstm.executeQuery();
      while (rs.next()) {
        temp = rs.getString("jbp_rid");
        list.add(temp);
      }
      if ((temp != null) && (temp.equalsIgnoreCase("4362")))
      {
        status = pstm.executeUpdate();
      } else {
        String query1 = "INSERT INTO LDAADMIN.JBP_ROLE_MEMBERSHIP(JBP_UID,JBP_RID)  VALUES(?,?)";
        

        pstm = conn.prepareStatement(query1);
        System.out.println("----" + temp1);
        pstm.setString(1, temp1);
        pstm.setInt(2, 4362);
        status = pstm.executeUpdate();
      }
      

      if ((conn != null) && (status > 0)) {
        conn.commit();
        ResourceUtil.addErrorMessage("SavedSuccessfully", "SavedSuccessfully");
      }
    }
    catch (Exception e) {
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
      if (conn != null) {
        try {
          conn.close();
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
  }
  
  private boolean saveRole(String userID, int roleID, Connection conn)
  {
    PreparedStatement pstmt = null;
    PreparedStatement pstmt1 = null;
    ResultSet rs = null;
    int saveStatus1 = 0;
    try {
      String querySelect = 
        "\tselect jbp_uid from jbp_users \twhere jbp_uname = ?";
      


      pstmt = conn.prepareStatement(querySelect);
      pstmt.setString(1, userID);
      rs = pstmt.executeQuery();
      if (rs.next())
      {


        pstmt = null;
        String queryRole = " insert into jbp_role_membership  (jbp_uid,jbp_rid) VALUES(?,?) ";
        
        pstmt1 = conn.prepareStatement(queryRole);
        pstmt1.setInt(1, rs.getInt("jbp_uid"));
        System.out.println("roleID-------------------------------------" + roleID + "--" + rs.getInt("jbp_uid"));
        pstmt1.setInt(2, roleID);
        saveStatus1 = pstmt1.executeUpdate();
        if (saveStatus1 > 0) {
          return true;
        }
        return false;
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    } finally {
      if (pstmt != null) {
        try {
          pstmt.close();
        }
        catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
    if (pstmt != null) {
      try {
        pstmt.close();
      }
      catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return false;
  }
  
  public void insertData(UserManagmentAction action) {
    Connection conn = ConnectionToDataBase.getConnection2();
    Connection con = ConnectionToDataBase.getConnection();
    PreparedStatement psmt = null;
    ResultSet rs = null;
    
    int saveStatus = 0;
    int UID = 0;
    try {
      conn.setAutoCommit(false);
      String query = "update PAYROLL.ESTB_M_EMPLOYEE_DETAIL set VCH_USR_ID=? where INT_EMPLOYEE_ID_P=?";
      
      psmt = conn.prepareStatement(query);
      


      psmt.setString(1, action.getUserid());
      psmt.setString(2, action.getEmpIDForMgmt());
      
      saveStatus = psmt.executeUpdate();
      if (saveStatus > 0)
      {
        saveStatus = 0;
        String userSQ = "SELECT  MAX(JBP_UID) AS USER_ID FROM  JBP_USERS";
        psmt = con.prepareStatement(userSQ);
        
        rs = psmt.executeQuery();
        while (rs.next())
        {
          UID = rs.getInt(1);
        }
        
        String query1 = "INSERT INTO LDAADMIN.JBP_USERS(JBP_UID,JBP_UNAME,JBP_GIVENNAME,JBP_PASSWORD,JBP_REALEMAIL,JBP_ENABLED,JBP_VIEWREALEMAIL)  VALUES(?,?,?,?,?,?,?)";
        

        psmt = con.prepareStatement(query1);
        int Seq = UID + 1;
        

        System.out.println("Seq===========================================--------------  " + Seq);
        psmt.setInt(1, Seq);
        

        psmt.setString(3, action.getUserid());
        
        psmt.setString(2, action.getEmpIDForMgmt());
        psmt.setString(4, CommonImpl.encryptMD5(action.getPassword()));
        psmt.setString(5, action.getEmail());
        psmt.setInt(6, 1);
        psmt.setInt(7, 1);
        saveStatus = psmt.executeUpdate();
      }
      
      if (saveStatus > 0)
      {
        saveStatus = 0;
        String query1 = "INSERT INTO LDAADMIN.JBP_ROLE_MEMBERSHIP(JBP_UID,JBP_RID)  VALUES(?,?)";
        

        psmt = con.prepareStatement(query1);
        


        psmt.setInt(1, UID);
        psmt.setInt(2, 2);
        saveStatus = psmt.executeUpdate();
      }
      
      if (saveStatus > 0)
      {
        saveStatus = 0;
        String query1 = "INSERT INTO LDAADMIN.JBP_ROLE_MEMBERSHIP(JBP_UID,JBP_RID)  VALUES(?,?)";
        

        psmt = con.prepareStatement(query1);
        


        psmt.setInt(1, UID);
        psmt.setInt(2, 4362);
        saveStatus = psmt.executeUpdate();
      }
      
      if (saveStatus > 0) {
        conn.commit();
        ResourceUtil.addMessage("USER_CREATED", "USER_CREATED");
        action.resetData();
      }
      else
      {
        ResourceUtil.addErrorMessage("USER_NT_CREATED", "USER_NT_CREATED");
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      


      try
      {
        if (conn != null)
        {
          conn.close();
        }
        if (con != null)
        {
          con.close();
        }
      }
      catch (SQLException e1)
      {
        e1.printStackTrace();
      }
    }
    finally
    {
      try
      {
        if (conn != null)
        {
          conn.close();
        }
        if (con != null)
        {
          con.close();
        }
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
    }
  }
  



  private int getSeqId(Connection conn)
  {
    int id = 0;
    
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    try
    {
      String queryList = "SELECT LDAADMIN.USER_SEQ.nextval FROM dual";
      conn = ConnectionToDataBase.getConnection();
      pstmt = conn.prepareStatement(queryList);
      rs = pstmt.executeQuery();
      while (rs.next())
      {
        id = rs.getInt(1);
      }
    }
    catch (SQLException se)
    {
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
    return id;
  }
  
  public static ArrayList employeeidList(String emp) {
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
      String query1 = " SELECT INT_EMPLOYEE_ID_P,VCH_EMPLOYEE_NM  \tFROM PAYROLL.ESTB_M_EMPLOYEE_DETAIL     WHERE INT_EMPLOYEE_ID_P = " + 
      
        emp + " and VCH_EMP_STATUS IN ('A','S') " + 
        "\tORDER BY INT_EMPLOYEE_ID_P ASC ";
      
      String query = " SELECT INT_EMPLOYEE_ID_P,VCH_EMPLOYEE_NM  \tFROM PAYROLL.ESTB_M_EMPLOYEE_DETAIL     WHERE VCH_EMP_STATUS IN ('A','S') \tORDER BY INT_EMPLOYEE_ID_P ASC ";
      



      conn = ConnectionToDataBase.getConnection2();
      if ((emp != null) && (emp.length() > 0)) {
        pstmt = conn.prepareStatement(query1);
      } else {
        pstmt = conn.prepareStatement(query);
      }
      
      rs = pstmt.executeQuery();
      while (rs.next())
      {
        item = new SelectItem();
        item.setLabel(rs.getString("INT_EMPLOYEE_ID_P"));
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
  
  public static ArrayList employeenmList(String nm) {
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
      String query1 = " SELECT INT_EMPLOYEE_ID_P,VCH_EMPLOYEE_NM  \tFROM PAYROLL.ESTB_M_EMPLOYEE_DETAIL     WHERE INT_EMPLOYEE_ID_P = " + 
      
        nm + " and VCH_EMP_STATUS IN ('A','S') " + 
        "\tORDER BY VCH_EMPLOYEE_NM ASC ";
      
      String query = " SELECT INT_EMPLOYEE_ID_P,VCH_EMPLOYEE_NM  \tFROM PAYROLL.ESTB_M_EMPLOYEE_DETAIL     WHERE VCH_EMP_STATUS IN ('A','S') \tORDER BY VCH_EMPLOYEE_NM ASC ";
      



      conn = ConnectionToDataBase.getConnection2();
      if ((nm != null) && (nm.length() > 0))
      {
        pstmt = conn.prepareStatement(query1);
      } else {
        pstmt = conn.prepareStatement(query);
      }
      
      rs = pstmt.executeQuery();
      while (rs.next())
      {
        item = new SelectItem();
        item.setLabel(rs.getString("VCH_EMPLOYEE_NM"));
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
  
  public static ArrayList idList(String emp) { ArrayList list = new ArrayList();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    SelectItem item = new SelectItem();
    item.setValue("");
    item.setLabel("---SELECT---");
    list.add(item);
    


    try
    {
      String query1 = " SELECT INT_EMPLOYEE_ID_P,VCH_EMPLOYEE_NM  \tFROM PAYROLL.ESTB_M_EMPLOYEE_DETAIL     WHERE INT_EMPLOYEE_ID_P = " + 
      
        emp + " and VCH_EMP_STATUS IN ('A','S') " + 
        "\tORDER BY VCH_EMPLOYEE_NM ASC ";
      


      conn = ConnectionToDataBase.getConnection2();
      
      pstmt = conn.prepareStatement(query1);
      

      rs = pstmt.executeQuery();
      while (rs.next())
      {
        item = new SelectItem();
        item.setLabel(rs.getString("INT_EMPLOYEE_ID_P"));
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
}