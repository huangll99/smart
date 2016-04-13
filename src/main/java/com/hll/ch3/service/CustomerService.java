package com.hll.ch3.service;

import com.hll.ch3.model.Customer;
import com.hll.ch3.utils.DBHelper;
import com.hll.framework.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by hll on 2016/4/11.
 */
@Service
public class CustomerService {

  private static Logger logger = LoggerFactory.getLogger(CustomerService.class.getName());

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
