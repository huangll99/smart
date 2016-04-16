package com.hll.ch3.aop;

import com.hll.framework.annotation.Aspect;
import com.hll.framework.annotation.Service;
import com.hll.framework.proxy.AspectProxy;

import java.lang.reflect.Method;

/**
 * Created by hll on 2016/4/15.
 */
@Aspect(Service.class)
public class ServiceAspect extends AspectProxy {
  @Override
  public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
    System.out.println("start...");
    System.out.println(cls);
    System.out.println(method);
    System.out.println(params);
  }

  @Override
  public void after(Class<?> cls, Method method, Object[] params, Object result) {
    System.out.println("end...");
  }


}
