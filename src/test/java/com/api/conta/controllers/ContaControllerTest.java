package com.api.conta.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.api.conta.dto.ContaDTO;
import com.api.conta.repositories.ContaRepository;
import com.api.conta.response.Response;
import com.api.conta.services.impl.ContaServicesImpl;

@SpringBootTest
public class ContaControllerTest {

	@Mock
	ContaRepository repository;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}
	
	private ContaController createTestSubject() {
		return new ContaController();
	}

	
	@Test
	public void testFindContas() throws Exception {
		ContaController testSubject;
		HttpServletRequest request = null;
		ResponseEntity<Response<List<ContaDTO>>> result;

		// default test 1
		testSubject = createTestSubject();
		result = testSubject.findContas(request);
	}

	@Test
	public void testFindContas_1() throws Exception {
		ContaController testSubject;
		HttpServletRequest request = null;
		ResponseEntity<Response<List<ContaDTO>>> result;

		// default test 1
		testSubject = createTestSubject();
		result = testSubject.findContas(request);
	}

	@Test
	public void testFindById() throws Exception {
		ContaController testSubject;
		Integer id = 0;
		ResponseEntity<Response<ContaDTO>> result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.findById(id);
	}

	@Test
	public void testSaveUsuario() throws Exception {
		ContaController testSubject;
		List<ContaDTO> contaDTO = null;
		ResponseEntity<Response<List<ContaDTO>>> result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.saveUsuario(contaDTO);
	}

	@Test
	public void testUpdate() throws Exception {
		ContaController testSubject;
		List<ContaDTO> contaDTO = null;
		ResponseEntity<Response<List<ContaDTO>>> result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.update(contaDTO);
	}

	@Test
	public void testDelete() throws Exception {
		ContaController testSubject;
		Integer id = 0;
		ResponseEntity<Response<String>> result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.delete(id);
	}
}