package com.algaworks.algalog.api.controller;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.algaworks.algalog.domain.model.Cliente;

@RestController //dizendo ao Spring que esta classe é um controlador 
public class ClienteController {

	@PersistenceContext
	private EntityManager manager;
	
	
	@GetMapping("/clientes") //mapeamento, quando a requisição "/clientes" for solicitada, esta função será executada
	public List<Cliente> listar() {
		return manager.createQuery("from Cliente", Cliente.class).getResultList();
	}
	
}
