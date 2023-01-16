package com.api.conta.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api.conta.dto.CartaoDTO;
import com.api.conta.dto.ContaDTO;
import com.api.conta.enums.TipoMovimentacao;
import com.api.conta.producer.ContaProducer;
import com.api.conta.response.Response;
import com.api.conta.rn.RegraNegocio;
import com.api.conta.rn.impl.RegraNegocioEntrada;
import com.api.conta.rn.impl.RegraNegocioSaida;
import com.api.conta.services.ContaServices;

import net.minidev.json.JSONArray;

@Service
public class ContaServicesImpl implements ContaServices{
	
	RegraNegocio regraNegocio;
	
	private String msgErro;
	
	private ModelMapper mapper = new ModelMapper();
	
    private static final String api_cadastro_cartao = "http://localhost:8084/api/cartao/";

    private static final String api_cadastro_conta = "http://localhost:8084/api/conta/";
    
	private static final Logger log = LoggerFactory.getLogger(ContaServicesImpl.class);
	
	RestTemplate restTemplate = new RestTemplate(); 
	
	@Value("${topic.name.producer}")
    private String topicName;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Override
	public List<ContaDTO> findAll() throws Exception {
		log.info("Buscando todas os registros de contas.");
		List<ContaDTO> contasRetorno = new ArrayList<>();
		
		try {
			Response<ContaDTO> response = restTemplate.getForObject(api_cadastro_conta, Response.class);
			log.info("Busca realizada com sucesso");
			return mapper.map(response.getData(), List.class);
		}catch (Exception e) {
			msgErro = "Erro ao buscar contas. "+e.getMessage();
			log.info(msgErro);
			throw new Exception(msgErro);
		}
	}

	@Override
	public ContaDTO findById(Integer idConta) throws Exception {
		log.info("Buscando conta.");
		try {
			Response<ContaDTO> response = restTemplate.getForObject(api_cadastro_conta+idConta, Response.class);
			if(response.getData() !=null) {
				log.info("Conta encontrada.");
				return mapper.map(response.getData(), ContaDTO.class);
			}
			throw new Exception("Erro ao procurar conta");
		}catch (Exception e) {
			msgErro = "Erro ao buscar conta. "+e.getMessage();
			log.info(msgErro);
			throw new Exception(msgErro);
		}
	}

	@Override
	public List<ContaDTO> save(List<ContaDTO> contasDTO) throws Exception {
		if(contasDTO.isEmpty()){
			log.info("Pesquisa em branco. ");
		}
		log.info("Salvando conta");
		List<ContaDTO>contaRetorno = new ArrayList<>();
		RestTemplate restTemplate = new RestTemplate(); 
		try {
			aplicarRegra(contasDTO);
			contasDTO.stream().forEach(cartao -> restTemplate.postForEntity(api_cadastro_conta, cartao, CartaoDTO.class));
			return contaRetorno;
		}catch (Exception e) {
			msgErro = "Erro ao salvar conta. "+e.getMessage();
			log.info(msgErro);
			throw new Exception(msgErro);
		}
	}

	@Override
	public void delete(Integer idConta) throws Exception {
		log.info("Deletando conta...");
		
		try{
			 restTemplate.delete(api_cadastro_conta+idConta, Response.class);
		}catch (Exception e) {
			msgErro = "Erro conta não pode ser deletado. "+e.getMessage();
			log.info(msgErro);
			throw new Exception(msgErro);
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
		 
		Response<CartaoDTO> response = restTemplate.getForObject(api_cadastro_cartao+idCartao, Response.class);
		if(response.getData() !=null) {
			return mapper.map(response.getData(), CartaoDTO.class);
		}
		return null;
	}
	
	private void atualizaCartao(List<CartaoDTO> cartoes) {
		cartoes.stream().forEach(cartao -> restTemplate.postForEntity(api_cadastro_cartao, cartao, CartaoDTO.class));
		//kafkaTemplate.send(topicName, JSONArray.toJSONString(cartoes));
		
	}
	
	
	
}
