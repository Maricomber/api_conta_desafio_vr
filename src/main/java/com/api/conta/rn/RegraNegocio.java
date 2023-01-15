package com.api.conta.rn;

import javax.validation.ValidationException;

import com.api.conta.dto.CartaoDTO;

public interface RegraNegocio {
	
	CartaoDTO aplicarRegra(float saldo, CartaoDTO cartaoDTO) throws ValidationException;
}
