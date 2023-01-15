package com.api.conta.services.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api.conta.dto.CartaoDTO;
import com.api.conta.dto.ContaDTO;
import com.api.conta.entities.Conta;
import com.api.conta.enums.TipoMovimentacao;
import com.api.conta.repositories.ContaRepository;
import com.api.conta.response.Response;
import com.api.conta.rn.RegraNegocio;
import com.api.conta.rn.impl.RegraNegocioEntrada;
import com.api.conta.rn.impl.RegraNegocioSaida;
import com.api.conta.services.ContaServices;

@Service
public class ContaServicesImpl implements ContaServices{

	@Autowired
	ContaRepository repository;
	
	RegraNegocio regraNegocio;
	
	private String msgErro;
	
	private ModelMapper mapper = new ModelMapper();
	
    private static final String api_cadastro = "http://localhost:8081/api/cartao/";

	private static final Logger log = LoggerFactory.getLogger(ContaServicesImpl.class);
	
	@Override
	public List<ContaDTO> findAll() throws SQLException {
		log.info("Buscando todas os registros de contas.");
		List<Conta> contas = new ArrayList<>();
		List<ContaDTO> contasRetorno = new ArrayList<>();
		
		try {
			contas = this.repository.findAll();
			contas.stream().forEach(conta->contasRetorno.add(mapper.map(conta, ContaDTO.class)));
			log.info("Busca realizada com sucesso");
			return contasRetorno;
		}catch (Exception e) {
			msgErro = "Erro ao buscar contas. "+e.getMessage();
			log.info(msgErro);
			throw new SQLException(msgErro);
		}
	}

	@Override
	public ContaDTO findById(Integer idConta) throws SQLException {
		log.info("Buscando conta.");
		Conta conta = new Conta();
		try {
			conta = this.repository.findByIdConta(idConta);
			if(conta == null) {
				throw new NoResultException("Sem resultados.");
			}
			log.info("Conta encontrado.");
			return mapper.map(conta, ContaDTO.class);
		}catch (Exception e) {
			msgErro = "Erro ao buscar conta. "+e.getMessage();
			log.info(msgErro);
			throw new SQLException(msgErro);
		}
	}

	@Override
	public List<ContaDTO> save(List<ContaDTO> contasDTO) throws SQLException {
		if(contasDTO.isEmpty()){
			throw new NoResultException("Pesquisa em branco. ");
		}
		log.info("Salvando conta");
		final List<Conta> contas = new ArrayList<>();
		List<ContaDTO>contaRetorno = new ArrayList<>();
		try {
			aplicarRegra(contasDTO);
			contasDTO.forEach(conta->contas.add(mapper.map(conta, Conta.class)));
			this.repository.saveAll(contas).forEach(conta->contaRetorno.add(mapper.map(conta, ContaDTO.class)));
			return contaRetorno;
		}catch (Exception e) {
			msgErro = "Erro ao salvar conta. "+e.getMessage();
			log.info(msgErro);
			throw new SQLException(msgErro);
		}
	}

	@Override
	public void delete(Integer idConta) throws SQLException {
		Conta conta = new Conta();
		log.info("Deletando conta...");
		
		try{
			conta = this.repository.findByIdConta(idConta);
			this.repository.delete(conta);
		}catch (Exception e) {
			msgErro = "Erro conta não pode ser deletado. "+e.getMessage();
			log.info(msgErro);
			throw new SQLException(msgErro);
		}
	}
	  
	  
	private void aplicarRegra(List<ContaDTO> contas){
		List<CartaoDTO>cartaoRetorno = new ArrayList<>();
		
		
		contas.stream().forEach(conta-> {
			try {
				definirInterface(conta);
				cartaoRetorno.add(regraNegocio.aplicarRegra(conta.getValor(), buscaCartao(conta.getIdCartao())));
			} catch (Exception e) {
				msgErro = "Erro não foi possível recuperar dados do cartão. "+e.getMessage();
				log.info(msgErro+e.getMessage());
			}
		});
		
		atualizaCartao(cartaoRetorno);
		
	}
	
	private void definirInterface(ContaDTO conta) {
		if(conta.getTipoMovimentacao().equals(TipoMovimentacao.ENTRADA)) {
			regraNegocio = new RegraNegocioEntrada();
		}
		else {
			regraNegocio = new RegraNegocioSaida();
		}
	}
	private CartaoDTO buscaCartao(Integer idCartao) {
		RestTemplate restTemplate = new RestTemplate();
		 
		Response<CartaoDTO> response = restTemplate.getForObject(api_cadastro+idCartao, Response.class);
		if(response.getData() !=null) {
			return mapper.map(response.getData(), CartaoDTO.class);
		}
		return null;
	}
	
	private void atualizaCartao(List<CartaoDTO> cartoes) {
		RestTemplate restTemplate = new RestTemplate(); 
		cartoes.stream().forEach(cartao -> restTemplate.postForEntity(api_cadastro, cartao, CartaoDTO.class));
	}
	
	
}
