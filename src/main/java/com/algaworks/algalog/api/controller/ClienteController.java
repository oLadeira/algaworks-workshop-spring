package com.algaworks.algalog.api.controller;

import java.util.List;
import java.util.Optional;

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
import com.algaworks.algalog.domain.repository.ClienteRepository;

//o controller serve para direcionar o usuário para a função que ele solicitou.

@RestController //dizendo ao Spring que esta classe é um controlador 
//@RequestMapping("/clientes")
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
	
	
	@GetMapping("/clientes/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) { //@PathVariable passa a variável da url para a variavel
		//de parametro da funcao
		
		//Optional é um container que nele pode ter ou nao algum valor
		Optional <Cliente> cliente = clienteRepository.findById(clienteId);
		
		//se dentro do Optional Cliente tem algo
		if (cliente.isPresent()) {
			//retorna uma resposta ok (200) e devolve o valor de cliente
			return ResponseEntity.ok(cliente.get());
		}
		
		//caso o optional nao possuir algum valor dentro dele, retorna notFound (404)
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/clientes") //quando um metodo POST for solicitado nesta url, a funcao abaixo será executada
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) { //atribuir o corpo da requisicao a classe Modelo Cliente
		return clienteRepository.save(cliente);
	}
	
	@PutMapping("/clientes/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente){
		if (!clienteRepository.existsById(clienteId)) { //se o cliente nao existe
			return ResponseEntity.notFound().build();	//retorna 404 not found	
		}
		cliente.setId(clienteId);
		cliente = clienteRepository.save(cliente);
		
		return ResponseEntity.ok(cliente);
	}
		
	@DeleteMapping("/clientes/{clienteId}")
	//retorna Void pq quando deleto o cliente nao quero visualizar o corpo dele
	public ResponseEntity<Void> remover(@PathVariable Long clienteId){
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();		
		}
		clienteRepository.deleteById(clienteId);
		
		return ResponseEntity.noContent().build();
	}
}
