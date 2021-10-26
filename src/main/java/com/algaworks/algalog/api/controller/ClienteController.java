package com.algaworks.algalog.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;

//o controller serve para direcionar o usuário para a função que ele solicitou.

@RestController //dizendo ao Spring que esta classe é um controlador 
public class ClienteController {
	
	@Autowired //injecao de dependencia
	private ClienteRepository clienteRepository;
		
	@GetMapping("/clientes") //mapeamento, quando a requisição "/clientes" for solicitada, esta função será executada
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/clientes/nome")
	public List <Cliente> listarByNome() {
		return clienteRepository.findByNome("João da Silva");
	}
		
	@GetMapping("/clientes/nome/like")
	public List <Cliente> listarByNomeLike(){
		return clienteRepository.findByNomeContaining("a");
	}
	
}
