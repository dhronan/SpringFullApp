package com.javatechie.redis.controller.test;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.example.controller.ProductController;
import com.sample.example.entity.Product;
import com.sample.example.respository.ProductRepo;

@SpringBootTest(classes=ProductController.class)
@AutoConfigureMockMvc
@EnableWebMvc
public class ProductControllerTest {
	
	@MockBean
	ProductRepo repo;
	
	@Autowired
	private MockMvc mockMvc;
	
	

	@Test
	void save_working() throws Exception {
		
		
		String a=new ObjectMapper().writeValueAsString(new Product(1,"Pen",2,12,null,null,null,null,null));
		
		when(repo.save(Mockito.any(Product.class))).thenReturn(new Product(1,"Pen",2,12,null,null,null,null,null));
		
		mockMvc.perform(post("/product").characterEncoding("UTF-8").
				contentType(MediaType.APPLICATION_JSON_VALUE).
				content(a)).andExpect(status().isOk()).
		
		
		andExpect(jsonPath("$.name", is("Pen")));
	}

}
