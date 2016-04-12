package com.hll.ch2.service;

import com.hll.ch2.model.Customer;
import com.hll.utils.DBHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.*;

/**
 * Created by hll on 2016/4/11.
 */
public class CustomerService {

  private static Logger logger = LoggerFactory.getLogger(CustomerService.class.getName());

  /*public List<Customer> getCustomerList(String keyword) {
    Connection conn = null;
    try {
      List<Customer> customerList = new ArrayList<>();
      String sql = "select * from customer";
      conn = getConnection();
      PreparedStatement stmt = conn.prepareStatement(sql);
      ResultSet rs = stmt.executeQuery();
      while (rs.next()) {
        Customer customer = new Customer();
        customer.setId(rs.getLong("id"));
        customer.setName(rs.getString("name"));
        customer.setContact(rs.getString("contact"));
        customer.setTelephone(rs.getString("email"));
        customer.setEmail(rs.getString("remark"));
        customerList.add(customer);
      }
      return customerList;
    } catch (SQLException e) {
      logger.error("execute sql failure", e);
    } finally {
      if (conn != null) {
        closeConnection(conn);
      }
    }
    //noinspection unchecked
    return Collections.EMPTY_LIST;
  }*/

  public List<Customer> getCustomerList() {
    String sql = "select * from customer";
    return DBHelper.queryEntityList(Customer.class, sql);
  }

  public Customer getCustomer(long id) {
    return DBHelper.queryEntity(Customer.class,"select * from customer where id=?",id);
  }

  public boolean createCustomer(Map<String, Object> fieldMap) {
    return DBHelper.insertEntity(Customer.class,fieldMap);
  }

  public boolean updateCustomer(long id, Map<String, Object> fieldMap) {
    return DBHelper.updateEntity(Customer.class,id,fieldMap);
  }

  public boolean deleteCustomer(long id) {
    return DBHelper.deleteEntity(Customer.class,id);
  }
}
