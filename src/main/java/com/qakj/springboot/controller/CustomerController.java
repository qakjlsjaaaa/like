package com.qakj.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qakj.springboot.pojo.Customer;
import com.qakj.springboot.service.CustomerService;
@RequestMapping("/customerController")
@RestController
public class CustomerController {
	@Autowired
	private CustomerService service;
	
	@RequestMapping("/getCustomer")
	public Map<String,Object> getCustomer(@RequestParam("currentPage") Integer currentPage,@RequestParam("pageSize") Integer pageSize,@RequestParam("searchName") String searchName){
		//判断搜索的名字
		if(searchName == null || "".equals(searchName)){
			searchName = "%%";
		}else{
			searchName = "%"+searchName+"%";
		}
		//页面显示的数据
		List<Customer> list = service.getCustomer(currentPage, pageSize,searchName);
		//获取总记录数
		int count = service.getCount(searchName);
		//总页数,可以进行计算
		int countPage = count % pageSize == 0?count / pageSize: (count / pageSize) + 1;
		//需要把总页数和页面显示的数据都响应json
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("countPage", countPage);
		map.put("list", list);
		return map;
	}
	
	
	@RequestMapping("/findAll")
	public List<Customer> findAll(){
		List<Customer> list = service.getAll();
		return list;
	}
	
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id){
		service.delete(id);
		return "删除成功";
	}
	@RequestMapping("/add")
	public String add(Customer customer){
		service.addCustomer(customer);
		return "添加成功";
	}
	
	@RequestMapping("/edit/{id}")
	public Customer getById(@PathVariable("id") Integer id){
		Customer customer = service.getById(id);
		return customer;
	}
	@RequestMapping("/update")
	public String update(Customer customer){
		service.update(customer);
		return "修改成功";
	}
	
	
}
