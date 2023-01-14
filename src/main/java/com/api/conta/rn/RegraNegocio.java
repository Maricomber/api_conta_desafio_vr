package com.api.conta.rn;

import com.api.conta.dto.CartaoDTO;

public interface RegraNegocio {
	
	CartaoDTO aplicarRegra(float saldo, CartaoDTO cartaoDTO) throws Exception;
}
