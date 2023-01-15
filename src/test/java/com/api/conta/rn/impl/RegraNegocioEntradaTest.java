package com.api.conta.rn.impl;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.conta.dto.CartaoDTO;

@SpringBootTest
public class RegraNegocioEntradaTest {

	private RegraNegocioEntrada createTestSubject() {
		return new RegraNegocioEntrada();
	}

	@Test
	public void testAplicarRegra() throws Exception {
		RegraNegocioEntrada testSubject;
		CartaoDTO cartaoDTO = null;
		float valor = 0;
		CartaoDTO result;

		// default test
		testSubject = createTestSubject();
		testSubject.aplicarRegra(valor, getCartao());
	}
	
	private CartaoDTO getCartao() {
		CartaoDTO cartao = new CartaoDTO();
		cartao.setCodSeguranca("123");
		cartao.setDataEmissao(new Date());
		cartao.setIdPessoa(1);
		cartao.setIsAtivo(true);
		cartao.setSaldo(20);
		return cartao;
	}

}