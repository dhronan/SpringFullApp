package com.sample.example.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.example.entity.Product;
import com.sample.example.respository.ProductRepo;
import com.sample.example.service.ProductService;

@RestController
@RequestMapping("/product")
@Transactional
public class ProductController {

	 @Autowired
	    private ProductService service;
	 
	 
	 	@RequestMapping(method=RequestMethod.GET,path="/revision/{id}")
	 	public List<Revision<Integer, Product>> getRevisions(@PathVariable("id") String id, @RequestParam("from") Date from, 
	 			@RequestParam("to") Date to) {
	        return service.findRevisionsBetweenDates(0, from, to);
	    }
	 	
	 	@RequestMapping(method=RequestMethod.GET,path="/revision/all/{id}")
	 	public List<Product> getAllRevisions(@PathVariable("id") Integer id) {
	        return service.findAllRevision(id);
	    }

	    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces = "application/json; charset=utf-8")
	    public Product save(@RequestBody Product product) {
	        return service.save(product);
	    }
	    @RequestMapping(method = RequestMethod.GET,path="/sw")
	    public List<Product> getSW(@RequestBody Product product) {
	        return service.getSWProducts();
	    }

	    @GetMapping("/all")
	    public List<Product> getAllProducts() {
	        return service.findAll();
	    }

	    @GetMapping("/{id}")
	    @Cacheable(key = "#id",value = "Product")
	    public Product findProduct(@PathVariable int id) {
	        return service.findById(id).orElseThrow();
	    }

	    @DeleteMapping("/{id}")
	    @CacheEvict(key = "#id",value = "Product")
	    public void remove(@PathVariable int id) {
	         service.deleteById(id);
	    }
	    
	    @PutMapping("/{id}")
	    @CachePut(key = "#id",value = "Product")
	    public Product update(@PathVariable int id,@RequestBody Product product) {
	         return service.save(product);
	    }

}
