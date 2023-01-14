package com.api.conta.rn.impl;

import com.api.conta.dto.CartaoDTO;
import com.api.conta.rn.RegraNegocio;

public class RegraNegocioSaida implements RegraNegocio {

	@Override
	public CartaoDTO aplicarRegra(float saldo, CartaoDTO cartaoDTO) throws Exception {
		validarCartao(cartaoDTO);
		return removerSaldoCartao(cartaoDTO, saldo);
		
	}

	private void validarCartao(CartaoDTO cartaoDTO) throws Exception {
		if(!cartaoDTO.getIsAtivo()) {
			throw new Exception("Cartão não se encontra ativo.");
		}
	}
	
	private CartaoDTO removerSaldoCartao(CartaoDTO cartaoDTO, float valor) throws Exception {
		if(valor > cartaoDTO.getSaldo()) {
			throw new Exception("Não possui saldo suficiente");
		}
		cartaoDTO.setSaldo(cartaoDTO.getSaldo()-valor);
		return cartaoDTO;
	}

}
