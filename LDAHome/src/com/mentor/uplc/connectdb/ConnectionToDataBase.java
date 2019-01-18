

package com.mentor.uplc.connectdb;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class ConnectionToDataBase
{
  public ConnectionToDataBase() {}
  
  public static Connection getConnection()
  {
    Connection conn = null;
    
    try
    {
      InitialContext ctx = new InitialContext();
      DataSource ds = (DataSource)ctx.lookup("java:PortalDS");
      conn = ds.getConnection();

    }
    catch (NamingException e)
    {
      e.printStackTrace();

    }
    catch (SQLException e)
    {
      e.printStackTrace();
    }
    return conn;
  }
  
  public static Connection getConnection2()
  {
    Connection con = null;
    InitialContext ctx = null;
    try {
      ctx = new InitialContext();
      DataSource ds = (DataSource)ctx.lookup("java:ldaDS");
      con = ds.getConnection();
    }
    catch (NamingException ne)
    {
      System.out.println("Exception 1" + ne.getMessage());
    }
    catch (SQLException se) {
      System.out.println("Exception 2" + se.getMessage());

    }
    catch (Exception se)
    {
      System.out.println("Exception 3" + se.getMessage());
    }
    
    return con;
  }
}

