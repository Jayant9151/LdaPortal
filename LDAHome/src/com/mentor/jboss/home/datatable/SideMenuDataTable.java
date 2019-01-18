package com.mentor.jboss.home.datatable;

public class SideMenuDataTable { private String linkName;
  private String linkURL;
  
  public SideMenuDataTable() {}
  
  public String getLinkName() { return linkName; }
  
  public void setLinkName(String linkName) {
    this.linkName = linkName;
  }
  
  public String getLinkURL() { return linkURL; }
  
  public void setLinkURL(String linkURL) {
    this.linkURL = linkURL;
  }
}