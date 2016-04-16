package com.hll.ch4;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by hll on 2016/4/14.
 */
public class CGLibProxy implements MethodInterceptor {

  private static CGLibProxy cgLibProxy = new CGLibProxy();

  public static CGLibProxy getInstance() {
    return cgLibProxy;
  }

  private CGLibProxy() {
  }

  @SuppressWarnings("unchecked")
  public <T> T getProxy(Class<T> cls) {
    return (T) Enhancer.create(cls, this);
  }

  @Override
  public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
    before();
    Object result = methodProxy.invokeSuper(o, objects);
    after();
    return result;
  }

  private void after() {
    System.out.println("after");
  }

  private void before() {
    System.out.println("before");
  }

  public static void main(String[] args) {
    HelloImpl proxy = CGLibProxy.getInstance().getProxy(HelloImpl.class);
    proxy.say("jack");
  }
}
