package com.hll.ch3.controller;

import com.hll.ch3.model.Customer;
import com.hll.ch3.service.CustomerService;
import com.hll.framework.annotation.Action;
import com.hll.framework.annotation.Controller;
import com.hll.framework.annotation.Inject;
import com.hll.framework.bean.Param;
import com.hll.framework.bean.View;

import java.util.List;

/**
 * Created by hll on 2016/4/13.
 */
@Controller
public class CustomerController {
  @Inject
  CustomerService customerService;

  @Action("get:/customers")
  public View getCustomers(Param param){
    List<Customer> customerList = customerService.getCustomerList();
    return new View("customer.jsp").addModel("customerList",customerList);
  }
}
