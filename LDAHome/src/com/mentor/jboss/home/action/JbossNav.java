package com.mentor.jboss.home.action;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.jboss.portal.api.node.PortalNode;
import org.jboss.portal.portlet.impl.jsr168.api.ActionRequestImpl;

public class JbossNav
{
  public JbossNav() {}
  
  public void navLinkAction()
  {
    ActionRequestImpl request = (ActionRequestImpl)javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequest();
    




    PortalNode root = (PortalNode)request.getAttribute("org.jboss.portal.api.PORTAL_NODE");
    PortalNode portal = root;
    PortalNode mainPage = portal;
    System.out.println("STEPS 1");
    while (portal.getType() != 1)
    {
      mainPage = portal;
      portal = portal.getParent();
      System.out.println("STEPS 1 WHILE LOOP");
    }
    
    org.jboss.portal.api.PortalRuntimeContext context = (org.jboss.portal.api.PortalRuntimeContext)request.getAttribute("org.jboss.portal.api.PORTAL_RUNTIME_CONTEXT");
    System.out.println("STEPS 2 ");
    
    java.util.Locale locale = request.getLocale();
    if (locale == null)
    {
      locale = java.util.Locale.getDefault();
    }
    ArrayList tmp = new ArrayList(10);
    Iterator childrenIt = portal.getChildren().iterator();
    Iterator j;
    
     
    for (; childrenIt.hasNext();j.hasNext())
    {
      PortalNode child = (PortalNode)childrenIt.next();
      System.out.println("STEPS 3");
      
      tmp.clear();
      for (Iterator i = child.getChildren().iterator(); i.hasNext();)
      {
        PortalNode childChild = (PortalNode)i.next();
        if (childChild.getType() == 2)
        {
          tmp.add(childChild);
        }
      }
      
      System.out.println(" MAIN PAGE ==> : " + mainPage);
      System.out.println(" CHILD PAGE ==> : " + child);
      
      if (child == mainPage)
      {
        System.out.println(" id=\"current\"" + child);
      }
      

      System.out.println(" URL GENERATED ==> " + child.createURL(context));
      System.out.println(" URL NAME GENERATED ==> " + child.getDisplayName(locale));
      
      if (tmp.size() == 0)
      {
        System.out.println(" NO TAB FOUND");
      }
      
      ArrayList tmpChildThird = new ArrayList(10);
      tmpChildThird.clear();
      j = tmp.iterator(); //continue;
      
      PortalNode childChild = (PortalNode)j.next();
      
      for (Iterator k = childChild.getChildren().iterator(); k.hasNext();)
      {
        PortalNode childThird = (PortalNode)k.next();
        if (childThird.getType() == 2)
        {
          tmpChildThird.add(childThird);
        }
      }
      childChild.createURL(context);
      childChild.getDisplayName(locale);
      System.out.println(" CHILD URL GENERATED ==> " + childChild.createURL(context));
      System.out.println(" CHILD NAME GENERATED ==> " + childChild.getDisplayName(locale));
      for (Iterator z = tmpChildThird.iterator(); z.hasNext();)
      {
        PortalNode childChildChild = (PortalNode)z.next();
        childChildChild.createURL(context);
        childChildChild.getDisplayName(locale);
      }
      
      tmpChildThird.clear();
    }
  }
}