package com.api.conta.rn.impl;

import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.api.conta.dto.CartaoDTO;
import com.api.conta.enums.TipoCartao;

@SpringBootTest
class RegraNegocioEntradaTest {

	private RegraNegocioEntrada createTestSubject() {
		return new RegraNegocioEntrada();
	}

	@Test
	void testAplicarRegra() throws Exception {
		RegraNegocioEntrada testSubject;
		float valor = 0;
		CartaoDTO result;

		// default test
		testSubject = createTestSubject();
		testSubject.aplicarRegra(valor, getCartao());
		assertNotNull(testSubject);
	}
	
	private CartaoDTO getCartao() {
		CartaoDTO cartao = new CartaoDTO();
		cartao.setCodSeguranca("123");
		cartao.setDataEmissao(new Date());
		cartao.setIdPessoa(1);
		cartao.setIsAtivo(true);
		cartao.setSaldo(20);
		cartao.setTipoCartao(TipoCartao.REFEICAO);
		cartao.setNumero("1234");
		cartao.setDataVencimento(new Date());
		return cartao;
	}

}