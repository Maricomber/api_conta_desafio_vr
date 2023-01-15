package com.api.conta.rn.impl;

import javax.validation.ValidationException;

import com.api.conta.dto.CartaoDTO;
import com.api.conta.rn.RegraNegocio;

public class RegraNegocioSaida implements RegraNegocio {

	@Override
	public CartaoDTO aplicarRegra(float saldo, CartaoDTO cartaoDTO) throws ValidationException {
		validarCartao(cartaoDTO);
		return removerSaldoCartao(cartaoDTO, saldo);
		
	}

	private void validarCartao(CartaoDTO cartaoDTO) throws ValidationException {
		if(Boolean.FALSE.equals(cartaoDTO.getIsAtivo())) {
			throw new ValidationException("Cartão não se encontra ativo.");
		}
	}
	
	private CartaoDTO removerSaldoCartao(CartaoDTO cartaoDTO, float valor) throws ValidationException {
		if(valor > cartaoDTO.getSaldo()) {
			throw new ValidationException("Não possui saldo suficiente");
		}
		cartaoDTO.setSaldo(cartaoDTO.getSaldo()-valor);
		return cartaoDTO;
	}

}
