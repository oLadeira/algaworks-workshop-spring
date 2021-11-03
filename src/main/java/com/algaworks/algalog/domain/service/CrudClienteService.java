package com.algaworks.algalog.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.algaworks.algalog.domain.exception.DomainException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;

@Service //componente Spring que representa um servico que será executado
public class CrudClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente buscar(Long clienteId) {
		return clienteRepository.findById(clienteId)
				.orElseThrow(() -> new DomainException("Cliente não encontrado"));
	}
	
	@Transactional //deve ser executado dentro de uma transação
	public Cliente salvar(Cliente cliente) {
		boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail())
				.stream() //api stream
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		
		if (emailEmUso) {
			throw new DomainException("Já existe um cliente cadastrado com este e-mail!");
		}
		
		return clienteRepository.save(cliente);		
	}
	
	@Transactional
	public ResponseEntity<Void> excluir(Long clienteId) {
		if (!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();		
		}
		clienteRepository.deleteById(clienteId);		
		return ResponseEntity.noContent().build();
	}
	
	
	@Transactional
	public List<Cliente> listarAll(){
		return clienteRepository.findAll();
	}
	
	@Transactional
	public List<Cliente> listarByNome(String nome){
		return clienteRepository.findByNomeContaining(nome);
	}
	
	@Transactional
	public ResponseEntity<Cliente> listarById(Long id){
		
		//Optional é um container que nele pode ter ou nao algum valor
		Optional<Cliente> cliente = clienteRepository.findById(id);
		
		//se dentro do Optional Cliente tem algo
		if(cliente.isPresent()) {
			//retorna uma resposta ok (200) e devolve o valor de cliente
			return ResponseEntity.ok(cliente.get());		
		}
		else {
			//caso o optional nao possuir algum valor dentro dele, retorna notFound (404)
			return ResponseEntity.notFound().build();
		}

	}
		
	@Transactional
	public ResponseEntity<Cliente> atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente){
		if(!clienteRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(id);
		clienteRepository.save(cliente);
		return ResponseEntity.ok(cliente);
	}
	
}
