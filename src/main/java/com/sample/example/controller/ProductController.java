package com.sample.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.example.entity.Product;
import com.sample.example.respository.ProductRepo;

@RestController
@RequestMapping("/product")
@Transactional
public class ProductController {

	 @Autowired
	    private ProductRepo dao;
	 
	 

	    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces = "application/json; charset=utf-8")
	    public Product save(@RequestBody Product product) {
	        return dao.save(product);
	    }

	    @GetMapping
	    public List<Product> getAllProducts() {
	        return dao.findAll();
	    }

	    @GetMapping("/{id}")
	    @Cacheable(key = "#id",value = "Product")
	    public Product findProduct(@PathVariable int id) {
	        return dao.findById(id).orElseThrow();
	    }

	    @DeleteMapping("/{id}")
	    @CacheEvict(key = "#id",value = "Product")
	    public void remove(@PathVariable int id) {
	         dao.deleteById(id);
	    }
	    
	    @PutMapping("/{id}")
	    @CachePut(key = "#id",value = "Product")
	    public Product update(@PathVariable int id,@RequestBody Product product) {
	         return dao.save(product);
	    }

}
