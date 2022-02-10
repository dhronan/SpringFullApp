package com.sample.example.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.history.Revision;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.sample.example.entity.Product;
import com.sample.example.service.ProductService;

@RestController
@RequestMapping("/product")
@Transactional
@CrossOrigin(origins = "http://localhost:3000")
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

		//@CrossOrigin(origins = "http://localhost:3000")
	    @RequestMapping(method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE,produces = "application/json; charset=utf-8")
	    public ResponseEntity<Object> save(@RequestBody Product product,UriComponentsBuilder b) {
	        
	        product=service.save(product);
	    	
	    	UriComponents uriComponents = 
	    	        b.path("/product/{id}").buildAndExpand(product.getId());
	        
	        return ResponseEntity.created(uriComponents.toUri()).body(Optional.of(product));
	        
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
