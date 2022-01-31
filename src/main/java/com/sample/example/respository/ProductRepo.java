package com.sample.example.respository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import com.sample.example.entity.Product;

import java.util.List;

@Repository
public interface ProductRepo extends RevisionRepository<Product, Integer,Integer>,JpaRepository<Product, Integer>{
	
}
