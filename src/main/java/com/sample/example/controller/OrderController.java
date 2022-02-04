package com.sample.example.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.history.Revision;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.sample.example.entity.CusOrder;
import com.sample.example.respository.OrderRepo;
import com.sample.example.service.OrderService;

@RestController
@RequestMapping("/order")
@Transactional
public class OrderController {

		@Autowired
	    private OrderService orderService;
	 
	 

	    @RequestMapping(method = RequestMethod.POST)
	    public ResponseEntity<Object> save(@RequestBody CusOrder CusOrder,UriComponentsBuilder b) {
	    	CusOrder order=orderService.save(CusOrder);
	    	
	    	UriComponents uriComponents = 
	    	        b.path("/order/{id}").buildAndExpand(order.getId());
	    	
	    	
	        
	        return ResponseEntity.created(uriComponents.toUri()).build();
	    }

	    @RequestMapping(method=RequestMethod.POST,path="/revision")
	    public List<Revision<Integer, CusOrder>> getRevisions(int id, Date dateFrom, Date dateTo) {
	        return orderService.findRevisionsBetweenDates(id, dateFrom, dateTo);
	    }
	    @GetMapping
	    public List<CusOrder> getAllOrders() {
	        return orderService.findAll();
	    }

	    @GetMapping("/{id}")
	    @Cacheable(key = "#id",value = "CusOrder")
	    public CusOrder findOrder(@PathVariable int id) {
	        return orderService.findById(id).orElseThrow();
	    }

	    @DeleteMapping("/{id}")
	    @CacheEvict(key = "#id",value = "CusOrder")
	    public void remove(@PathVariable int id) {
	    	orderService.deleteById(id);
	    }
	    
	    @PutMapping("/{id}")
	    @CachePut(key = "#id",value = "CusOrder")
	    public CusOrder update(@PathVariable int id,@RequestBody CusOrder CusOrder) {
	         return orderService.save(CusOrder);
	    }

}
