/*
 * Copyright (C) 2018 JavaSmyths javasmyths@javasmyths.com
 */
package com.javasmyths.diningphilosiphers;

/**
 *
 * @author Richard
 */
public class Philosophers {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) throws InterruptedException {
    int tick = 0;
    DiningRoomTable diningRoomTable = new DiningRoomTable();

    diningRoomTable.addPhilosopher(new Philosopher("Socrates"));
    diningRoomTable.addPhilosopher(new Philosopher("Plato"));
    diningRoomTable.addPhilosopher(new Philosopher("Aristotle"));
//    diningRoomTable.addPhilosopher(new Philosopher("Pythagoras"));

    System.out.println(tick++ + ":" + diningRoomTable);
    diningRoomTable.start();

    while (true) {
      Thread.sleep(500);
      System.out.println(tick++ + ":" + diningRoomTable);
    }
  }

}
