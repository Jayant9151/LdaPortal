package com.mentor.jboss.home.action;

import com.mentor.jboss.home.datatable.SideMenuDataTable;
import com.mentor.jboss.home.impl.SideMenuImpl;
import java.util.ArrayList;



public class SideMenuAction
{
  private ArrayList<SideMenuDataTable> subMenuList = new ArrayList();
  
  private boolean displayFlag = false;
  
  public SideMenuAction() {}
  
  public ArrayList<SideMenuDataTable> getSubMenuList() { SideMenuImpl impl = new SideMenuImpl();
    
    subMenuList = impl.displaySubMenuList(this);
    
    return subMenuList;
  }
  
  public void setSubMenuList(ArrayList<SideMenuDataTable> subMenuList) {
    this.subMenuList = subMenuList;
  }
  
  public boolean isDisplayFlag() {
    return displayFlag;
  }
  
  public void setDisplayFlag(boolean displayFlag) {
    this.displayFlag = displayFlag;
  }
}