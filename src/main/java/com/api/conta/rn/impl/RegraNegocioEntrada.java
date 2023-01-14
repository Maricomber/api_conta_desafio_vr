package com.api.conta.rn.impl;

import com.api.conta.dto.CartaoDTO;
import com.api.conta.rn.RegraNegocio;

public class RegraNegocioEntrada implements  RegraNegocio{

	@Override
	public CartaoDTO aplicarRegra(float saldo, CartaoDTO cartaoDTO) throws Exception {
		validarCartao(cartaoDTO);
		return adicionarSaldoCartao(cartaoDTO, saldo);
	}
	
	private void validarCartao(CartaoDTO cartaoDTO) throws Exception {
		if(!cartaoDTO.getIsAtivo()) {
			throw new Exception("Cartão não se encontra ativo.");
		}
	}
	
	private CartaoDTO adicionarSaldoCartao(CartaoDTO cartaoDTO, float valor) {
		cartaoDTO.setSaldo(cartaoDTO.getSaldo()+valor);
		return cartaoDTO;
	}
	
}
