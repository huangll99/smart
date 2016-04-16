package com.hll.ch4.threadlocl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by hll on 2016/4/15.
 */
public class SequenceC {
  private static ThreadLocal<Integer> threadLocalNumber = new ThreadLocal<Integer>() {
    @Override
    protected Integer initialValue() {
      return 0;
    }
  };

  public static int getNumber() {
    threadLocalNumber.set(threadLocalNumber.get() + 1);
    return threadLocalNumber.get();
  }

  public static void main(String[] args) {
    ExecutorService p = Executors.newFixedThreadPool(3);
    p.submit(() -> {
      for (int i = 0; i < 10; i++) {
        System.out.println(getNumber());
      }
    });
    p.submit(() -> {
      for (int i = 0; i < 10; i++) {
        System.out.println(getNumber());
      }
    });
    p.submit(() -> {
      for (int i = 0; i < 10; i++) {
        System.out.println(getNumber());
      }
    });
  }
}
