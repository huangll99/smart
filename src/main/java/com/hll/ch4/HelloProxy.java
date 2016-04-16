package com.hll.ch4;

/**
 * Created by hll on 2016/4/14.
 */
public class HelloProxy implements Hello {

  private Hello hello;

  public HelloProxy() {
    this.hello=new HelloImpl();
  }

  @Override
  public void say(String name) {
    before();
    hello.say(name);
    after();
  }

  private void after() {
    System.out.println("after");
  }

  private void before() {
    System.out.println("before");
  }

  public static void main(String[] args) {
    Hello hello = new HelloProxy();
    hello.say("jack");
  }
}
