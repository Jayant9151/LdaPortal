package com.mentor.jboss.home.datatable;

public class UserManagmentDataTable
{
  private String moduleID = null;
  private int serailNo = 1;
  private String moduleName = null;
  private boolean checkAdmin = false;
  private boolean checkUser = false;
  
  private int moduleCode = 0;
  private boolean check = false;
  
  private String roleName = null;
  private int roleid = 0;
  private boolean rolecheck = false;
  private int serailNorole = 1;
  private String uid = null;
  private String usrnm = null;
  private boolean usrcheck = false;
  

  public UserManagmentDataTable() {}
  
  public boolean isUsrcheck()
  {
    return usrcheck;
  }
  
  public void setUsrcheck(boolean usrcheck) { this.usrcheck = usrcheck; }
  
  public String getUid() {
    return uid;
  }
  
  public void setUid(String uid) { this.uid = uid; }
  
  public String getUsrnm() {
    return usrnm;
  }
  
  public void setUsrnm(String usrnm) { this.usrnm = usrnm; }
  
  public int getSerailNorole() {
    return serailNorole;
  }
  
  public void setSerailNorole(int serailNorole) { this.serailNorole = serailNorole; }
  
  public String getRoleName() {
    return roleName;
  }
  
  public void setRoleName(String roleName) { this.roleName = roleName; }
  
  public int getRoleid()
  {
    return roleid;
  }
  
  public void setRoleid(int roleid) { this.roleid = roleid; }
  
  public boolean isRolecheck() {
    return rolecheck;
  }
  
  public void setRolecheck(boolean rolecheck) { this.rolecheck = rolecheck; }
  
  public boolean isCheck() {
    return check;
  }
  
  public void setCheck(boolean check) { this.check = check; }
  
  public int getModuleCode() {
    return moduleCode;
  }
  
  public void setModuleCode(int moduleCode) { this.moduleCode = moduleCode; }
  
  public int getSerailNo() {
    return serailNo;
  }
  
  public void setSerailNo(int serailNo) { this.serailNo = serailNo; }
  
  public String getModuleID() {
    return moduleID;
  }
  
  public void setModuleID(String moduleID) { this.moduleID = moduleID; }
  
  public boolean isCheckAdmin() {
    return checkAdmin;
  }
  
  public void setCheckAdmin(boolean checkAdmin) { this.checkAdmin = checkAdmin; }
  
  public boolean isCheckUser() {
    return checkUser;
  }
  
  public void setCheckUser(boolean checkUser) { this.checkUser = checkUser; }
  
  public String getModuleName() {
    return moduleName;
  }
  
  public void setModuleName(String moduleName) { this.moduleName = moduleName; }
}