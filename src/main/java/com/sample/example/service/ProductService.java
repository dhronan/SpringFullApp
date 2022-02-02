package com.sample.example.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.stereotype.Service;

import com.sample.example.entity.Product;
import com.sample.example.respository.ProductRepo;

@Service
public class ProductService {

	@Autowired
	ProductRepo productRepo;

	public Product save(Product product) {
		return productRepo.save(product);
	}

	public Optional<Product> findById(int id) {
		return productRepo.findById(id);
	}

	public List<Product> findAll() {
		return productRepo.findAll();
	}

	public void deleteById(int id) {

		productRepo.deleteById(id);
	}
	
	public List<Revision<Integer, Product>> findRevisionsBetweenDates(int id, Date startDate, Date endDate){
        return productRepo.findRevisions(id).getContent().stream().filter(
            revision -> revision.getEntity().getCreatedOn().getTime() > startDate.getTime() && 
                revision.getEntity().getCreatedOn().getTime() < endDate.getTime()
         ).collect(Collectors.toList());
	}

	public List<Product> getSWProducts() {
		return productRepo.getSWProducts();
	}

	public List<Product> findAllRevision(Integer id){
		return productRepo.findRevisions(id).getContent().stream().map(x->x.getEntity()).collect(Collectors.toList());
		
	}
	
}
