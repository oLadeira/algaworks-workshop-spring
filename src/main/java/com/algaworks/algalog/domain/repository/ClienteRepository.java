package com.algaworks.algalog.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algalog.domain.model.Cliente;

//nos repositories são feitos as operações de acesso a dados, elas herdão o JpaRepository, nele é onde está a 
//implementacao das operacoes de acesso, manipulacao de dados.

@Repository //estou dizendo ao spring que a interface abaixo é um repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{ //
		
	//meu proprio método, o SpringDataJpa ele vai fornecer uma implementacao de uma pesquisa por nome 
	//em tempo de execucao, não preciso escrever mais nada, apenas vou definir o metódo e ele vai me fornecer
	//uma implementacao desta consulta
	List<Cliente> findByNome(String nome);
	
	//Containing = 'like' no SQL, pesquisa nomes parecidos
	List<Cliente> findByNomeContaining(String nome);
	
	
}
