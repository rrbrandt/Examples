/*
 * Copyright (C) 2018 JavaSmyths javasmyths@javasmyths.com
 */
package com.javasmyths.filters;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Richard
 */
public class Encryption32BitPrimeTest {

  public Encryption32BitPrimeTest() {
  }

  @BeforeClass
  public static void setUpClass() {
  }

  @AfterClass
  public static void tearDownClass() {
  }

  @Test
  public void testEncrypt() {
    System.out.println("****************************************");
    System.out.println("encrypt");
    System.out.println("****************************************");
    byte[] bytes = null;
    long primePassword = 0L;
    Encryption32BitPrime instance = new Encryption32BitPrime();
    byte[] expResult = null;
    byte[] result = instance.encrypt(bytes, primePassword);
    assertArrayEquals(expResult, result);

    result = instance.encrypt("Vini Vidi Vici.".getBytes(), instance.integerFromBytes("Spock".getBytes(), 0));
    expResult = new byte[]{0, 0, 0, 15, 28, 42, 34, -123, 122, -107, 57, -101, 10, -118, 56, 11, -128, -35, 29, -84, 34, 67, -89, -25, -123, -24, -15, -101, 32, 102, -61, 47, 44, -98, -54, 0};
    assertArrayEquals(expResult, result);
  }

  @Test
  public void testDecrypt() {
    System.out.println("****************************************");
    System.out.println("decrypt");
    System.out.println("****************************************");
    byte[] bytes = null;
    long primePassword = 0L;
    Encryption32BitPrime instance = new Encryption32BitPrime();
    byte[] expResult = null;
    byte[] result = instance.decrypt(bytes, primePassword);
    assertArrayEquals(expResult, result);

    result = instance.decrypt(new byte[]{0, 0, 0, 15, 28, 42, 34, -123, 122, -107, 57, -101, 10, -118, 56, 11, -128, -35, 29, -84, 34, 67, -89, -25, -123, -24, -15, -101, 32, 102, -61, 47, 44, -98, -54, 0},
             instance.integerFromBytes("Spock".getBytes(), 0));
    assertArrayEquals("Vini Vidi Vici.".getBytes(), result);
  }

}
