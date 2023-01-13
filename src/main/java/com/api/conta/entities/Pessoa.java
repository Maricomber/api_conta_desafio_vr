package com.api.conta.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.api.conta.enums.TipoPessoa;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pessoa")
public class Pessoa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pessoa")
	private Integer idPessoa;
	
	@Column(name = "nome_pessoa", nullable = false, length = 150)
	private String nome;
	
	@Column(name = "data_nasc_pessoa", nullable = false)
	private Date dataNascimento;
	
	@Column(name = "doc_pessoa", nullable = false, length = 20)
	private String documento;
	
	@Column(name = "tipo_pessoa", nullable = false, length = 2)
	private TipoPessoa tipoPessoa;
	
	@Column(name = "end_pessoa", nullable = false, length = 150)
	private String endereco;
	
	@OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)
	private List<Telefone> telefone;
	
	@OneToMany(mappedBy = "cartao", cascade = CascadeType.ALL)
	private List<Cartao> cartao;
	
}
