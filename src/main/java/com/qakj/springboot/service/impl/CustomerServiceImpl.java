package com.qakj.springboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qakj.springboot.mapper.CustomerMapper;
import com.qakj.springboot.pojo.Customer;
import com.qakj.springboot.pojo.CustomerExample;
import com.qakj.springboot.pojo.CustomerExample.Criteria;
import com.qakj.springboot.service.CustomerService;
@Transactional
@Service
public class CustomerServiceImpl implements CustomerService{
	@Autowired
	private CustomerMapper mapper;
	@Override
	public List<Customer> getAll() {
		CustomerExample example = new CustomerExample();
		List<Customer> list = mapper.selectByExample(example);
		return list;
	}
	@Override
	public void delete(int id) {
		mapper.deleteByPrimaryKey(id);
		
	}
	@Override
	public void addCustomer(Customer customer) {
		mapper.insertSelective(customer);
	}
	@Override
	public Customer getById(int id) {
		Customer customer = mapper.selectByPrimaryKey(id);
		return customer;
	}
	@Override
	public void update(Customer customer) {
		mapper.updateByPrimaryKeySelective(customer);
		
	}
	@Override
	public List<Customer> getCustomer(int currentPage, int pageSize,String searchName) {
		return mapper.selectByLimit((currentPage-1) * pageSize, pageSize,searchName);
	}
	@Override
	public int getCount(String searchName) {
		CustomerExample example = new CustomerExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameLike(searchName);
		int count = mapper.countByExample(example);
		return count;
	}
}
