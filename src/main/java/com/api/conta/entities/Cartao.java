package com.api.conta.entities;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cartao")
public class Cartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cartao")
	private Integer id;
	
	@Column(name = "numero", nullable = false, length = 150)
	private String numero;
	
	@Column(name = "data_vencimento", nullable = false)
	private Date dataVencimento;
	
	@Column(name = "data_vencimento", nullable = false)
	private Date dataEmissao;
	
	@Column(name = "cod_seguranca", nullable = false, length = 20)
	private String codSeguranca;
	
	@Column(name = "saldo", nullable = false)
	private float saldo;
	
	@ManyToOne(cascade = CascadeType.ALL)  
    @JoinColumn(name="id_pessoa", nullable = false)
	private Pessoa pessoa;
}
