/*
 * Copyright (C) 2018 JavaSmyths javasmyths@javasmyths.com
 */
package com.javasmyths.filters;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.math3.primes.Primes;

/**
 *
 * @author Richard
 */
public class CryptPrime {

  public static void main(String[] args) {
    int multiplicand = 0x75a600;
    long multiplier = 0x75a6;
    long result = (long) (multiplicand * multiplier);
    long result2 = (long) (((long) result) / ((long) multiplier));
    System.out.println("multiplicand = " + (long) multiplicand);
    System.out.println("multiplier = " + (long) multiplier);
    System.out.println("result = " + (long) result);
    System.out.println("result2 = " + result2);

    String str
            = "Now is the time for all good men to come to the aid of his country";
    String password = "Brody@314159";

    long nextPrimePassword = integerFromString(password, 0);
    System.out.println("nextPrimeStep " + nextPrimePassword + " = 0x" + Long.toHexString(nextPrimePassword));
    nextPrimePassword = Primes.nextPrime((int) nextPrimePassword);
    System.out.println("nextPrimeStep " + nextPrimePassword);

    ByteBuffer byteBuffer = ByteBuffer.allocate((str.length() * 2) + (str.length() % 8) * 2);
    System.out.println("****************************************");
    ArrayList<Long> encryptedLongs = new ArrayList();
    for (int i = 0; i < str.length(); i += 4) {
      long encryptedLong = integerFromString(str, i) * nextPrimePassword;
      encryptedLongs.add(encryptedLong);
      byteBuffer.putLong(i * 2, encryptedLong);
      System.out.println("64 bytes encrypted = " + encryptedLong + " as hex 0x" + Long.toHexString(encryptedLong));
    }

    System.out.println("Full hex string encrypted: " + Hex.encodeHexString(byteBuffer));
    System.out.println("New method               : " + Hex.encodeHexString(encryptBytes(str.getBytes(), nextPrimePassword)));
    
    System.out.println("****************************************");
    for (Long encryptedLong : encryptedLongs) {
      long decryptedLong = encryptedLong / nextPrimePassword;
      byte[] bytes = ByteBuffer.allocate(8).putLong(decryptedLong).array();
      System.out.println("64 bytes decrypted = " + decryptedLong + " as hex 0x" + Long.toHexString(decryptedLong) + " Decrypted String = " + new String(bytes));
    }
  }

  private static long integerFromString(String str, int offset) {
    long returnValue = 0;
    byte bytes[] = str.getBytes();
    int top = 4;
    if ((offset + top) > bytes.length) {
      top = bytes.length - offset;
    }

    for (int i = 0; i < top; i++) {
      returnValue <<= 8;
      returnValue |= bytes[offset + i];
    }
    return returnValue;
  }

  private static byte[] encryptBytes(byte bytes [], long prime) {
    ByteBuffer byteBuffer = ByteBuffer.allocate((bytes.length * 2) + (bytes.length % 8) * 2);
    for (int i = 0; i < bytes.length; i += 4) {
      long encryptedLong = integerFromBytes(bytes, i) * prime;
      byteBuffer.putLong(i * 2, encryptedLong);
    }
    
    return byteBuffer.array();
  }
  private static byte[] decryptBytes(byte bytes [], long prime) {
    ByteBuffer byteBufferIn = ByteBuffer.wrap(bytes);
    ByteBuffer byteBufferOut = ByteBuffer.allocate((bytes.length * 2) + (bytes.length % 8) * 2);
    
    for (int i = 0; i < bytes.length; i += 8) {
      long dividend = byteBufferIn.getLong(i);
      long decryptedLong = dividend / prime;
      byteBufferOut.putLong(i * 2, decryptedLong);
    }
    
    return byteBufferOut.array();
  }


  private static long integerFromBytes(byte bytes [], int offset) {
    long returnValue = 0;
    int top = 4;
    if ((offset + top) > bytes.length) {
      top = bytes.length - offset;
    }

    for (int i = 0; i < top; i++) {
      returnValue <<= 8;
      returnValue |= bytes[offset + i];
    }
    return returnValue;
  }
}
