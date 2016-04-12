package com.hll.ch2.service;

import com.hll.ch2.model.Customer;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.List;

/**
 * CustomerService Tester.
 * @author hll
 * @version 1.0
 * @since <pre>04/11/2016</pre>
 */
public class CustomerServiceTest {

  public CustomerServiceTest() {
    this.customerService = new CustomerService();
  }

  private final CustomerService customerService;

  @Before
  public void before() throws Exception {
  }

  @After
  public void after() throws Exception {
  }

  /**
   * Method: getCustomerList(String keyword)
   */
  @Test
  public void testGetCustomerList() throws Exception {
    List<Customer> customerList = customerService.getCustomerList();
    System.out.println(customerList);
    assert 2==customerList.size();
  }

  /**
   * Method: getCustomer(long id)
   */
  @Test
  public void testGetCustomer() throws Exception {
    Customer customer = customerService.getCustomer(2);
    System.out.println(customer);
  }

  /**
   * Method: createCustomer(Map<String,Object> fieldMap)
   */
  @Test
  public void testCreateCustomer() throws Exception {
//TODO: Test goes here... 
  }

  /**
   * Method: updateCustomer(long id, Map<String,Object> fieldMap)
   */
  @Test
  public void testUpdateCustomer() throws Exception {
//TODO: Test goes here... 
  }

  /**
   * Method: deleteCustomer(long id)
   */
  @Test
  public void testDeleteCustomer() throws Exception {
//TODO: Test goes here... 
  }


} 
