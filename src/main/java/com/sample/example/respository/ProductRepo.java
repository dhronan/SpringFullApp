package com.sample.example.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import com.sample.example.entity.Product;

import java.util.List;

@Repository
public interface ProductRepo extends RevisionRepository<Product, Integer,Integer>,JpaRepository<Product, Integer>{
	
	@Query("select p from Product p where name like 'SW%'")
	List<Product> getSWProducts();
	
}
