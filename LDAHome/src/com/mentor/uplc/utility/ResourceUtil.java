package com.mentor.uplc.utility;

import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.acl.Group;
import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.portlet.PortletContext;
import javax.portlet.PortletRequest;
import javax.security.auth.Subject;
import org.apache.catalina.connector.RequestFacade;
import org.jboss.portal.identity.IdentityException;
import org.jboss.portal.identity.MembershipModule;
import org.jboss.portal.identity.NoSuchUserException;
import org.jboss.portal.identity.Role;
import org.jboss.portal.identity.UserModule;
import org.jboss.portal.identity.auth.UserPrincipal;
import org.jboss.portal.portlet.impl.jsr168.api.ActionRequestImpl;
import org.jboss.portal.portlet.impl.jsr168.api.RenderRequestImpl;

public class ResourceUtil
{
  public ResourceUtil() {}
  
  public static String encryptMD5(String pass)
  {
    String passwd = "";
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      




      md.update(pass.getBytes());
      byte[] encodedPassword = md.digest();
      passwd = bytesToHex(encodedPassword);
    }
    catch (NoSuchAlgorithmException nsae) {
      System.out.println("NoSuchAlgorithmException : " + nsae);
    }
    catch (Exception e) {
      System.out.println("Exception : " + e);
    }
    
    return passwd;
  }
  

  public static String bytesToHex(byte[] b)
  {
    char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6', '7', 
      '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    StringBuffer buf = new StringBuffer();
    for (int j = 0; j < b.length; j++) {
      buf.append(hexDigit[(b[j] >> 4 & 0xF)]);
      buf.append(hexDigit[(b[j] & 0xF)]);
    }
    return buf.toString();
  }
  
  public static void addRowErrorMessage(int rowNumber, String messageKey, Object[] param) {
    FacesContext ctx = FacesContext.getCurrentInstance();
    
    Locale locale = ctx.getViewRoot().getLocale();
    
    ResourceBundle bundle = ResourceBundle.getBundle(ctx
      .getApplication().getMessageBundle(), locale);
    
    String resource = bundle.getString("rowNumber");
    
    String message = MessageFormat.format(getMessage(messageKey), param);
    
    message = message + " " + resource + "-" + rowNumber;
    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
      message, message);
    ctx.addMessage(messageKey, msg);
  }
  
  public static Object getBean(String expr) {
    FacesContext context = FacesContext.getCurrentInstance();
    Application app = context.getApplication();
    ValueBinding binding = app.createValueBinding("#{" + expr + "}");
    Object value = binding.getValue(context);
    return value;
  }
  
  public static boolean isUserInRole(String roleName)
  {
    boolean status = false;
    
    try
    {
      InitialContext ic = new InitialContext();
      Subject subject = (Subject)ic.lookup("java:comp/env/security/subject");
      


      Iterator principals = subject.getPrincipals(Group.class).iterator();
      if (principals.hasNext())
      {
        Group roles = (Group)principals.next();
        Enumeration roleEnum = roles.members();
        
        while (roleEnum.hasMoreElements()) {
          UserPrincipal role = (UserPrincipal)roleEnum.nextElement();
          String str = role.toString();
          if (str.equalsIgnoreCase(roleName))
          {

            status = true;
          }
        }
      }
    }
    catch (Exception ex)
    {
      ex.printStackTrace();
    }
    

    return status;
  }
  
  public static String getUserInRole(String userID)
  {
    Role role = null;
    UserModule usrMod = null;
    try {
      usrMod = (UserModule)new InitialContext().lookup("java:/portal/UserModule");
    }
    catch (NamingException e) {
      e.printStackTrace();
    }
    MembershipModule mbr = null;
    try {
      mbr = (MembershipModule)new InitialContext().lookup("java:/portal/MembershipModule");
    }
    catch (NamingException e) {
      e.printStackTrace();
    }
    org.jboss.portal.identity.User user = null;
    try {
      user = usrMod.findUserById(userID);
    }
    catch (IllegalArgumentException e) {
      e.printStackTrace();
    }
    catch (NoSuchUserException e) {
      e.printStackTrace();
    }
    catch (IdentityException e) {
      e.printStackTrace();
    }
    Set roles = null;
    Iterator itr = null;
    try {
      roles = mbr.getRoles(user);
    }
    catch (IllegalArgumentException e) {
      e.printStackTrace();
    }
    catch (IdentityException e) {
      e.printStackTrace();
    }
    itr = roles.iterator();
    while (itr.hasNext()) {
      role = (Role)itr.next();
      System.out.println("ROLE : " + role.getDisplayName());
    }
    System.out.println("ROLE : " + role.getDisplayName());
    return role.getDisplayName();
  }
  
  public static String getUserNameAllReq()
  {
    Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();
    RenderRequestImpl reqRRI = null;
    ActionRequestImpl reqARI = null;
    String userName = null;
    
    if ((request instanceof RenderRequestImpl))
    {
      reqRRI = (RenderRequestImpl)request;
      userName = reqRRI.getRemoteUser();

    }
    else if ((request instanceof ActionRequestImpl))
    {
      reqARI = (ActionRequestImpl)request;
      userName = reqARI.getRemoteUser();

    }
    else
    {
      userName = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
    }
    

    return userName;
  }
  








































  public static String getPath(String pathReport)
  {
    String path = "";
    try {
      FacesContext context = FacesContext.getCurrentInstance();
      
      PortletContext sc = (PortletContext)context.getExternalContext().getContext();
      ActionRequestImpl req = (ActionRequestImpl)FacesContext.getCurrentInstance().getExternalContext().getRequest();
      
      path = sc.getRealPath("");
    } catch (Exception e) {
      System.out.println(e);
    }
    
    return path;
  }
  



  public static void addRowErrorMessage1(int rowNumber, String id, String messageKey)
  {
    FacesContext ctx = FacesContext.getCurrentInstance();
    Locale locale = ctx.getViewRoot().getLocale();
    String message = getMessage(id, messageKey);
    ResourceBundle bundle = ResourceBundle.getBundle(ctx
      .getApplication().getMessageBundle(), locale);
    
    String resource = bundle.getString("rowNumber");
    
    message = message + " " + resource + "-" + rowNumber;
    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
      message, message);
    ctx.addMessage(id, msg);
  }
  





  public static final PortletContext getContext()
  {
    FacesContext ctx = FacesContext.getCurrentInstance();
    return (PortletContext)ctx.getExternalContext().getContext();
  }
  
  public static String getRealPath(String path)
  {
    return getContext().getRealPath(path);
  }
  
  public static String getMessage(String key)
  {
    FacesContext ctx = FacesContext.getCurrentInstance();
    Locale locale = ctx.getViewRoot().getLocale();
    ResourceBundle bundle = ResourceBundle.getBundle(ctx.getApplication().getMessageBundle(), locale);
    return bundle.getString(key);
  }
  
  public static String getMessage(String id, String messageKey) {
    try {
      FacesContext ctx = FacesContext.getCurrentInstance();
      Locale locale = ctx.getViewRoot().getLocale();
      ResourceBundle bundle = ResourceBundle.getBundle(ctx
        .getApplication().getMessageBundle(), locale);
      String resource = bundle.getString(messageKey);
      if (id != null) {
        String argument = bundle.getString(id);
        
        MessageFormat formatter = new MessageFormat(resource);
        
        return formatter.format(new String[] { argument });
      }
      return resource;
    } catch (MissingResourceException e) {
      e.printStackTrace();
    }
    
    return null;
  }
  
  public static String getMessageSec(int strLength, String id, String messageKey) {
    try { FacesContext ctx = FacesContext.getCurrentInstance();
      Locale locale = ctx.getViewRoot().getLocale();
      ResourceBundle bundle = ResourceBundle.getBundle(ctx
        .getApplication().getMessageBundle(), locale);
      String resource = bundle.getString(messageKey);
      if (id != null) {
        String argument = bundle.getString(id);
        argument = argument + " " + strLength;
        MessageFormat formatter = new MessageFormat(resource);
        
        return formatter.format(new String[] { argument });
      }
      return resource;
    } catch (MissingResourceException e) {
      e.printStackTrace();
    }
    
    return null;
  }
  
  public static String getMessageSecSecond(int strLength, String id, String messageKey) {
    try {
      FacesContext ctx = FacesContext.getCurrentInstance();
      Locale locale = ctx.getViewRoot().getLocale();
      ResourceBundle bundle = ResourceBundle.getBundle(ctx
        .getApplication().getMessageBundle(), locale);
      String resource = bundle.getString(messageKey);
      if (id != null) {
        String argument = bundle.getString(id);
        argument = String.valueOf(strLength);
        MessageFormat formatter = new MessageFormat(resource);
        
        return formatter.format(new String[] { argument });
      }
      return resource;
    } catch (MissingResourceException e) {
      e.printStackTrace();
    }
    
    return null;
  }
  
  public static void addErrorMessageRow(int rowNumber, String id, String key, Object[] params) {
    FacesContext ctx = FacesContext.getCurrentInstance();
    String message = MessageFormat.format(getMessage(key), params);
    Locale locale = ctx.getViewRoot().getLocale();
    
    ResourceBundle bundle = ResourceBundle.getBundle(ctx
      .getApplication().getMessageBundle(), locale);
    
    String resource = bundle.getString("rowNumber");
    
    message = message + " " + resource + "-" + rowNumber;
    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
    
    ctx.addMessage(id, msg);
  }
  
  public static void addErrorMessage(String id, String messageKey) { FacesContext ctx = FacesContext.getCurrentInstance();
    String message = getMessage(id, messageKey);
    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
      message, message);
    ctx.addMessage(id, msg);
  }
  
  public static void addErrorMessageSec(int strLength, String id, String messageKey) {
    FacesContext ctx = FacesContext.getCurrentInstance();
    String message = getMessageSec(strLength, id, messageKey);
    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
      message, message);
    ctx.addMessage(id, msg);
  }
  
  public static void addErrorMessageSecSecond(int strLength, String id, String messageKey) { FacesContext ctx = FacesContext.getCurrentInstance();
    String message = getMessageSecSecond(strLength, id, messageKey);
    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
      message, message);
    ctx.addMessage(id, msg);
  }
  
  public static void addMessage(String id, String messageKey) { FacesContext ctx = FacesContext.getCurrentInstance();
    String message = getMessage(null, messageKey);
    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, 
      message, message);
    
    ctx.addMessage(id, msg);
  }
  
  public static void addErrorMessage(String id, String key, Object[] params) {
    String message = MessageFormat.format(getMessage(key), params);
    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message);
    FacesContext ctx = FacesContext.getCurrentInstance();
    ctx.addMessage(id, msg);
  }
  






  public static String getUser()
  {
    ActionRequestImpl req = (ActionRequestImpl)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    String userName = req.getRemoteUser();
    return userName;
  }
  



  public static String getUserForPopUp()
  {
    RenderRequestImpl req = (RenderRequestImpl)FacesContext.getCurrentInstance().getExternalContext().getRequest();
    String userName = req.getRemoteUser();
    
    return userName;
  }
  





  public static javax.portlet.PortletSession getSession()
  {
    FacesContext ctx = FacesContext.getCurrentInstance();
    RequestFacade requestFacade = null;
    PortletRequest requestPR = null;
    Object request1 = FacesContext.getCurrentInstance().getExternalContext().getRequest();
    
    if ((request1 instanceof PortletRequest))
    {
      requestPR = (PortletRequest)request1;
      return requestPR.getPortletSession();
    }
    if ((request1 instanceof RequestFacade))
    {
      requestFacade = (RequestFacade)request1;
      return (javax.portlet.PortletSession)requestFacade.getSession();
    }
    


    return null;
  }
  



  public static String getMessagee(String[] id, String messageKey)
  {
    try
    {
      FacesContext ctx = FacesContext.getCurrentInstance();
      Locale locale = ctx.getViewRoot().getLocale();
      ResourceBundle bundle = ResourceBundle.getBundle(ctx.getApplication().getMessageBundle(), locale);
      String resource = bundle.getString(messageKey);
      if (id != null) {
        String[] argument = new String[id.length];
        for (int i = 0; i < id.length; i++) {
          argument[i] = bundle.getString(id[i]);
        }
        MessageFormat formatter = new MessageFormat(resource);
        return formatter.format(argument);
      }
      return resource;
    } catch (MissingResourceException e) {
      e.printStackTrace();
    }
    
    return null;
  }
  
  public static void addRowErrorMessage(int rowNumber, String id, String messageKey) { FacesContext ctx = FacesContext.getCurrentInstance();
    
    Locale locale = ctx.getViewRoot().getLocale();
    String message = getMessage(id, messageKey);
    ResourceBundle bundle = ResourceBundle.getBundle(ctx
      .getApplication().getMessageBundle(), locale);
    
    String resource = bundle.getString("rowNumber");
    
    message = message + " " + resource + "-" + rowNumber;
    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
      message, message);
    ctx.addMessage(id, msg);
  }
  
  public static void addRowErrorMessageSec(int rowNumber, int strLength, String id, String messageKey) {
    FacesContext ctx = FacesContext.getCurrentInstance();
    Locale locale = ctx.getViewRoot().getLocale();
    String message = getMessageSec(strLength, id, messageKey);
    ResourceBundle bundle = ResourceBundle.getBundle(ctx
      .getApplication().getMessageBundle(), locale);
    
    String resource = bundle.getString("rowNumber");
    
    message = message + " " + resource + "-" + rowNumber;
    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
      message, message);
    ctx.addMessage(id, msg);
  }
  
  public static void addErrorMessageForParameter(int id1, int id2, String str1, String str2) {
    FacesContext ctx = FacesContext.getCurrentInstance();
    Locale locale = ctx.getViewRoot().getLocale();
    String message = getMessage(str1, str2);
    ResourceBundle bundle = ResourceBundle.getBundle(ctx
      .getApplication().getMessageBundle(), locale);
    
    String resource = bundle.getString("rowNumber");
    
    message = message + " " + id1 + "-" + id2;
    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
      message, message);
    ctx.addMessage(message, msg);
  }
  
  public static void addErrorMessageTwoID(int id, int id1, String messageKey) {
    FacesContext ctx = FacesContext.getCurrentInstance();
    String message = getMessageTwoID(id, id1, messageKey);
    

    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
      message, message);
    ctx.addMessage(message, msg);
  }
  
  public static String getMessageTwoID(int id, int id1, String messageKey) {
    try {
      FacesContext ctx = FacesContext.getCurrentInstance();
      Locale locale = ctx.getViewRoot().getLocale();
      ResourceBundle bundle = ResourceBundle.getBundle(ctx
        .getApplication().getMessageBundle(), locale);
      String resource = bundle.getString(messageKey);
      




      String argument1 = String.valueOf(id);
      String argument2 = String.valueOf(id1);
      MessageFormat formatter = new MessageFormat(resource);
      
      return formatter.format(new String[] { argument1, argument2 });
    }
    catch (MissingResourceException e)
    {
      e.printStackTrace();
    }
    
    return null;
  }
}