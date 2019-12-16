/*
 * Copyright (C) 2018 JavaSmyths javasmyths@javasmyths.com
 */
package com.javasmyths.filters;

import org.apache.commons.codec.binary.Hex;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Richard
 */
public class ByteFiltersTest {
  
  public ByteFiltersTest() {
  }
  String loremIpsum = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua."
          + " Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in"
          + " reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt"
          + " in culpa qui officia deserunt mollit anim id est laborum.";
  
  @BeforeClass
  public static void setUpClass() {
  }
  
  @AfterClass
  public static void tearDownClass() {
  }

  @Test
  public void testXor() {
    System.out.println("****************************************");
    System.out.println("xor");
    System.out.println("****************************************");
    assertEquals("9e", Hex.encodeHexString(ByteFilters.xor("a", (byte)0xFF)));
    assertEquals("a", new String(ByteFilters.xor(ByteFilters.xor("a", (byte)0xFF), (byte)0xFF)));
    assertEquals("be", Hex.encodeHexString(ByteFilters.xor("A", (byte)0xFF)));
    assertEquals("A", new String(ByteFilters.xor(ByteFilters.xor("A", (byte)0xFF), (byte)0xFF)));
    assertEquals("cb", Hex.encodeHexString(ByteFilters.xor("a", (byte)0xAA)));
    assertEquals("bc", Hex.encodeHexString(ByteFilters.xor("a", (byte)0xDD)));
    assertEquals("61", Hex.encodeHexString(ByteFilters.xor("a", (byte)0x00)));
    
    assertEquals("02213c2b236e273e3d3b236e2a2122213c6e3d273a6e2f232b3a626e2d21203"
            + "d2b2d3a2b3a3b3c6e2f2a273e273d2d2720296e2b22273a626e3d2b2a6e2a216e"
            + "2b273b3d23212a6e3a2b233e213c6e27202d272a272a3b203a6e3b3a6e222f2c2"
            + "13c2b6e2b3a6e2a2122213c2b6e232f29202f6e2f22273f3b2f606e1b3a6e2b20"
            + "27236e2f2a6e23272027236e382b20272f23626e3f3b273d6e20213d3a3c3b2a6"
            + "e2b362b3c2d273a2f3a2721206e3b22222f232d216e222f2c213c273d6e20273d"
            + "276e3b3a6e2f22273f3b273e6e2b366e2b2f6e2d212323212a216e2d21203d2b3"
            + "f3b2f3a606e0a3b273d6e2f3b3a2b6e273c3b3c2b6e2a2122213c6e27206e3c2b"
            + "3e3c2b262b202a2b3c273a6e27206e3821223b3e3a2f3a2b6e382b22273a6e2b3"
            + "d3d2b6e2d2722223b236e2a2122213c2b6e2b3b6e283b29272f3a6e203b22222f"
            + "6e3e2f3c272f3a3b3c606e0b362d2b3e3a2b3b3c6e3d27203a6e212d2d2f2b2d2"
            + "f3a6e2d3b3e272a2f3a2f3a6e2021206e3e3c21272a2b203a626e3d3b203a6e27"
            + "206e2d3b223e2f6e3f3b276e212828272d272f6e2a2b3d2b3c3b203a6e2321222"
            + "2273a6e2f2027236e272a6e2b3d3a6e222f2c213c3b2360", 
            Hex.encodeHexString(ByteFilters.xor(loremIpsum, (byte)0x4e)));
  }
  
}
