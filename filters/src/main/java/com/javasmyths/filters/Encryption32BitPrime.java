/*
 * Copyright (C) 2018 JavaSmyths javasmyths@javasmyths.com
 */
package com.javasmyths.filters;

import java.nio.ByteBuffer;

/**
 *
 * @author Richard
 */
public class Encryption32BitPrime implements com.javasmyths.filters.Encryption {

  @Override
  public byte[] encrypt(byte[] bytes, long primePassword) {
    if (bytes != null) {
      ByteBuffer byteBuffer = ByteBuffer.allocate((((bytes.length - 1) / 4) * 8 + 12));
      byteBuffer.putInt(0, bytes.length);
      
      for (int i = 0; i < bytes.length; i += 4) {
        long encryptedLong = integerFromBytes(bytes, i) * primePassword;
        byteBuffer.putLong((i * 2) + 4, encryptedLong);
      }
      return byteBuffer.array();
      
    } else {
      return null;
    }
  }

  /**
   * don't over thing it.
   *
   * @param bytes
   * @param primePassword
   * @return
   */
  @Override
  public byte[] decrypt(byte[] bytes, long primePassword) {
    if (bytes != null) {
      ByteBuffer byteBufferIn = ByteBuffer.wrap(bytes);
      ByteBuffer byteBufferOut = ByteBuffer.allocate(bytes.length / 2);
      int size = byteBufferIn.getInt();
      byte returnBytes [] = new byte[size];
      
      for (int i = 0; i < (bytes.length - 4); i += 8) {
        long dividend = byteBufferIn.getLong();
        long decryptedLong = dividend / primePassword;
        byteBufferOut.putInt(i / 2, (int)decryptedLong);
      }
      
      ByteBuffer get = byteBufferOut.get(returnBytes, 0, size);
      return returnBytes;
      
    } else {
      return null;
    }
  }

  /**
   * return a long from a byte string.
   * 
   * @param bytes
   * @param offset
   * @return 0 if any problems
   */
  public long integerFromBytes(byte bytes[], int offset) {
    long returnValue = 0;
    if (offset < bytes.length) returnValue |= bytes[offset];
    returnValue <<= 8;

    offset++;
    if (offset < bytes.length) returnValue |= bytes[offset];
    returnValue <<= 8;
    
    offset++;
    if (offset < bytes.length) returnValue |= bytes[offset];
    returnValue <<= 8;
    
    offset++;
    if (offset < bytes.length) returnValue |= bytes[offset];
    return returnValue;
  }
}
