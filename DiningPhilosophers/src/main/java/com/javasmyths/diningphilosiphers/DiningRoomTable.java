/*
 * Copyright (C) 2018 JavaSmyths javasmyths@javasmyths.com
 */
package com.javasmyths.diningphilosiphers;

import java.util.ArrayList;

/**
 *
 * @author Richard
 */
public class DiningRoomTable {
  
  private ArrayList<Philosopher> philosophers;

  public DiningRoomTable() {
    this.philosophers = new ArrayList<>();
  }

  public void start() {
    for (Philosopher philosopher : philosophers) {
      philosopher.start();
    }
  }
  
  public void addPhilosopher(Philosopher philosopher) {
    if (philosophers.isEmpty()) {
      philosopher.setLeftChopStick(new ChopStick());
      philosophers.add(philosopher);
    } else {
      philosopher.setLeftChopStick(new ChopStick());
      philosopher.setRightChopStick(philosophers.get(philosophers.size() - 1).getLeftChopStick());
      philosophers.add(philosopher);
      philosophers.get(0).setRightChopStick(philosopher.getLeftChopStick());
    }
  }
  
  public ArrayList<Philosopher> getPhilosophers() {
    return philosophers;
  }

  public void setPhilosophers(ArrayList<Philosopher> philosophers) {
    this.philosophers = philosophers;
  }

  @Override
  public String toString() {
    return "DinningRoomTable{" + "philosophers=" + philosophers + '}';
  }

}
