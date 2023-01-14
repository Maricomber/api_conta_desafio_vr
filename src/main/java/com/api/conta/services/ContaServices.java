package com.api.conta.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.conta.dto.ContaDTO;

@Service
public interface ContaServices {
	List<ContaDTO>findAll() throws Exception;
	
	ContaDTO findById(Integer id_pessoa) throws Exception;
	
	List<ContaDTO> save(List<ContaDTO>  contaDTO) throws Exception;
	
	void delete(Integer id_pessoa) throws Exception;
}
