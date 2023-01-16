package com.api.conta.services.impl;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.conta.dto.ContaDTO;

@SpringBootTest
class ContaServicesImplTest {

	
	@Mock
	ContaDTO contaDTO;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	private ContaServicesImpl createTestSubject() {
		ContaServicesImpl service = new ContaServicesImpl();
		return service;
	}

	@Test
	void testFindAll() throws Exception {
		ContaServicesImpl testSubject;
		List<ContaDTO> result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.findAll();
		assertNotNull(result);
	}
	

	@Test
	void testFindById() throws Exception {
		ContaServicesImpl testSubject;
		Integer idConta = 0;
		ContaDTO result;

		testSubject = createTestSubject();
		
		// default test
		
		result = testSubject.findById(0);
	}

	@Test
	void testSave() throws Exception {
		ContaServicesImpl testSubject;
		List<ContaDTO> result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.save(getContasDTO());
	}
	
	@Test
	void testDelete() throws Exception {
		ContaServicesImpl testSubject;
		Integer idConta = 0;

		// default test
		testSubject = createTestSubject();
		testSubject.delete(idConta);
	}
	

	private List<ContaDTO> getContasDTO() {
		List<ContaDTO>contas = new ArrayList<>();
		return contas;
	}
}