package com.api.conta.services.impl;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.api.conta.dto.ContaDTO;
import com.api.conta.entities.Cartao;
import com.api.conta.entities.Conta;
import com.api.conta.enums.TipoMovimentacao;
import com.api.conta.repositories.ContaRepository;

@SpringBootTest
class ContaServicesImplTest {

	@Mock
	ContaRepository repository;
	
	@Mock
	ContaDTO contaDTO;
	
	private ModelMapper mapper = new ModelMapper();
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	private ContaServicesImpl createTestSubject() {
		ContaServicesImpl service = new ContaServicesImpl();
		service.repository = repository;
		return service;
	}

	@Test
	void testFindAll() throws Exception {
		ContaServicesImpl testSubject;
		List<ContaDTO> result;

		when(repository.findAll()).thenReturn(getContas());
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
		
		when(repository.findByIdConta(0)).thenReturn(getConta());
		// default test
		
		result = testSubject.findById(0);
		assertEquals(result.getIdConta(), getConta().getIdConta());
	}

	@Test
	void testSave() throws Exception {
		ContaServicesImpl testSubject;
		List<ContaDTO> result;

		// default test
		when(repository.saveAll(Mockito.any(List.class))).thenReturn(getContas());
		testSubject = createTestSubject();
		result = testSubject.save(getContasDTO());
		assertEquals(result.get(0).getIdCartao(), getConta().getCartao().getIdCartao());
	}
	
	@Test
	void testDelete() throws Exception {
		ContaServicesImpl testSubject;
		Integer idConta = 0;

		// default test
		testSubject = createTestSubject();
		testSubject.delete(idConta);
	}
	
	private Cartao getCartao() {
		Cartao cartao = new Cartao();
		cartao.setIdCartao(1);
		cartao.setCodSeguranca("123");
		cartao.setDataEmissao(new Date(0));
		cartao.setIsAtivo(true);
		cartao.setDataVencimento(new Date(0));
		cartao.setNumero("123456789");
		cartao.setSaldo(200);
		return cartao;
	}
	private Conta getConta() {
		Conta conta = new Conta();
		conta.setCartao(getCartao());
		conta.setDataOperacao(new Date(0));
		conta.setIdConta(1);
		conta.setTipoMovimentacao(TipoMovimentacao.ENTRADA);
		conta.setValor(200);
		return conta;
	}
	
	private List<Conta> getContas(){
		List<Conta>contas = new ArrayList<>();
		contas.add(getConta());
		return contas;
	}
	private List<ContaDTO> getContasDTO() {
		List<ContaDTO>contas = new ArrayList<>();
		contas.add(mapper.map(getConta(), ContaDTO.class));
		return contas;
	}
}