package com.sample.example.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;

import com.sample.example.entity.CusOrder;

@Repository
public interface OrderRepo extends RevisionRepository<CusOrder, Integer,Integer>,JpaRepository<CusOrder, Integer>{
	
}
