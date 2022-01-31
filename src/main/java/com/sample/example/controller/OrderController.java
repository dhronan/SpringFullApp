package com.sample.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.example.entity.CusOrder;
import com.sample.example.respository.OrderRepo;

@RestController
@RequestMapping("/order")
@Transactional
public class OrderController {

	 @Autowired
	    private OrderRepo dao;
	 
	 

	    @RequestMapping(method = RequestMethod.POST)
	    public CusOrder save(@RequestBody CusOrder CusOrder) {
	        return dao.save(CusOrder);
	    }

	    @GetMapping
	    public List<CusOrder> getAllOrders() {
	        return dao.findAll();
	    }

	    @GetMapping("/{id}")
	    @Cacheable(key = "#id",value = "CusOrder")
	    public CusOrder findOrder(@PathVariable int id) {
	        return dao.findById(id).orElseThrow();
	    }

	    @DeleteMapping("/{id}")
	    @CacheEvict(key = "#id",value = "CusOrder")
	    public void remove(@PathVariable int id) {
	         dao.deleteById(id);
	    }
	    
	    @PutMapping("/{id}")
	    @CachePut(key = "#id",value = "CusOrder")
	    public CusOrder update(@PathVariable int id,@RequestBody CusOrder CusOrder) {
	         return dao.save(CusOrder);
	    }

}
