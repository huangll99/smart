package com.hll.ch4;

/**
 * Created by hll on 2016/4/14.
 */
public final class HelloImpl implements Hello {
  @Override
  public void say(String name) {
    System.out.println("hello " + name);
  }
}
