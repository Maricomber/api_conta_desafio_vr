package com.api.conta.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.api.conta.dto.ContaDTO;
import com.api.conta.response.Response;
import com.api.conta.services.ContaServices;

import io.swagger.annotations.*;

public class ContaController {
	
	@Autowired
	ContaServices service;
	
	@ApiOperation(value = "Retorna uma lista de contas")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Lista de contas retornada com sucesso"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Response<List<ContaDTO>>> findContas(HttpServletRequest request) {
		
		Response<List<ContaDTO>> response = new Response<List<ContaDTO>>();
		List<String>erros = new ArrayList<String>();
		
		try{
			List<ContaDTO>contaDTO = this.service.findAll();
			
			if(contaDTO.isEmpty()) {
				throw new Exception("Registro de contas não encontrado");
			}
			response.setData(contaDTO);
			return ResponseEntity.ok(response);
		}catch (Exception e) {
			erros.add(e.getMessage());
			response.setErrors(erros);
			return ResponseEntity.badRequest().body(response);
		}
		
	}
	
	@ApiOperation(value = "Retorna uma conta por id")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Conta pesquisada com sucesso"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@GetMapping(path = {"/{id}"},produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Response<ContaDTO>> findById(@PathVariable Integer id){
		
		List<String>erros = new ArrayList<String>();
		Response<ContaDTO>response = new Response<ContaDTO>();
		ContaDTO contaDTO;
		
		try {
			
			if(id == null) {
				throw new Exception("Campos em branco");
			}
			
			contaDTO= this.service.findById(id);
			
			if(contaDTO.equals(null)) {
				throw new Exception("Usuario não encontrado. ");
			}
			response.setData(contaDTO);
			return ResponseEntity.ok(response);
		}catch (Exception e) {
			erros.add(e.getMessage());
			response.setErrors(erros);
			return ResponseEntity.badRequest().body(response);
		}
		
	}
	
	@ApiOperation(value = "Cria um registro de conta")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Registro de conta criado com sucesso"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@PostMapping
	public @ResponseBody ResponseEntity<Response<ContaDTO>> saveUsuario(@RequestBody ContaDTO contaDTO) {
		
		Response<ContaDTO> response = new Response<ContaDTO>();
		List<String>erros = new ArrayList<String>();
		
		try {

			if(contaDTO == null) {
				throw new Exception("Campos vazios. ");
			}
			contaDTO = this.service.save(contaDTO);
			response.setData(contaDTO);
			return ResponseEntity.ok(response);
			
		}catch (Exception e) {
			erros.add(e.getMessage());
			response.setErrors(erros);
			return ResponseEntity.badRequest().body(response);
		}
		
	}
	
	@ApiOperation(value = "Atualiza um registro de conta")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Conta atualizado com sucesso"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes =  MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Response<ContaDTO>> update(@RequestBody ContaDTO contaDTO){
		
		List<String>erros = new ArrayList<String>();
		Response<ContaDTO>response = new Response<ContaDTO>();
		
		try {
			contaDTO = this.service.save(contaDTO);
			if(contaDTO.equals(null)) {
				return ResponseEntity.badRequest().body(response);
			}
		response.setData(contaDTO);
		return ResponseEntity.ok(response);
		}catch (Exception e) {
			erros.add(e.getMessage());
			response.setErrors(erros);
			return ResponseEntity.badRequest().body(response);
		}
	}

	@ApiOperation(value = "Deleta um registro de conta")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Conta deletada com sucesso"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@DeleteMapping("/{id}")
	public @ResponseBody ResponseEntity<Response<ContaDTO>> deleteUsuario(@PathVariable Integer id) {
		
		Response<ContaDTO> response = new Response<ContaDTO>();
		List<String>erros = new ArrayList<String>();
		
		try {
			if(id == null) {
				throw new Exception("Campos em branco. ");
			}
			this.service.delete(id);
		}catch (Exception e) {
			erros.add(e.getMessage());
			response.setErrors(erros);
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.ok(response);
	}
}
