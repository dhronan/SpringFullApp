package com.sample.example.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.stereotype.Service;

import com.sample.example.entity.CusOrder;
import com.sample.example.respository.OrderRepo;

@Service
public class OrderService {

	@Autowired
	OrderRepo orderRepo;

	public CusOrder save(CusOrder cusOrder) {
		return orderRepo.save(cusOrder);
	}

	public Optional<CusOrder> findById(int id) {
		return orderRepo.findById(id);
	}

	public List<CusOrder> findAll() {
		return orderRepo.findAll();
	}

	public void deleteById(int id) {

		orderRepo.deleteById(id);
	}
	
	public List<Revision<Integer, CusOrder>> findRevisionsBetweenDates(int id, Date startDate, Date endDate){
        return orderRepo.findRevisions(id).getContent().stream().filter(
            revision -> revision.getEntity().getCreatedOn().getTime() > startDate.getTime() && 
                revision.getEntity().getCreatedOn().getTime() < endDate.getTime()
         ).collect(Collectors.toList());
	}

	
}
