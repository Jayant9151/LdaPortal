package com.mentor.uplc.utility;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class Sender
{
  String message;
  String type;
  String sendername;
  String mobile;
  String dlr;
  String server;
  int port;
  String destination;
  String username;
  String password;
  String source;
  
  public Sender(String server, String username, String password, String mobile, String sendername, String message)
  {
    this.username = username;
    this.password = password;
    this.message = message;
    this.mobile = mobile;
    this.sendername = sendername;
  }
  

  public String submitMessage()
  {
    String dataFromUrl = "";String dataBuffer = "";
    




    try
    {
      URL sendUrl = new URL("http://priority.muzztech.in/sms_api/sendsms.php");
      HttpURLConnection httpConnection = (HttpURLConnection)sendUrl.openConnection();
      
      httpConnection.setRequestMethod("POST");
      
      httpConnection.setDoInput(true);
      
      httpConnection.setDoOutput(true);
      
      httpConnection.setUseCaches(false);
      
      DataOutputStream dataStreamToServer = new DataOutputStream(httpConnection.getOutputStream());
      






      dataStreamToServer.writeBytes("username=" + URLEncoder.encode(username, "UTF-8") + "&password=" + 
      
        URLEncoder.encode(password, "UTF-8") + "&mobile=" + URLEncoder.encode(mobile, "UTF-8") + 
        
        "&sendername=" + URLEncoder.encode(sendername, "UTF-8") + "&message=" + URLEncoder.encode(
        
        message, "UTF-8"));
      dataStreamToServer.flush();
      dataStreamToServer.close();
      
      BufferedReader dataStreamFromUrl = new BufferedReader(
        new InputStreamReader(httpConnection.getInputStream()));
      


      while ((dataBuffer = dataStreamFromUrl.readLine()) != null)
      {


        dataFromUrl = dataFromUrl + dataBuffer;
      }
      




      dataStreamFromUrl.close();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    return dataFromUrl;
  }
  


  private static StringBuffer convertToUnicode(String regText)
  {
    char[] chars = regText.toCharArray();
    StringBuffer hexString = new StringBuffer();
    for (int i = 0; i < chars.length; i++) {
      String iniHexString = Integer.toHexString(chars[i]);
      if (iniHexString.length() == 1) {
        iniHexString = "000" + iniHexString;
      } else if (iniHexString.length() == 2) {
        iniHexString = "00" + iniHexString;
      } else if (iniHexString.length() == 3) {
        iniHexString = "0" + iniHexString;hexString.append(iniHexString);
      } }
    System.out.println(hexString);
    return hexString;
  }
}