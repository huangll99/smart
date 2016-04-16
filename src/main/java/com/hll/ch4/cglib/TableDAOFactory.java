package com.hll.ch4.cglib;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

/**
 * Created by hll on 2016/4/14.
 */
public class TableDAOFactory {
  private static TableDAO tableDAO = new TableDAO();

  public static TableDAO getInstance() {
    return tableDAO;
  }

  public static TableDAO getAuthInstance(AuthProxy authProxy){
    Enhancer enhancer=new Enhancer();
    enhancer.setSuperclass(TableDAO.class);
    enhancer.setCallbacks(new Callback[]{authProxy, NoOp.INSTANCE});
    enhancer.setCallbackFilter(new AuthProxyFilter());
    return (TableDAO) enhancer.create();
  }

}
