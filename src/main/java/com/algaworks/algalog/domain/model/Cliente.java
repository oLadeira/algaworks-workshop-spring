package com.algaworks.algalog.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

//aqui fica a nossa classe modelo, nela fica todos seus atributos, funcoes getters e setters, hashcode e equals etc.
//aqui no caso estou utilizando a dependência Lombok para fazer os metodos getters, setts, hashcode, usando apenas
//anotações, deixando o código mais limpo e enxuto.

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter //Lombok
@Getter //Lombok

@Entity //estou dizendo ao spring que essa classe é uma entidade
public class Cliente {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	
}
