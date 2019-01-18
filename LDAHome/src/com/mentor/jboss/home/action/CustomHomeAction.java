package com.mentor.jboss.home.action;

import com.mentor.jboss.home.common.CommonImpl;
import com.mentor.jboss.home.datatable.CustomHomeDataTable;
import com.mentor.jboss.home.impl.CustomHomeImpl;
import com.mentor.uplc.utility.Constants;
import com.mentor.uplc.utility.ResourceUtil;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import javax.portlet.PortletSession;

public class CustomHomeAction
{
  private ArrayList<CustomHomeDataTable> menuLinkList = new ArrayList();
  
  private boolean linkListRefershFlag = true;
  
  private boolean renderHomePortlet = true;
  
  private boolean columnRender1 = false;
  private boolean columnRender2 = false;
  private boolean columnRender3 = false;
  private boolean columnRender4 = false;
  private boolean columnRender5 = false;
  private boolean columnRender6 = false;
  private boolean columnRender7 = false;
  private boolean columnRender8 = false;
  private boolean columnRender9 = false;
  private boolean columnRender10 = false;
  private boolean columnRender11 = false;
  private boolean renderHomePage = true;
  private boolean imgRender = true;
  private boolean pdfRender = false;
  private String srcMethod;
  private boolean backRendered = false;
  private boolean rendere1 = true;
  private boolean rendere9 = true;
  private boolean renderActsRules = false;
  private String style = "border-right: solid 1px #c0c0c0; padding-bottom: 10px;";
  private boolean pdfRenderActsRules = false;
  private String hidn = "";
  private String style4 = "min-width: 160px; min-height: 258px; max-height: 258px; color: black;";
  

  private boolean eaucflg = false;
  private boolean regflg = false;
  
  private int maxpdf = 0;
  
  public CustomHomeAction() {}
  
  public boolean isEaucflg()
  {
    new CustomHomeImpl();eaucflg = CustomHomeImpl.flgeauc();
    return eaucflg;
  }
  
  public void setEaucflg(boolean eaucflg) {
    this.eaucflg = eaucflg;
  }
  
  public boolean isRegflg() {
    new CustomHomeImpl();regflg = CustomHomeImpl.flgreg();
    return regflg;
  }
  
  public void setRegflg(boolean regflg) {
    this.regflg = regflg;
  }
  
  public int getMaxpdf() {
    System.out.println("lllllllllll");
    CustomHomeImpl impl = new CustomHomeImpl();
    maxpdf = CustomHomeImpl.uploadSeq();
    return maxpdf;
  }
  
  public void setMaxpdf(int maxpdf) {
    this.maxpdf = maxpdf;
  }
  


  private boolean pdfRender1 = false;
  
  public String getHidn() { PortletSession portletSession = ResourceUtil.getSession();
    
    return hidn;
  }
  
  public void setHidn(String hidn) {
    this.hidn = hidn;
  }
  
  public boolean isColumnRender8() {
    return columnRender8;
  }
  
  public void setColumnRender8(boolean columnRender8) {
    this.columnRender8 = columnRender8;
  }
  
  public boolean isColumnRender9() {
    return columnRender9;
  }
  
  public void setColumnRender9(boolean columnRender9) {
    this.columnRender9 = columnRender9;
  }
  
  public boolean isColumnRender10() {
    return columnRender10;
  }
  
  public void setColumnRender10(boolean columnRender10) {
    this.columnRender10 = columnRender10;
  }
  
  public boolean isColumnRender11() {
    return columnRender11;
  }
  
  public void setColumnRender11(boolean columnRender11) {
    this.columnRender11 = columnRender11;
  }
  
  public boolean isPdfRenderActsRules() {
    return pdfRenderActsRules;
  }
  
  public void setPdfRenderActsRules(boolean pdfRenderActsRules) {
    this.pdfRenderActsRules = pdfRenderActsRules;
  }
  
  public String getStyle4() {
    return style4;
  }
  
  public void setStyle4(String style4) {
    this.style4 = style4;
  }
  
  public boolean isRenderActsRules() {
    return renderActsRules;
  }
  
  public void setRenderActsRules(boolean renderActsRules) {
    this.renderActsRules = renderActsRules;
  }
  

  private boolean backRendered1 = false;
  private String width;
  
  public boolean isBackRendered1() { return backRendered1; }
  
  public void setBackRendered1(boolean backRendered1)
  {
    this.backRendered1 = backRendered1;
  }
  
  public boolean isPdfRender1() {
    return pdfRender1;
  }
  
  public void setPdfRender1(boolean pdfRender1) {
    this.pdfRender1 = pdfRender1;
  }
  










  public String getWidth()
  {
    return width;
  }
  
  public void setWidth(String width) {
    this.width = width;
  }
  
  public String getStyle() {
    return style;
  }
  
  public void setStyle(String style) {
    this.style = style;
  }
  
  public boolean isRendere1() {
    return rendere1;
  }
  
  public void setRendere1(boolean rendere1) {
    this.rendere1 = rendere1;
  }
  
  public boolean isBackRendered() {
    return backRendered;
  }
  
  public void setBackRendered(boolean backRendered) {
    this.backRendered = backRendered;
  }
  
  public String getSrcMethod() {
    return srcMethod;
  }
  
  public void setSrcMethod(String srcMethod) {
    this.srcMethod = srcMethod;
  }
  
  public boolean isPdfRender() {
    return pdfRender;
  }
  
  public void setPdfRender(boolean pdfRender) {
    this.pdfRender = pdfRender;
  }
  
  public boolean isImgRender() {
    return imgRender;
  }
  
  public void setImgRender(boolean imgRender) {
    this.imgRender = imgRender;
  }
  
  public void listener2(ActionEvent e)
  {
    String mypath = Constants.JBOSS_SERVER_PATH + "doc";
    
    String s = e.getComponent().getId();
    



    CustomHomeImpl impl = new CustomHomeImpl();
    imgRender = false;
    backRendered = true;
    
    pdfRender = true;
    rendere1 = false;
    rendere9 = true;
    style = "";
    
    renderActsRules = false;
    pdfRender1 = false;
    backRendered1 = false;
    pdfRenderActsRules = false;
    
    String pdfName = impl.pdfImpl(s);
    
    srcMethod = (File.separator + "doc" + File.separator + "LDA" + File.separator + "LDA_HOME_PDF" + File.separator + pdfName + ".pdf");
  }
  


  public void listenerFrActsRules(ActionEvent e)
  {
    String mypath = Constants.JBOSS_SERVER_PATH + "doc";
    
    String s = e.getComponent().getId();
    
    CustomHomeImpl impl = new CustomHomeImpl();
    imgRender = false;
    backRendered = true;
    
    pdfRender = false;
    rendere1 = false;
    rendere9 = true;
    style = "";
    pdfRenderActsRules = true;
    renderActsRules = true;
    pdfRender1 = false;
    backRendered1 = false;
    System.out.println("hello");
    
    String pdfName = impl.pdfImpl(s);
    
    srcMethod = (File.separator + "doc" + File.separator + "LDA" + File.separator + "LDA_HOME_PDF" + File.separator + pdfName + ".pdf");
  }
  





  public void listener8(ActionEvent e)
  {
    String mypath = Constants.JBOSS_SERVER_PATH + "doc";
    srcMethod = "";
    String s = e.getComponent().getId();
    if (s.equalsIgnoreCase("abc"))
    {
      int flag = 0;
      imgRender = false;
      backRendered = true;
      System.out.print(backRendered);
      pdfRender = false;
      pdfRenderActsRules = false;
      rendere1 = false;
      rendere9 = false;
      pdfRender1 = true;
      backRendered1 = true;
      
      renderActsRules = false;
      style = "";
      
      String path = File.separator + "doc" + File.separator + "LDA" + File.separator + "LDA_HOME_PDF" + File.separator + "MapApproval.pdf";
      
      String folderPath = File.separator + "doc" + File.separator + "LDA" + File.separator + "LDA_HOME_PDF";
      


      File folder = new File(folderPath);
      
      if (folder.isDirectory()) {
        File[] listOfFiles = folder.listFiles();
        
        if (listOfFiles.length > 0)
        {
          for (File file : listOfFiles)
          {

            if (file.toString().equalsIgnoreCase(path))
            {
              flag++;
            }
          }
        }
      }
      





      if (flag == 1) {
        srcMethod = path;
      } else {
        srcMethod = (File.separator + "doc" + File.separator + "LDA" + File.separator + "LDA_HOME_PDF" + File.separator + "notpdf.pdf");
      }
    }
  }
  

  public void listener3(ActionEvent e)
  {
    String mypath = Constants.JBOSS_SERVER_PATH + "doc";
    
    String s = e.getComponent().getId();
    CustomHomeImpl impl = new CustomHomeImpl();
    imgRender = false;
    backRendered = true;
    
    pdfRender = false;
    rendere1 = false;
    rendere9 = false;
    pdfRender1 = true;
    backRendered1 = true;
    renderActsRules = false;
    String pdfName = impl.pdfImpl(s);
    pdfRenderActsRules = false;
    srcMethod = (File.separator + "doc" + File.separator + "LDA" + File.separator + "LDA_HOME_PDF" + File.separator + pdfName + ".pdf");
  }
  





  public boolean isRendere9()
  {
    return rendere9;
  }
  
  public void setRendere9(boolean rendere9) {
    this.rendere9 = rendere9;
  }
  
  public String backMethod()
  {
    imgRender = true;
    pdfRender = false;
    backRendered = false;
    rendere1 = true;
    rendere9 = true;
    style = "border-right: solid 1px #c0c0c0; padding-bottom: 10px;";
    width = "15%";
    pdfRenderActsRules = false;
    pdfRender1 = false;
    backRendered1 = false;
    renderActsRules = false;
    



    return "";
  }
  


  public String backMethod1()
  {
    imgRender = true;
    pdfRender = false;
    backRendered = false;
    rendere1 = true;
    rendere9 = true;
    style = "border-right: solid 1px #c0c0c0; padding-bottom: 10px;";
    width = "15%";
    
    pdfRender1 = false;
    backRendered1 = false;
    
    renderActsRules = false;
    


    return "";
  }
  


  private String baseName = "com.mentor.uplc.message.nl.homepagemsg";
  





  private String newsTextToDisplay = "";
  private String image1 = "http:" + CommonImpl.serverIpAddressWithPort() + "//LDA//welcome//scroller//1.jpg";
  private String image2 = "http:" + CommonImpl.serverIpAddressWithPort() + "//LDA//welcome//scroller//2.jpg";
  private String image3 = "http:" + CommonImpl.serverIpAddressWithPort() + "//LDA//welcome//scroller//3.jpg";
  private String image4 = "http:" + CommonImpl.serverIpAddressWithPort() + "//LDA//welcome//scroller//4.jpg";
  private String image5 = "http:" + CommonImpl.serverIpAddressWithPort() + "//LDA//welcome//scroller//5.jpg";
  private String image6 = "http:" + CommonImpl.serverIpAddressWithPort() + "//LDA//welcome//scroller//6.jpg";
  private String imagepdf = "http:" + CommonImpl.serverIpAddressWithPort() + "//LDA//welcome//contactus.pdf";
  




  public String getImagepdf()
  {
    return imagepdf;
  }
  
  public void setImagepdf(String imagepdf) {
    this.imagepdf = imagepdf;
  }
  
  public String getNewsTextToDisplay() {
    CustomHomeImpl impl = new CustomHomeImpl();
    try {
      newsTextToDisplay = impl.newsText(null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return newsTextToDisplay;
  }
  
  public void setNewsTextToDisplay(String newsTextToDisplay) {
    this.newsTextToDisplay = newsTextToDisplay;
  }
  
  public ArrayList<CustomHomeDataTable> getMenuLinkList() {
    CustomHomeImpl impl = new CustomHomeImpl();
    newsTextToDisplay = "";
    try {
      if (ResourceUtil.getUserNameAllReq() == null) {
        renderHomePage = true;
        menuLinkList = new ArrayList();
      } else {
        renderHomePage = false;
        menuLinkList = impl.displayMenuList(this);
      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return menuLinkList;
  }
  
  public boolean isColumnRender5() {
    return columnRender5;
  }
  
  public void setColumnRender5(boolean columnRender5) {
    this.columnRender5 = columnRender5;
  }
  
  public String getBaseName() {
    return baseName;
  }
  
  public void setBaseName(String baseName) {
    this.baseName = baseName;
  }
  
  public boolean isRenderHomePage()
  {
    if (ResourceUtil.getUserNameAllReq() == null) {
      renderHomePage = true;
    } else {
      renderHomePage = false;
    }
    
    return renderHomePage;
  }
  
  public void setRenderHomePage(boolean renderHomePage) {
    this.renderHomePage = renderHomePage;
  }
  
  public void setMenuLinkList(ArrayList<CustomHomeDataTable> menuLinkList) {
    this.menuLinkList = menuLinkList;
  }
  
  public boolean isLinkListRefershFlag() {
    return linkListRefershFlag;
  }
  
  public void setLinkListRefershFlag(boolean linkListRefershFlag) {
    this.linkListRefershFlag = linkListRefershFlag;
  }
  
  public boolean isColumnRender1() { return columnRender1; }
  
  public void setColumnRender1(boolean columnRender1) {
    this.columnRender1 = columnRender1;
  }
  
  public boolean isColumnRender2() { return columnRender2; }
  
  public void setColumnRender2(boolean columnRender2) {
    this.columnRender2 = columnRender2;
  }
  
  public boolean isColumnRender3() { return columnRender3; }
  
  public void setColumnRender3(boolean columnRender3) {
    this.columnRender3 = columnRender3;
  }
  
  public boolean isColumnRender4() { return columnRender4; }
  
  public void setColumnRender4(boolean columnRender4) {
    this.columnRender4 = columnRender4;
  }
  
  public String menuLink() {
    JbossNav nav = new JbossNav();
    try {
      nav.navLinkAction();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "";
  }
  
  public String getImage1() {
    return image1;
  }
  
  public void setImage1(String image1) {
    this.image1 = image1;
  }
  
  public String getImage2() {
    return image2;
  }
  
  public void setImage2(String image2) {
    this.image2 = image2;
  }
  
  public String getImage3() {
    return image3;
  }
  
  public void setImage3(String image3) {
    this.image3 = image3;
  }
  
  public String getImage4() {
    return image4;
  }
  
  public void setImage4(String image4) {
    this.image4 = image4;
  }
  
  public String getImage5() {
    return image5;
  }
  
  public void setImage5(String image5) {
    this.image5 = image5;
  }
  
  public String getImage6() {
    return image6;
  }
  
  public void setImage6(String image6) {
    this.image6 = image6;
  }
  









  public boolean isRenderHomePortlet()
  {
    return renderHomePortlet;
  }
  
  public void setRenderHomePortlet(boolean renderHomePortlet) {
    this.renderHomePortlet = renderHomePortlet;
  }
  
  public boolean isColumnRender6() {
    return columnRender6;
  }
  
  public void setColumnRender6(boolean columnRender6) {
    this.columnRender6 = columnRender6;
  }
  
  public boolean isColumnRender7() {
    return columnRender7;
  }
  
  public void setColumnRender7(boolean columnRender7) {
    this.columnRender7 = columnRender7;
  }
}