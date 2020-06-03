package com.qakj.springboot.service;

import java.util.List;

import com.qakj.springboot.pojo.Customer;

public interface CustomerService {
	//返回值页面上显示数据，第一个参数是当前页，第二参数是每页显示多条
	List<Customer> getCustomer(int currentPage,int pageSize,String searchName);
	//返回值获取总记录数
	int getCount(String searchName);
	
	List<Customer> getAll();
	
	void delete(int id);
	
	void addCustomer(Customer customer);
	
	Customer getById(int id);
	
	void update(Customer customer);
	
}
