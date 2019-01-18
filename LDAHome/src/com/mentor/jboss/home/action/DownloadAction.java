package com.mentor.jboss.home.action;

import com.mentor.jboss.home.impl.DownloadImpl;
import com.mentor.uplc.utility.Validate;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

public class DownloadAction
{
  private int srNO1;
  private String name;
  private String srcMethod;
  private String filename;
  private int srno;
  private String fileNm;
  
  public DownloadAction() {}
  
  public String getFilename()
  {
    return filename;
  }
  
  public void setFilename(String filename) { this.filename = filename; }
  
  public int getSrno() {
    return srno;
  }
  
  public void setSrno(int srno) { this.srno = srno; }
  




  private Date uploadDate = new Date();
  
  private String recordFile1;
  
  private boolean validateInput = true;
  

  public boolean isValidateInput()
  {
    validateInput = true;
    if (!Validate.validateStrReq("filedesc", getFileNm())) { validateInput = false;
    } else if (!Validate.validateDate("uploaddate", getUploadDate())) { validateInput = false;
    } else if (!Validate.validateDate("validdate", getValidDate())) validateInput = false;
    return validateInput;
  }
  
  public void setValidateInput(boolean validateInput) { this.validateInput = validateInput; }
  

  private Date validDate = new DownloadImpl().getdisplayTill();
  private boolean vailidate;
  private Date validDate1;
  
  public Date getValidDate1()
  {
    return validDate1;
  }
  
  public void setValidDate1(Date validDate1) { this.validDate1 = validDate1; }
  
  public boolean isVailidate() {
    vailidate = true;
    if (!Validate.validateStrReq("fileNm", getFileNm()))
      vailidate = false;
    System.out.println("vailidate=" + vailidate);
    return vailidate;
  }
  
  public void setVailidate(boolean vailidate) { this.vailidate = vailidate; }
  
  public String getFileNm() {
    return fileNm;
  }
  
  public void setFileNm(String fileNm) { this.fileNm = fileNm; }
  
  public Date getUploadDate() {
    return uploadDate;
  }
  
  public void setUploadDate(Date uploadDate) { this.uploadDate = uploadDate; }
  

  private ArrayList<DownloadAction> displaydatalist = new ArrayList();
  

  public String reset()
  {
    uploadDate = new Date();
    validDate = new DownloadImpl().getdisplayTill();
    fileNm = "";
    return "";
  }
  
  public ArrayList<DownloadAction> getDisplaydatalist()
  {
    try {
      DownloadImpl impl = new DownloadImpl();
      displaydatalist = impl.pdfName();
    }
    catch (Exception localException) {}
    


    return displaydatalist;
  }
  
  public void setDisplaydatalist(ArrayList<DownloadAction> displaydatalist) { this.displaydatalist = displaydatalist; }
  
  public String getSrcMethod() {
    return srcMethod;
  }
  
  public void setSrcMethod(String srcMethod) { this.srcMethod = srcMethod; }
  
  public int getSrNO1() {
    return srNO1;
  }
  
  public void setSrNO1(int srNO1) { this.srNO1 = srNO1; }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) { this.name = name; }
  

  public void listener2(ActionEvent e)
  {
    String mypath = com.mentor.uplc.utility.Constants.JBOSS_SERVER_PATH + "doc";
    

    org.richfaces.component.UIDataTable uiTable = (org.richfaces.component.UIDataTable)e.getComponent().getParent().getParent().getParent();
    int rowId = uiTable.getRowIndex();
    DownloadAction dt = (DownloadAction)getDisplaydatalist().get(rowId);
    DownloadImpl impl = new DownloadImpl();
    
    System.out.println("000000000000000000000000----" + dt);
    String pdfName = impl.pdfImpl(dt);
  }
  




  private static BufferedInputStream in = null;
  
  public static BufferedInputStream getIn() {
    return in;
  }
  
  public static void setIn(BufferedInputStream in) { in = in; }
  
  private BufferedInputStream bufferedinForUploadFile = null;
  
  public BufferedInputStream getBufferedinForUploadFile() {
    return bufferedinForUploadFile;
  }
  
  public void setBufferedinForUploadFile(BufferedInputStream bufferedinForUploadFile) {
    this.bufferedinForUploadFile = bufferedinForUploadFile;
  }
  


  private String filePath = "";
  private String FullfileExt = "";
  private String FullfileName = "";
  

  public String getFilePath()
  {
    return filePath;
  }
  
  public void setFilePath(String filePath) { this.filePath = filePath; }
  
  public String getFullfileExt() {
    return FullfileExt;
  }
  
  public void setFullfileExt(String fullfileExt) { FullfileExt = fullfileExt; }
  
  public String getFullfileName() {
    return FullfileName;
  }
  
  public void setFullfileName(String fullfileName) { FullfileName = fullfileName; }
  
  public void zipUpload(UploadEvent event)
  {
    UploadItem item = event.getUploadItem();
    

    FullfileName = item.getFileName();
    FullfileExt = FullfileName.substring(FullfileName.lastIndexOf("."));
    



    System.out.println("File Name =" + FullfileName);
    
    try
    {
      if ((FullfileName != null) && (FullfileName.length() > 0))
      {
        setFilePath(item.getFile().getPath());
        String mypath = com.mentor.uplc.utility.Constants.JBOSS_SERVER_PATH + "doc";
        String newFilePath = mypath + File.separator + "LDA" + File.separator + "welcome" + File.separator + "homepage" + File.separator + "DOWNLOADS";
        
        if (!new File(newFilePath).exists())
        {
          File file = new File(newFilePath);
          file.mkdirs();
        }
        java.io.InputStream infile = new java.io.FileInputStream(filePath);
        BufferedInputStream in = new BufferedInputStream(infile);
        setBufferedinForUploadFile(in);
      }
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
  }
  















  public String save()
  {
    try
    {
      if (isValidateInput())
      {
        if ((FullfileName != null) && (FullfileName.length() > 0))
        {
          DownloadImpl impl = new DownloadImpl();
          impl.saveUploadedFile(fileNm, uploadDate, fileNm, validDate, this, FullfileName);
          reset();
        }
        else {
          com.mentor.uplc.utility.ResourceUtil.addErrorMessage("FILE_REQ", "FILE_REQ");
        }
      }
    }
    catch (Exception localException) {}
    



    return "";
  }
  
  public String getRecordFile1() { return recordFile1; }
  
  public void setRecordFile1(String recordFile1) {
    this.recordFile1 = recordFile1;
  }
  
  public Date getValidDate() { return validDate; }
  
  public void setValidDate(Date validDate) {
    this.validDate = validDate;
  }
}