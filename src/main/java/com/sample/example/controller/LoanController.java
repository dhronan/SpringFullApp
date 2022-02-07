package com.sample.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.sample.example.entity.Loan;
import com.sample.example.service.LoanService;

@RestController
@RequestMapping("/loan")
@Transactional
public class LoanController {

		@Autowired
	    private LoanService loanService;

	    @RequestMapping(method = RequestMethod.POST)
	    public ResponseEntity<Object> save(@RequestBody Loan loan,UriComponentsBuilder b) {
	    	Loan loantemp=loanService.save(loan);
	    	UriComponents uriComponents = 
	    	        b.path("/loan/{id}").buildAndExpand(loantemp.getId());
	        return ResponseEntity.created(uriComponents.toUri()).body(loan);
	    }

	    @RequestMapping(method = RequestMethod.GET,path="/{id}")
	    public Loan get(@PathVariable Integer id) {
	    	return loanService.get(id);
	        
	    }
	    
}
