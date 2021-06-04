/*
 * Copyright (C) 2018 JavaSmyths javasmyths@javasmyths.com
 */
package com.javasmyths.filters;

/**
 *
 * @author Richard
 */
public interface Encryption {
  public byte [] encrypt (byte bytes[], long primePassword);
  public byte [] decrypt (byte bytes[], long primePassword);
}
