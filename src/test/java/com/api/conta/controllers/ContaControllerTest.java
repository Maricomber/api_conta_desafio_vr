package com.api.conta.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.api.conta.dto.ContaDTO;
import com.api.conta.enums.TipoMovimentacao;
import com.api.conta.response.Response;
import com.api.conta.services.ContaServices;
import com.api.conta.services.impl.ContaServicesImpl;

@SpringBootTest
public class ContaControllerTest {
	@Mock
	ContaServicesImpl service;
	
	@Mock
	List<ContaDTO> contaDTO;
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}
	
	private ContaController createTestSubject() {
		ContaController controller = new ContaController();
		controller.service = service;
		return controller;
	}

	
	@Test
	public void testFindContas() throws Exception {
		ContaController testSubject;
		HttpServletRequest request = null;
		ResponseEntity<Response<List<ContaDTO>>> result;

		when(service.findAll()).thenReturn(getContas());
		
		// default test 1
		testSubject = createTestSubject();
		result = testSubject.findContas(request);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}
	
	@Test()
	public void testFindContasIsEmptyError() throws Exception {
		ContaController testSubject;
		HttpServletRequest request = null;
		ResponseEntity<Response<List<ContaDTO>>> result;

		when(service.findAll()).thenReturn(new ArrayList<ContaDTO>());
		
		// default test 1
		testSubject = createTestSubject();
		result = testSubject.findContas(request);
		assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
	}

	@Test
	public void testFindById() throws Exception {
		ContaController testSubject;
		Integer id = 0;
		ResponseEntity<Response<ContaDTO>> result;

		// default test
		testSubject = createTestSubject();
		
		when(service.findById(0)).thenReturn(getContas().get(0));
		result = testSubject.findById(id);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}
	
	@Test
	public void testFindByIdError() throws Exception {
		ContaController testSubject;
		Integer id = 0;
		ResponseEntity<Response<ContaDTO>> result;

		// default test
		testSubject = createTestSubject();
		
		when(service.findById(0)).thenReturn(null);
		result = testSubject.findById(id);
		assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
	}

	@Test
	public void testSaveUsuario() throws Exception {
		ContaController testSubject;
		ResponseEntity<Response<List<ContaDTO>>> result;
		
		when(service.save(Mockito.any(List.class))).thenReturn(getContas());
		testSubject = createTestSubject();
		result = testSubject.saveUsuario(getContas());
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}
	
	@Test
	public void testSaveUsuarioError() throws Exception {
		ContaController testSubject;
		ResponseEntity<Response<List<ContaDTO>>> result;
	
		when(service.save(Mockito.any(List.class))).thenReturn(new ArrayList<ContaDTO>());
		
		testSubject = createTestSubject();
		result = testSubject.saveUsuario(getContas());
		assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
	}

	@Test
	public void testUpdate() throws Exception {
		ContaController testSubject;
		ResponseEntity<Response<List<ContaDTO>>> result;
		
		when(service.save(Mockito.any(List.class))).thenReturn(getContas());
		testSubject = createTestSubject();
		result = testSubject.update(getContas());
		assertEquals(HttpStatus.OK, result.getStatusCode());
		
	}

	@Test
	public void testUpdateError() throws Exception {
		ContaController testSubject;
		ResponseEntity<Response<List<ContaDTO>>> result;
	
		when(service.save(Mockito.any(List.class))).thenReturn(new ArrayList<ContaDTO>());
		
		testSubject = createTestSubject();
		result = testSubject.update(getContas());
		assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
	}
	
	@Test
	public void testDelete() throws Exception {
		ContaController testSubject;
		Integer id = 0;
		ResponseEntity<Response<String>> result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.delete(id);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}
	
	@Test
	public void testDeleteError() throws Exception {
		ContaController testSubject;
		Integer id = 0;
		ResponseEntity<Response<String>> result;

		doThrow(new Exception()).when(service);
		testSubject = createTestSubject();
		result = testSubject.delete(id);
		assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
	}
	
	public List<ContaDTO> getContas() {
		List<ContaDTO> contasDTO = new ArrayList<ContaDTO>();
		ContaDTO contaDTO = new ContaDTO();
		contaDTO.setDataOperacao(new Date());
		contaDTO.setIdCartao(1);
		contaDTO.setTipoMovimentacao(TipoMovimentacao.ENTRADA);
		contaDTO.setValor(200);
		contaDTO.setIdConta(1);
		
		contasDTO.add(contaDTO);
		return contasDTO;
	}
}