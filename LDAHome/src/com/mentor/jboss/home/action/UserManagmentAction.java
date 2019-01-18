package com.mentor.jboss.home.action;

import com.mentor.jboss.home.common.CommonImpl;
import com.mentor.jboss.home.datatable.UserManagmentDataTable;
import com.mentor.jboss.home.impl.UserManagmentImpl;
import com.mentor.uplc.utility.ResourceUtil;
import com.mentor.uplc.utility.Validate;
import java.util.ArrayList;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;








public class UserManagmentAction
{
  private String sectionIDForMgmt = "";
  private String empIDForMgmt = "";
  
  private String sectionIDForAct = "";
  private String empIDForAct = "";
  
  private String sectionIDForReset = "";
  private String empIDForReset = "";
  
  private boolean mgmtFlag = false;
  private boolean actFlag = false;
  private boolean resetFlag = false;
  
  private ArrayList sectionList = new ArrayList();
  private ArrayList empList = new ArrayList();
  
  private ArrayList<UserManagmentDataTable> moduleList = new ArrayList();
  private ArrayList<UserManagmentDataTable> roleList = new ArrayList();
  private boolean roleflg = false;
  
  private ArrayList<UserManagmentDataTable> emprolList = new ArrayList();
  private String radio = "E";
  private String modulid;
  private String roleid;
  private ArrayList modulList = new ArrayList();
  private ArrayList roleidList = new ArrayList();
  private String empid;
  private String empnm;
  private ArrayList empidList = new ArrayList();
  private ArrayList empnmList = new ArrayList();
  
  public UserManagmentAction() {}
  
  public String getEmpid() { return empid; }
  
  public void setEmpid(String empid)
  {
    this.empid = empid;
  }
  
  public String getEmpnm() {
    return empnm;
  }
  
  public void setEmpnm(String empnm) {
    this.empnm = empnm;
  }
  
  public ArrayList getEmpidList()
  {
    UserManagmentImpl impl = new UserManagmentImpl();
    empidList = UserManagmentImpl.employeeidList(empnm);
    return empidList;
  }
  
  public void setEmpidList(ArrayList empidList) {
    this.empidList = empidList;
  }
  
  public ArrayList getEmpnmList()
  {
    UserManagmentImpl impl = new UserManagmentImpl();
    empnmList = UserManagmentImpl.employeenmList(empid);
    return empnmList;
  }
  
  public void setEmpnmList(ArrayList empnmList) {
    this.empnmList = empnmList;
  }
  
  public ArrayList<UserManagmentDataTable> getEmprolList() {
    return emprolList;
  }
  
  public void setEmprolList(ArrayList<UserManagmentDataTable> emprolList) {
    this.emprolList = emprolList;
  }
  

  public String getModulid()
  {
    return modulid;
  }
  
  public void setModulid(String modulid) {
    this.modulid = modulid;
  }
  
  public String getRoleid() {
    return roleid;
  }
  
  public void setRoleid(String roleid) {
    this.roleid = roleid;
  }
  
  public ArrayList getModulList() {
    try {
      modulList = CommonImpl.moduleList();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return modulList;
  }
  
  public void setModulList(ArrayList modulList) {
    this.modulList = modulList;
  }
  
  public ArrayList getRoleidList() {
    try {
      if (modulid != null) {
        roleidList = CommonImpl.roleList(modulid);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return roleidList;
  }
  
  public void setRoleidList(ArrayList roleidList) {
    this.roleidList = roleidList;
  }
  
  public String getRadio() {
    return radio;
  }
  
  public void setRadio(String radio) {
    this.radio = radio;
  }
  
  public boolean isRoleflg() {
    return roleflg;
  }
  
  public void setRoleflg(boolean roleflg) {
    this.roleflg = roleflg;
  }
  
  public ArrayList<UserManagmentDataTable> getRoleList() {
    return roleList;
  }
  
  public void setRoleList(ArrayList<UserManagmentDataTable> roleList) {
    this.roleList = roleList;
  }
  

  private boolean moduleFlag = false;
  
  private String userActivateDeActivateStatus = "Active";
  
  private String newPassword = null;
  private String confirmPassword = null;
  
  public String getNewPassword() {
    return newPassword;
  }
  
  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }
  

  public String getConfirmPassword()
  {
    return confirmPassword;
  }
  
  public void setConfirmPassword(String confirmPassword) {
    this.confirmPassword = confirmPassword;
  }
  
  public ArrayList getSectionList() {
    try {
      sectionList = CommonImpl.sectionList();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return sectionList;
  }
  
  public boolean isMgmtFlag() {
    return mgmtFlag;
  }
  
  public void setMgmtFlag(boolean mgmtFlag) {
    this.mgmtFlag = mgmtFlag;
  }
  
  public boolean isActFlag() {
    return actFlag;
  }
  
  public void setActFlag(boolean actFlag) {
    this.actFlag = actFlag;
  }
  
  public boolean isResetFlag() {
    return resetFlag;
  }
  
  public void setResetFlag(boolean resetFlag) {
    this.resetFlag = resetFlag;
  }
  
  public void setSectionList(ArrayList sectionList) {
    this.sectionList = sectionList;
  }
  
  public ArrayList getEmpList() {
    try {
      if (mgmtFlag) {
        empList = CommonImpl.employeeList(sectionIDForMgmt);
      }
      else if (actFlag) {
        empList = CommonImpl.employeeList(sectionIDForAct);
      }
      else if (resetFlag) {
        empList = CommonImpl.employeeList(sectionIDForReset);
      }
      else {
        empList = CommonImpl.employeeList(null);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return empList;
  }
  
  public void setEmpList(ArrayList empList) {
    this.empList = empList;
  }
  
  public String getUserActivateDeActivateStatus()
  {
    return userActivateDeActivateStatus;
  }
  
  public String getSectionIDForMgmt() {
    return sectionIDForMgmt;
  }
  
  public void setSectionIDForMgmt(String sectionIDForMgmt) {
    this.sectionIDForMgmt = sectionIDForMgmt;
  }
  
  public String getEmpIDForMgmt() {
    return empIDForMgmt;
  }
  
  public void setEmpIDForMgmt(String empIDForMgmt) {
    this.empIDForMgmt = empIDForMgmt;
  }
  
  public String getSectionIDForAct() {
    return sectionIDForAct;
  }
  
  public void setSectionIDForAct(String sectionIDForAct) {
    this.sectionIDForAct = sectionIDForAct;
  }
  
  public String getEmpIDForAct() {
    return empIDForAct;
  }
  
  public void setEmpIDForAct(String empIDForAct) {
    this.empIDForAct = empIDForAct;
  }
  
  public String getSectionIDForReset() {
    return sectionIDForReset;
  }
  
  public void setSectionIDForReset(String sectionIDForReset) {
    this.sectionIDForReset = sectionIDForReset;
  }
  
  public String getEmpIDForReset() {
    return empIDForReset;
  }
  
  public void setEmpIDForReset(String empIDForReset) {
    this.empIDForReset = empIDForReset;
  }
  
  public void setUserActivateDeActivateStatus(String userActivateDeActivateStatus) {
    this.userActivateDeActivateStatus = userActivateDeActivateStatus;
  }
  





  public ArrayList<UserManagmentDataTable> getModuleList()
  {
    return moduleList;
  }
  
  public void setModuleList(ArrayList<UserManagmentDataTable> moduleList) {
    this.moduleList = moduleList;
  }
  
  public boolean isModuleFlag() {
    return moduleFlag;
  }
  
  public void setModuleFlag(boolean moduleFlag) {
    this.moduleFlag = moduleFlag;
  }
  
  public String getUserCurrentStatus() {
    return userCurrentStatus;
  }
  
  public void setUserCurrentStatus(String userCurrentStatus) {
    this.userCurrentStatus = userCurrentStatus;
  }
  
  public void changeListnerMgmt(ValueChangeEvent evt) {
    mgmtFlag = true;
    actFlag = false;
    resetFlag = false;
  }
  
  public void changeListnerAct(ValueChangeEvent evt) {
    mgmtFlag = false;
    actFlag = true;
    resetFlag = false;
  }
  
  public void changeListnerReset(ValueChangeEvent evt) {
    mgmtFlag = false;
    actFlag = false;
    resetFlag = true;
  }
  


  public void userMangeListner(ValueChangeEvent evt) {}
  


  public void empidlistnr(ValueChangeEvent evt) {}
  


  private String userCurrentStatus = "Select Section/Employee to Check Login status.";
  private boolean userStatus = false;
  
  public void empnmlistnr(ValueChangeEvent evt) {}
  
  public boolean isUserStatus() {
    return userStatus; }
  
  public void setUserStatus(boolean userStatus)
  {
    this.userStatus = userStatus;
  }
  
  public void changeEmployeeListner(ValueChangeEvent evt) {
    String empCode = (String)evt.getNewValue();
    if ((empCode == null) || (empCode.equalsIgnoreCase("0"))) {
      return;
    }
    if (CommonImpl.currentStatusMethod(empCode)) {
      userCurrentStatus = "Currently Selected User is Active";
      userActivateDeActivateStatus = "Deactivate this Account";
      userStatus = true;
    } else {
      userCurrentStatus = "Currently Selected User is De-active";
      userActivateDeActivateStatus = "Activate this Account";
      userStatus = false;
    }
  }
  
  public String resetData() { userCurrentStatus = "Select Section/Employee to Check Login status.";
    userStatus = false;
    sectionIDForAct = "0";
    sectionIDForMgmt = "0";
    sectionIDForReset = "0";
    empIDForAct = "0";
    empIDForMgmt = "0";
    empIDForReset = "0";
    mgmtFlag = false;
    actFlag = false;
    resetFlag = false;
    moduleFlag = false;
    userActivateDeActivateStatus = "Activate";
    newPassword = null;
    confirmPassword = null;
    setMsgPanel(false);
    setNewUser(false);
    newUser = false;
    setUserid(null);
    setRepassword(null);
    setPassword(null);
    setEmail(null);
    moduleList.clear();
    roleList.clear();
    empid = null;empnm = null;
    empnmList.clear();
    empidList.clear();
    radio = "E";
    emprolList.clear();
    modulid = null;
    roleid = null;
    return "";
  }
  
  public String clear() {
    setRepassword(null);
    setPassword(null);
    setEmail(null);
    
    return ""; }
  
  private boolean msgPanel = false;
  
  public boolean isMsgPanel() {
    return msgPanel;
  }
  
  public void setMsgPanel(boolean msgPanel) {
    this.msgPanel = msgPanel;
  }
  
  public String save1() {
    if ((roleid != null) && (roleid.length() > 0) && (modulid != null) && (modulid.length() > 0))
    {
      UserManagmentImpl impl = new UserManagmentImpl();
      
      impl.saveUsrData(this);
    }
    

    return "";
  }
  
  public String save2() {
    int id = 0;
    if ((roleid != null) && (roleid.length() > 0) && (modulid != null) && (modulid.length() > 0)) {
      if (((empid != null) && (empid.length() > 0)) || ((empnm != null) && (empnm.length() > 0)))
      {
        UserManagmentImpl impl = new UserManagmentImpl();
        if (empid != null) {
          id = impl.usrcode1(empid);
        }
        else {
          id = impl.usrcode1(empnm);
        }
        impl.saveUsrData2(this, id);
        emprolList.clear();
        
        emprolList = impl.rolewiseEmpMenuList(this);
      } else {
        ResourceUtil.addMessage("SELECT_ALL_MANDATORY", "SELECT_ALL_MANDATORY");
      }
    } else {
      ResourceUtil.addMessage("SELECT_ALL_MANDATORY", "SELECT_ALL_MANDATORY");
    }
    
    return "";
  }
  
  public String saveRoleMgmt() {
    if ((sectionIDForMgmt == null) || (sectionIDForMgmt.equalsIgnoreCase("0")) || (sectionIDForMgmt.equalsIgnoreCase(""))) {
      ResourceUtil.addErrorMessage("selectSection", "selectSection");
      return "";
    }
    
    if ((empIDForMgmt == null) || (empIDForMgmt.equalsIgnoreCase("0")) || (empIDForMgmt.equalsIgnoreCase(""))) {
      ResourceUtil.addErrorMessage("selectEmployee", "selectEmployee");
      return "";
    }
    
    UserManagmentImpl impl = new UserManagmentImpl();
    
    impl.saveRoleData(this);
    
    return "";
  }
  
  public String saveActivation() {
    if ((sectionIDForAct == null) || (sectionIDForAct.equalsIgnoreCase("0")) || (sectionIDForAct.equalsIgnoreCase(""))) {
      ResourceUtil.addErrorMessage("selectSection", "selectSection");
      return "";
    }
    
    if ((empIDForAct == null) || (empIDForAct.equalsIgnoreCase("0")) || (empIDForAct.equalsIgnoreCase(""))) {
      ResourceUtil.addErrorMessage("selectEmployee", "selectEmployee");
      return "";
    }
    UserManagmentImpl impl = new UserManagmentImpl();
    impl.deActivationMethod(empIDForAct, userStatus, this);
    return "";
  }
  
  public String saveResetPassword() {
    if ((sectionIDForReset == null) || (sectionIDForReset.equalsIgnoreCase("0")) || (sectionIDForReset.equalsIgnoreCase(""))) {
      ResourceUtil.addErrorMessage("selectSection", "selectSection");
      return "";
    }
    
    if ((empIDForReset == null) || (empIDForReset.equalsIgnoreCase("0")) || (empIDForReset.equalsIgnoreCase(""))) {
      ResourceUtil.addErrorMessage("selectEmployee", "selectEmployee");
      return "";
    }
    











    UserManagmentImpl impl = new UserManagmentImpl();
    impl.updatePassword(empIDForReset, this);
    



    return ""; }
  
  private boolean newUser = false;
  private String userid = "";
  private String email = "";
  private String password = "";
  private String repassword = "";
  private boolean validateInputs;
  
  public String getUserid() {
    userid = getEmpIDForMgmt();
    return userid;
  }
  
  public void setUserid(String userid) {
    this.userid = userid;
  }
  
  public String getEmail() {
    return email;
  }
  
  public void setEmail(String email) {
    this.email = email;
  }
  
  public String getPassword() {
    return password;
  }
  
  public void setPassword(String password) {
    this.password = password;
  }
  
  public String getRepassword() {
    return repassword;
  }
  
  public void setRepassword(String repassword) {
    this.repassword = repassword;
  }
  
  public boolean isNewUser() {
    return newUser;
  }
  
  public void setNewUser(boolean newUser) {
    this.newUser = newUser;
  }
  
  public boolean isValidateInputs()
  {
    validateInputs = true;
    if (!Validate.validateStrReq("userid", getUserid())) { validateInputs = false;
    } else if (!Validate.validateStrReq("EmailId", getEmail())) { validateInputs = false;
    } else if (!Validate.validateStrReqEmail("EmailId", getEmail())) { validateInputs = false;
    } else if (!Validate.validateStrReq("password", getPassword().trim())) { validateInputs = false;
    } else if (!Validate.validateStrReq("repassword", getRepassword().trim())) { validateInputs = false;
    }
    else if (validateInputs)
      if ((getPassword() != null) && (getPassword().length() < 5)) {
        ResourceUtil.addMessage("passwordLength", "passwordLength");
        validateInputs = false;
      } else { validateInputs = true;
      }
    if (validateInputs)
    {
      if (getPassword().equals(getRepassword())) {
        validateInputs = true;
      }
      else {
        validateInputs = false;
        ResourceUtil.addMessage("confirmPasswordValid", "confirmPasswordValid");
      }
    }
    return validateInputs;
  }
  
  public void setValidateInputs(boolean validateInputs) {
    this.validateInputs = validateInputs;
  }
  
  public void newUserListener(ActionEvent ac)
  {
    setUserid(empIDForMgmt);
  }
  

  public String saveUser()
  {
    if (isValidateInputs())
    {

      UserManagmentImpl impl = new UserManagmentImpl();
      impl.insertData(this); }
    return "";
  }
  

  public void showDedList(ActionEvent ace)
  {
    ArrayList arr = new ArrayList();
    String var = "";
    roleflg = false;
    for (int i = 0; i < moduleList.size(); i++)
    {
      UserManagmentDataTable dt = (UserManagmentDataTable)getModuleList().get(i);
      
      if (dt.isCheck())
      {
        var = var + dt.getModuleID() + ",";
        arr.add(dt);
      }
    }
    
    if (arr.size() > 0)
    {
      roleflg = true;
      
      UserManagmentDataTable dt = (UserManagmentDataTable)arr.get(0);
      UserManagmentImpl impl = new UserManagmentImpl();
      int id = impl.usrcode(this);
      roleList = impl.displayRoleList(var, id);

    }
    else
    {
      roleflg = false;
    }
  }
  




  public String viewData2()
  {
    emprolList.clear();
    
    UserManagmentImpl impl = new UserManagmentImpl();
    emprolList = impl.rolewiseEmpMenuList(this);
    
    return "";
  }
  


  public String viewData()
  {
    moduleFlag = true;
    if (moduleFlag) {
      UserManagmentImpl impl = new UserManagmentImpl();
      moduleList = impl.displayMenuList(this);
      moduleFlag = false;
      roleList.clear();
    }
    return "";
  }
}