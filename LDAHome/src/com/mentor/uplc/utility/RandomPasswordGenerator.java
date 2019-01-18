package com.mentor.uplc.utility;

import java.util.Random;

public class RandomPasswordGenerator {
  private static final String ALPHA_CAPS = "0123456789";
  private static final String ALPHA = "0123456789";
  private static final String NUM = "0123456789";
  private static final String SPL_CHARS = "!@#$%^&*_=+-/";
  
  public RandomPasswordGenerator() {}
  
  public static char[] generatePswd(int minLen, int maxLen, int noOfCAPSAlpha, int noOfDigits, int noOfSplChars) {
    if (minLen > maxLen)
      throw new IllegalArgumentException("Min. Length > Max. Length!");
    if (noOfCAPSAlpha + noOfDigits + noOfSplChars > minLen)
      throw new IllegalArgumentException(
        "Min. Length should be atleast sum of (CAPS, DIGITS, SPL CHARS) Length!");
    Random rnd = new Random();
    int len = rnd.nextInt(maxLen - minLen + 1) + minLen;
    char[] pswd = new char[len];
    int index = 0;
    for (int i = 0; i < noOfCAPSAlpha; i++) {
      index = getNextIndex(rnd, len, pswd);
      pswd[index] = "0123456789".charAt(rnd.nextInt("0123456789".length()));
    }
    for (int i = 0; i < noOfDigits; i++) {
      index = getNextIndex(rnd, len, pswd);
      pswd[index] = "0123456789".charAt(rnd.nextInt("0123456789".length()));
    }
    for (int i = 0; i < noOfSplChars; i++) {
      index = getNextIndex(rnd, len, pswd);
      pswd[index] = "!@#$%^&*_=+-/".charAt(rnd.nextInt("!@#$%^&*_=+-/".length()));
    }
    for (int i = 0; i < len; i++) {
      if (pswd[i] == 0) {
        pswd[i] = "0123456789".charAt(rnd.nextInt("0123456789".length()));
      }
    }
    return pswd;
  }
  
  private static int getNextIndex(Random rnd, int len, char[] pswd) {
    int index = rnd.nextInt(len);
    while (pswd[(index = rnd.nextInt(len))] != 0) {}
    return index;
  }
}