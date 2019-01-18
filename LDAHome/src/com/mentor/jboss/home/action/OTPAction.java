package com.mentor.jboss.home.action;

import com.mentor.jboss.home.datatable.CustomHomeDataTable;
import com.mentor.jboss.home.impl.OtpImpl;
import com.mentor.uplc.utility.ResourceUtil;
import java.util.ArrayList;





public class OTPAction
{
  OtpImpl impl = new OtpImpl();
  
  private String otpinput = "";
  private boolean otpflg = false;
  private ArrayList<CustomHomeDataTable> menuLinkListotp = new ArrayList();
  

  public OTPAction() {}
  
  public String getOtpinput()
  {
    return otpinput;
  }
  
  public void setOtpinput(String otpinput) {
    this.otpinput = otpinput;
  }
  


  public String hidn()
  {
    return "";
  }
  


  public ArrayList<CustomHomeDataTable> getMenuLinkListotp()
  {
    return menuLinkListotp;
  }
  
  public void setMenuLinkListotp(ArrayList<CustomHomeDataTable> menuLinkListotp) {
    this.menuLinkListotp = menuLinkListotp;
  }
  
  public boolean isOtpflg()
  {
    return otpflg;
  }
  
  public void setOtpflg(boolean otpflg) {
    this.otpflg = otpflg;
  }
  



  public String otpmethod1()
  {
    try
    {
      impl.getAndSendMessage();
    } catch (Exception e) {
      e.printStackTrace();
    }
    

    return "";
  }
  



  public String otpmethod()
  {
    try
    {
      if (ResourceUtil.getUserNameAllReq() == null)
      {
        menuLinkListotp = new ArrayList();
      }
      else if (impl.validateotp(otpinput))
      {
        menuLinkListotp = impl.displayMenuListotp(this);
      } else {
        ResourceUtil.addMessage("WRONG_OTP", "WRONG_OTP");
      }
      
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    

    return "";
  }
}