package com.api.conta.services;

import java.util.List;

import com.api.conta.dto.ContaDTO;

public interface ContaServices {
	List<ContaDTO>findAll() throws Exception;
	
	ContaDTO findById(Integer id_pessoa) throws Exception;
	
	ContaDTO save(ContaDTO contaDTO) throws Exception;
	
	void delete(Integer id_pessoa) throws Exception;
}
