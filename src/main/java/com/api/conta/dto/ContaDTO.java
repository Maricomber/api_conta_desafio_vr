package com.api.conta.dto;

import java.util.Date;

import com.api.conta.enums.TipoMovimentacao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContaDTO {

	private int idConta;
	private int idCartao;
	private int valor;
	private TipoMovimentacao tipoMovimentacao;
	private Date dataOperacao;
}
