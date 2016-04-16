package com.hll.ch4;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by hll on 2016/4/14.
 */
public class DynamicProxy implements InvocationHandler {

  private Object target;

  public DynamicProxy(Object target) {
    this.target = target;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    before();
    Object result = method.invoke(target, args);
    after();
    return result;
  }

  @SuppressWarnings("unchecked")
  public <T> T getProxy() {
    return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
  }

  private void after() {
    System.out.println("after");
  }

  private void before() {
    System.out.println("before");
  }

  public static void main(String[] args) {
    DynamicProxy dynamicProxy=new DynamicProxy(new HelloImpl());
    Hello helloProxy = dynamicProxy.getProxy();
    helloProxy.say("jack");
  }
}
