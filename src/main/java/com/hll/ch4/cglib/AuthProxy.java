package com.hll.ch4.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by hll on 2016/4/14.
 */
public class AuthProxy implements MethodInterceptor{
  private String name;

  public AuthProxy(String name) {
    this.name = name;
  }

  @Override
  public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
    if (!"zhangsan".equals(name)){
      System.out.println("wuquanxian");
      return null;
    }
    return methodProxy.invokeSuper(o,objects);
  }
}
