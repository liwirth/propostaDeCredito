package com.testecredito.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.testecredito.PropostaCreditoApplication;
import com.testecredito.entity.Proposta;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PropostaCreditoApplication.class)
@WebAppConfiguration
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PropostaControllerTests {
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private ObjectMapper mapper;
	@Before
	public void setup() throws Exception {
		this.mockMvc = webAppContextSetup(this.webApplicationContext).build();
	}

	@Test
	public void _findAllPropostas() throws Exception {
		this.mockMvc.perform(get("/propostas")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(4)));
	}

	@Test
	public void findOneProposta() throws Exception {
		this.mockMvc.perform(get("/propostas/1")).andExpect(status().isOk()).andExpect(jsonPath("$.id", equalTo(1)))
				.andExpect(jsonPath("$.cpf", equalToIgnoringCase("02432569874")));
	
	}

	@Test
	public void addProposta() throws Exception {
		final Proposta propostaCadastrada = new Proposta("Joao Teste", "3542178695", "SC", 25, 'M', "Casado(a)", 1, new BigDecimal(1000));
		final String propostaJson = this.mapper.writeValueAsString(propostaCadastrada);
		System.out.println("************ " + propostaJson);
		this.mockMvc.perform(post("/propostas").contentType(MediaType.APPLICATION_JSON).content(propostaJson))
				.andExpect(status().isCreated())
				.andExpect(header().string(HttpHeaders.LOCATION, "http://localhost/propostas/5"));
	}
	
}
