package com.sample.example.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.stereotype.Service;

import com.sample.example.entity.CusOrder;
import com.sample.example.entity.Loan;
import com.sample.example.respository.LoanRepo;
import com.sample.example.respository.OrderRepo;

@Service
public class LoanService {

	@Autowired
	LoanRepo loanRepo;

	public Loan save(Loan loan) {
		return (Loan) loanRepo.save(loan);
	}

	public Loan get(Integer id) {
		return (Loan) loanRepo.get(id);
	}

	
}
