package com.hll.ch2.service;

import com.hll.ch2.model.Customer;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by hll on 2016/4/11.
 */
public class CustomerService {
  public List<Customer> getCustomerList(String keyword){
    return Arrays.asList(new Customer(),new Customer());
  }

  public Customer getCustomer(long id){
    return null;
  }

  public boolean createCustomer(Map<String,Object> fieldMap){
    return false;
  }

  public boolean updateCustomer(long id,Map<String,Object> fieldMap){
    return false;
  }

  public boolean deleteCustomer(long id){
    return false;
  }
}
