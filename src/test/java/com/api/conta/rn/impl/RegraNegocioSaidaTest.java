package com.api.conta.rn.impl;


import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.conta.dto.CartaoDTO;

@SpringBootTest
public class RegraNegocioSaidaTest {

	private RegraNegocioSaida createTestSubject() {
		return new RegraNegocioSaida();
	}

	@Test
	public void testAplicarRegra() throws Exception {
		RegraNegocioSaida testSubject;
		float saldo = 0;
		CartaoDTO cartaoDTO = getCartao();
		CartaoDTO result;

		// default test
		testSubject = createTestSubject();
		result = testSubject.aplicarRegra(saldo, cartaoDTO);
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