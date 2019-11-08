/*
 * Copyright (C) 2018 JavaSmyths javasmyths@javasmyths.com
 */
package com.javasmyths.diningphilosiphers;

/**
 *
 * @author Richard
 */
public class ChopStick {
  
  private ChopStickState chopStickState = ChopStickState.AVAILABLE;
  private String philosopherName = "";
  
  public ChopStickState getChopStickState() {
    return chopStickState;
  }
  public void setChopStickState(ChopStickState chopStickState) {
    this.chopStickState = chopStickState;
  }
  public String getPhilosopher() {
    return philosopherName;
  }
  public void setPhilosopher(String philosopherName) {
    this.philosopherName = philosopherName;
  }

  @Override
  public String toString() {
    return "ChopStick{" + "chopStickState=" + chopStickState + ", philosopherName=" + philosopherName + '}';
  }
  
}
