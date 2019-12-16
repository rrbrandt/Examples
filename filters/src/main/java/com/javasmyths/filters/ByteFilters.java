/*
 * Copyright (C) 2018 JavaSmyths javasmyths@javasmyths.com
 */
package com.javasmyths.filters;

/**
 *
 * @author Richard
 */
public class ByteFilters {
  public static byte [] xor(String str, byte xorByte) {
    return xor( str.getBytes() , xorByte);
  }
  
  public static byte [] xor(byte [] bytes, byte xorByte) {
    for (int i = 0; i < bytes.length; i++) {
      bytes [i] ^= xorByte;
    }
    return bytes;
  }
}
