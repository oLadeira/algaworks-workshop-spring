package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.service.CrudClienteService;

//o controller serve para direcionar o usuário para a função que ele solicitou.

@RestController //dizendo ao Spring que esta classe é um controlador 
@RequestMapping("/clientes") //todos GetMapping será "/clientes"
public class ClienteController {

	
	@Autowired//injecao de dependencia
	private CrudClienteService crudClienteService; //classe onde fica todas as operacoes de crud do cliente (service)
		
	
	//GET//
	@GetMapping //mapeamento, quando a requisição "/clientes" for solicitada, esta função será executada
	public List<Cliente> listar() {
		return crudClienteService.listarAll();
	}
	
		
	@GetMapping("/nome/{clienteNome}")
	public List <Cliente> listarByNomeLike(@PathVariable String clienteNome){
		return crudClienteService.listarByNome(clienteNome);
	}
	
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) { //@PathVariable passa a variável da url para a variavel
		return crudClienteService.listarById(clienteId);
	}
	//FIM GET
	
	
	
	//POST//
	@PostMapping //quando um metodo POST for solicitado nesta url, a funcao abaixo será executada
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) { //atribuir o corpo da requisicao a classe Modelo Cliente
		return crudClienteService.salvar(cliente);
	}
	//FIM POST
	
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente){				
		crudClienteService.atualizarCliente(clienteId, cliente);		
		return ResponseEntity.ok(cliente);
	}
		
	
	@DeleteMapping("/{clienteId}")
	//retorna Void pq quando deleto o cliente nao quero visualizar o corpo dele
	public ResponseEntity<Void> remover(@PathVariable Long clienteId){		
		crudClienteService.excluir(clienteId);
		return ResponseEntity.noContent().build();		
	}
}
