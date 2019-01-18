

package com.mentor.jboss.home.action;

import com.mentor.jboss.home.datatable.ApplicationEntryDataTable;
import com.mentor.jboss.home.impl.ApplicationEntryImpl;
import java.util.ArrayList;


public class ApplicationEntryAction
{
  public ApplicationEntryAction() {}
  
  private ArrayList<ApplicationEntryDataTable> displayNotificationProposalList = new ArrayList();
  private String noRecordsText = "Current Offering: Registrations open for";
  private String noRecordsText2 = "Currently No Registrations are open.";
  private boolean displayFlag = true;
  private boolean renderTable = true;
  
  public ArrayList<ApplicationEntryDataTable> getDisplayNotificationProposalList() {
    return displayNotificationProposalList;
  }
  
  public void setDisplayNotificationProposalList(ArrayList<ApplicationEntryDataTable> displayNotificationProposalList)
  {
    this.displayNotificationProposalList = displayNotificationProposalList;
  }
  
  public String getNoRecordsText2() {
    return noRecordsText2;
  }
  
  public void setNoRecordsText2(String noRecordsText2) {
    this.noRecordsText2 = noRecordsText2;
  }
  
  public boolean isRenderTable() {
    return renderTable;
  }
  
  public void setRenderTable(boolean renderTable) {
    this.renderTable = renderTable;
  }
  
  public boolean isDisplayFlag() {
    return displayFlag;
  }
  
  public void setDisplayFlag(boolean displayFlag) {
    this.displayFlag = displayFlag;
  }
  
  public String getNoRecordsText() {
    try {
      if (isDisplayFlag()) {
        ApplicationEntryImpl impl = new ApplicationEntryImpl();
        displayNotificationProposalList = impl.getDisplayNotificationProposal();
        if ((displayNotificationProposalList != null) && (displayNotificationProposalList.size() > 0)) {
          renderTable = true;
        } else {
          renderTable = false;
        }
        displayFlag = false;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return noRecordsText;
  }
  
  public void setNoRecordsText(String noRecordsText) {
    this.noRecordsText = noRecordsText;
  }
}



