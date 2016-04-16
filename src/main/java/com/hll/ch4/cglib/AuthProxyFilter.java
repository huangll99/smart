package com.hll.ch4.cglib;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * Created by hll on 2016/4/14.
 */
public class AuthProxyFilter implements CallbackFilter {
  @Override
  public int accept(Method method) {
    if (!"query".equalsIgnoreCase(method.getName())){
      return 0;
    }
    return 1;
  }
}
