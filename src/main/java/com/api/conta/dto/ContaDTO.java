package com.api.conta.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

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
	
	@NotNull
	private int idCartao;
	
	@NotNull
	private int valor;
	
	@NotNull
	private TipoMovimentacao tipoMovimentacao;
	
	@NotNull
	private Date dataOperacao;
}
