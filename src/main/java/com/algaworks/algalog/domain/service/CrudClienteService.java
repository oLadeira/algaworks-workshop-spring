package com.algaworks.algalog.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.domain.exception.DomainException;
import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;

@Service //componente Spring que representa um servico que será executado
public class CrudClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
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
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);		
	}
	
}
