package com.algaworks.algalog.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
	
	@NotBlank //anotacoes do Java Bean Validation, verifica se o campo está vazio 
	@Size(max = 60) //tamanho máximo
	private String nome;
	
	@NotBlank
	@Email //verifica se possui a sintaxe de um email
	@Size(max = 255)
	private String email;
	
	@NotBlank
	@Size(max = 20)
	private String telefone;
	
}
