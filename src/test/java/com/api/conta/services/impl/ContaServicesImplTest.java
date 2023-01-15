package com.api.conta.services.impl;

import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.api.conta.dto.ContaDTO;
import com.api.conta.entities.Conta;
import com.api.conta.repositories.ContaRepository;

@SpringBootTest
public class ContaServicesImplTest {

	@Mock
	ContaRepository repository;
	
	@Mock
	ContaDTO contaDTO;
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	private ContaServicesImpl createTestSubject() {
		ContaServicesImpl service = new ContaServicesImpl();
		service.repository = repository;
		return service;
	}

	@Test
	public void testFindAll() throws Exception {
		ContaServicesImpl testSubject;
		List<ContaDTO> result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.findAll();
	}
	

	@Test
	public void testFindById() throws Exception {
		ContaServicesImpl testSubject;
		Integer idConta = 0;
		ContaDTO result;

		testSubject = createTestSubject();
		
		when(repository.findByIdConta(0)).thenReturn(getConta());
		// default test
		
		result = testSubject.findById(0);
	}

	@Test
	public void testSave() throws Exception {
		ContaServicesImpl testSubject;
		List<ContaDTO> result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.save(getContas());
	}
	
	@Test
	public void testDelete() throws Exception {
		ContaServicesImpl testSubject;
		Integer idConta = 0;

		// default test
		testSubject = createTestSubject();
		testSubject.delete(idConta);
	}
	
	private Conta getConta() {
		return Mockito.mock(Conta.class);
	}
	private List<ContaDTO> getContas() {
		List<ContaDTO>contas = new ArrayList<ContaDTO>();
		contas.add(Mockito.mock(ContaDTO.class));
		return contas;
	}
}