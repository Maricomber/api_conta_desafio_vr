package com.api.conta.rn.impl;

import javax.validation.ValidationException;

import com.api.conta.dto.CartaoDTO;
import com.api.conta.rn.RegraNegocio;

public class RegraNegocioEntrada implements  RegraNegocio{

	@Override
	public CartaoDTO aplicarRegra(float saldo, CartaoDTO cartaoDTO) throws ValidationException {
		validarCartao(cartaoDTO);
		return adicionarSaldoCartao(cartaoDTO, saldo);
	}
	
	private void validarCartao(CartaoDTO cartaoDTO) throws ValidationException {
		if(Boolean.FALSE.equals(cartaoDTO.getIsAtivo())) {
			throw new ValidationException("Cartão não se encontra ativo.");
		}
	}
	
	private CartaoDTO adicionarSaldoCartao(CartaoDTO cartaoDTO, float valor) {
		cartaoDTO.setSaldo(cartaoDTO.getSaldo()+valor);
		return cartaoDTO;
	}
	
}
