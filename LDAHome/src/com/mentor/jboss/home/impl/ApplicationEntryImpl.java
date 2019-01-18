package com.mentor.jboss.home.impl;

import com.mentor.jboss.home.datatable.ApplicationEntryDataTable;
import com.mentor.uplc.connectdb.ConnectionToDataBase;
import com.mentor.uplc.utility.Utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;





public class ApplicationEntryImpl
{
  public ApplicationEntryImpl() {}
  
  public ArrayList<ApplicationEntryDataTable> getDisplayNotificationProposal()
  {
    ArrayList<ApplicationEntryDataTable> list = new ArrayList();
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    try
    {
      DecimalFormat myFormatter = new DecimalFormat("#0.00");
      String query = "SELECT DISTINCT E.PLDVSCHEME_NAME,F.PLDVSUBSCHEME_NAME,"
      		+ "G.PLDVSECTOR_NAME,\r\nH.PLDVDISP_PROP_TYPE_DESC,I.PLDVDISP_PROP_SUBTYPE"
      		+ "_DESC,J.PLDVARCHITECTURAL_PLAN_DESC,K.PLDVFLOOR_DESC\r\n,A.PLDISE"
      		+ "CTOR_DISP_PROP_QUANTITY,A.PRDRTOTAL_PRICE \r\n,L.PRDVELIGIB"
      		+ "ILITY_DESC,C.PRDVREGIST_SCHEME_TYPE_FLAG,A.PRDIPROPOSAL_ID,"
      		+ "\r\nD.PRDVSPECIAL_NOTE,M.PRDVNOTIFICATION_ID,A.PLDVSCHEME_ID,"
      		+ "A.PLDVSUBSCHEME_ID\r\n,A.PLDVSECTOR_ID,A.PLDVDISP_PROP_TYPE_ID ,"
      		+ "A.PLDVDISP_PROP_SUBTYPE_ID,\r\n A.PLDVARCHITECTURAL_PLAN_ID,"
      		+ "B.PRDVELIGIBILTY_ID,A.PLDVFLOOR_ID , N.PRDRBOOKLET_COST,"
      		+ "N.VCH_BOOKLET_FILE,N.VCH_AFFIDAVIT_FILE \r\n "
      		+ "FROM PROPERTY.PRE_REGIST_PROPOSAL_DETAIL A \r\nLEFT OUTER JOIN"
      		+ " \r\nPROPERTY.PRE_PROPOSAL_NOTE D ON "
      		+ "A.PRDIPROPOSAL_ID=D.PRDIPROPOSAL_ID,\r\nPROPERTY.PRE_PROPOSAL_ELIGIBLE_ASSOC B"
      		+ " ,\r\nPROPERTY.PRE_REGIST_PROPOSAL C, \r\nPROPERTY.PLE_SCHEME_DETAILS E,\r\nPROPERTY.PLE_SUBSCHEME_DETAILS  F,\r\nPROPERTY.PLE_SECTOR_DETAILS G,\r\nPROPERTY.PLE_DISP_PROP_TYPE_MASTER H,\r\nPROPERTY.PLE_DISP_PROP_SUBTYPE_MASTER I,\r\nPROPERTY.PLE_ARCHITECTURAL_PLAN_MASTER J,\r\nPROPERTY.PLE_FLOOR_MASTER K ,\r\nPROPERTY.PRE_ELIGIBILITY_MASTER L,\r\nPROPERTY.PRE_NOTIFICATION_PROPOSAL M,\r\nPROPERTY.PRE_REGIST_NOTIFICATION N \r\nWHERE\r\nA.PRDIPROPOSAL_ID=B.PRDIPROPOSAL_ID AND A.PRDIPROPOSAL_ID=C.PRDIPROPOSAL_ID\r\nAND A.PLDVSCHEME_ID=E.PLDVSCHEME_ID\r\nAND A.PLDVSUBSCHEME_ID=F.PLDVSUBSCHEME_ID AND A.PLDVSECTOR_ID=G.PLDVSECTOR_ID\r\nAND A.PLDVDISP_PROP_TYPE_ID=H.PLDVDISP_PROP_TYPE_ID\r\nAND A.PLDVDISP_PROP_SUBTYPE_ID=I.PLDVDISP_PROP_SUBTYPE_ID\r\nAND A.PLDVARCHITECTURAL_PLAN_ID=J.PLDVARCHITECTURAL_PLAN_ID\r\nAND A.PLDVFLOOR_ID=K.PLDVFLOOR_ID\r\nAND B.PRDVELIGIBILTY_ID=L.PRDVELIGIBILTY_ID\r\nAND A.PRDIPROPOSAL_ID=M.PRDIPROPOSAL_ID \r\nAND M.PRDVNOTIFICATION_ID=N.PRDVNOTIFICATION_ID\r\nAND N.PRDDREGISTRATION_OPEN_DATE<=? \r\nAND N.PRDDREGISTRATION_CLOSE_DATE>=? \r\n";
      

      conn = ConnectionToDataBase.getConnection2();
      pstmt = conn.prepareStatement(query);
      pstmt.setDate(1, Utility.convertUtilDateToSQLDate(new Date()));
      pstmt.setDate(2, Utility.convertUtilDateToSQLDate(new Date()));
      rs = pstmt.executeQuery();
      
      while (rs.next())
      {
        ApplicationEntryDataTable dt = new ApplicationEntryDataTable();
        
        dt.setSchemeName(rs.getString(1));
        dt.setSubSchemeName(rs.getString(2));
        dt.setSectorName(rs.getString(3));
        dt.setPropertyTypeName(rs.getString(4));
        dt.setPropertySubTypeName(rs.getString(5));
        dt.setArchPlanName(rs.getString(6));
        dt.setFloorName(rs.getString(7));
        dt.setQuantity(rs.getString(8));
        dt.setTotalPrice(myFormatter.format(new Double(rs.getString(9))));
        dt.setEligibilityDesc(rs.getString(10));
        
        dt.setBookletCost(myFormatter.format(new Double(rs.getString("PRDRBOOKLET_COST"))));
        dt.setBookletName(rs.getString("VCH_BOOKLET_FILE"));
        dt.setAffidavitName(rs.getString("VCH_AFFIDAVIT_FILE"));
        
        dt.setRegistrationSchemeType(rs.getString(11));
        if ((rs.getString(11) != null) && (rs.getString(11).equalsIgnoreCase("L")))
        {
          dt.setRegistrationSchemeTypeDisplay("Lottery");
        }
        else if ((rs.getString(11) != null) && (rs.getString(11).equalsIgnoreCase("B")))
        {
          dt.setRegistrationSchemeTypeDisplay("Bulk Sale");
        }
        else if ((rs.getString(11) != null) && (rs.getString(11).equalsIgnoreCase("C")))
        {
          dt.setRegistrationSchemeTypeDisplay("Cash Sale");
        }
        else if ((rs.getString(11) != null) && (rs.getString(11).equalsIgnoreCase("R")))
        {
          dt.setRegistrationSchemeTypeDisplay("Rent");
        }
        else if ((rs.getString(11) != null) && (rs.getString(11).equalsIgnoreCase("T")))
        {
          dt.setRegistrationSchemeTypeDisplay("Tender");
        }
        else if ((rs.getString(11) != null) && (rs.getString(11).equalsIgnoreCase("A")))
        {
          dt.setRegistrationSchemeTypeDisplay("Auction");
        }
        else if ((rs.getString(11) != null) && (rs.getString(11).equalsIgnoreCase("N")))
        {
          dt.setRegistrationSchemeTypeDisplay("Nazul");

        }
        else if ((rs.getString(11) != null) && (rs.getString(11).equalsIgnoreCase("D")))
        {
          dt.setRegistrationSchemeTypeDisplay("Direct Allotment");
        }
        else if ((rs.getString(11) != null) && (rs.getString(11).equalsIgnoreCase("F")))
        {
          dt.setRegistrationSchemeTypeDisplay("First Come First Serve");
        }
        

        dt.setProposalId(rs.getString(12));
        
        dt.setSpecialNoteDetail(rs.getString(13));
        dt.setNotificationId(rs.getString(14));
        
        dt.setSchemeId(rs.getString(15));
        dt.setSubSchemeId(rs.getString(16));
        
        dt.setSectorId(rs.getString(17));
        dt.setPropertyTypeId(rs.getString(18));
        dt.setPropertySubTypeId(rs.getString(19));
        dt.setArchiPlanId(rs.getString(20));
        dt.setEligibilityId(rs.getString(21));
        dt.setFloorId(rs.getString(22));
        dt.setPaymentId(2);
        list.add(dt);
      }
      

    }
    catch (Exception e)
    {

      e.printStackTrace();
      


      try
      {
        if (pstmt != null) pstmt.close();
        if (conn != null) { conn.close();
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
        if (conn != null) { conn.close();
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    return list;
  }
}
    
