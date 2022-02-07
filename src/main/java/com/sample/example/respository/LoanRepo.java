package com.sample.example.respository;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.sample.example.entity.Loan;

@Repository
public class LoanRepo {

	@Autowired
	EntityManager entityManager;
	
	@Autowired
	JdbcTemplate template;
	
	@SuppressWarnings("deprecation")
	public Object save(Loan loan) {
		
		template.update("insert into LOAN(ID, NAME, PRICE) values (?,?,?)",loan.getId(),loan.getName(),loan.getPrice());
		return template.queryForObject("select * from LOAN where id =?",new Object[] {loan.getId()}, new RowMapper<Object>() {

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Loan loan=new Loan();
				loan.setId(rs.getInt(1));
				loan.setName(rs.getString(2));
				loan.setPrice(rs.getLong(3));
				return loan;
			}
			
		});
		
	}

	@SuppressWarnings("deprecation")
	public Loan get(Integer id) {
		return (Loan)template.queryForObject("select * from LOAN where id =?",new Object[] {id}, new RowMapper<Object>() {

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Loan loan=new Loan();
				loan.setId(rs.getInt(1));
				loan.setName(rs.getString(2));
				loan.setPrice(rs.getLong(3));
				return loan;
			}
			
		});
	}
	
}
