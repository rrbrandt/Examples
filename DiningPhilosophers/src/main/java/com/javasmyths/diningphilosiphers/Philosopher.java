/*
 * Copyright (C) 2018 JavaSmyths javasmyths@javasmyths.com
 */
package com.javasmyths.diningphilosiphers;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Richard
 */
public final class Philosopher extends Thread {

  private final int MAX_EATING_TIME = 2000;
  private final int MAX_TALKING_TIME = 1000;
  private final int MIN_EATING_TIME = 1000;
  private final int MIN_TALKING_TIME = 1000;

  private String philospherName;
  private PhilosopherState philosopherState;
  private ChopStick leftChopStick;
  private ChopStick rightChopStick;
  private final Random random = new Random();

  public Philosopher(String name) {
    this.philospherName = name;
    setPhilosipherState(changeState());
  }

  @Override
  public void run() {
    try {
      while (true) {
        PhilosopherState philosopherStatePrime = philosopherState;
//        System.out.println(philospherName + " " + philosopherStatePrime);

        switch (philosopherStatePrime) {
          case EAT:
            if ((random.nextInt() % 2) == 0) {
              System.out.println(philospherName + " Attempt to grab Left Chop Stick");
              acquireChopStick(leftChopStick, "left");
              System.out.println(philospherName + " Attempt to grab Right Chop Stick");
              acquireChopStick(rightChopStick, "right");
            } else {
              System.out.println(philospherName + " Attempt to grab Right Chop Stick");
              acquireChopStick(rightChopStick, "right");
              System.out.println(philospherName + " Attempt to grab Left Chop Stick");
              acquireChopStick(leftChopStick, "left");
            }
            setPhilosipherState(PhilosopherState.EATING);
            System.out.println(philospherName + " " + getPhilosipherState());
            Thread.sleep(MIN_EATING_TIME + (int)(random.nextFloat() * (float)MAX_EATING_TIME));
            leftChopStick.setChopStickState(ChopStickState.AVAILABLE);
            leftChopStick.setPhilosopher("");
            rightChopStick.setChopStickState(ChopStickState.AVAILABLE);
            rightChopStick.setPhilosopher("");
            break;
          case TALK:
            Thread.sleep(MIN_TALKING_TIME + (int)(random.nextFloat() * (float)MAX_TALKING_TIME));
            break;
        }

        setPhilosipherState(changeState());
        System.out.println(philospherName + " done " + philosopherStatePrime + " next state = " + getPhilosipherState());
      }
    } catch (Throwable t) {
      System.out.println("Exception! + " + t);
    }
  }

  /**
   * Attempt to aquire 
   * @param chopStick
   * @param whichChopStick
   * @throws InterruptedException 
   */
  private synchronized void acquireChopStick(ChopStick chopStick, String whichChopStick) throws InterruptedException {
    int count = 0;
    
    while (ChopStickState.INUSE == chopStick.getChopStickState() && !chopStick.getPhilosopher().equals(getPhilospherName()))
    {
      Thread.sleep(MAX_EATING_TIME / 10);
      
      if (count++ > 40) {
        System.out.println(getPhilospherName() + " DEADLOCK! "  + whichChopStick + " ChopStick State = " + chopStick.getChopStickState());
      }
    };
    
    chopStick.setPhilosopher(philospherName);
    chopStick.setChopStickState(ChopStickState.INUSE);
    Thread.sleep(500);
  }

  public PhilosopherState getPhilosipherState() {
    return philosopherState;
  }

  public void setPhilosipherState(PhilosopherState philosopherState) {
    this.philosopherState = philosopherState;
  }

  public String getPhilospherName() {
    return philospherName;
  }

  public void setPhilospherName(String philospherName) {
    this.philospherName = philospherName;
  }

  public ChopStick getLeftChopStick() {
    return leftChopStick;
  }

  public void setLeftChopStick(ChopStick leftChopStick) {
    this.leftChopStick = leftChopStick;
  }

  public ChopStick getRightChopStick() {
    return rightChopStick;
  }

  public void setRightChopStick(ChopStick rightChopStick) {
    this.rightChopStick = rightChopStick;
  }

  private PhilosopherState changeState() {
    int randomState = random.nextInt();

    switch (randomState % 2) {
      case 0:
        return PhilosopherState.EAT;
      default:
        return PhilosopherState.TALK;
    }
  }

  @Override
  public String toString() {
    return "{" + philospherName + ", " + philosopherState
            + ", left CS=(" + leftChopStick.getPhilosopher() + ")" + leftChopStick.getChopStickState()
            + ", right CS=(" + rightChopStick.getPhilosopher() + ")" + rightChopStick.getChopStickState() + '}';
  }

}
