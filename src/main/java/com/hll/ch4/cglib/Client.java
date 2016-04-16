package com.hll.ch4.cglib;

/**
 * Created by hll on 2016/4/14.
 */
public class Client {
  public static void main(String[] args) {
    TableDAO tableDAO = TableDAOFactory.getAuthInstance(new AuthProxy("zhangsan1"));
    tableDAO.create();
    tableDAO.query();
  }
}
