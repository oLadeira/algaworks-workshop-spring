package com.algaworks.algalog.domain.model;

import lombok.Getter;
import lombok.Setter;

@Setter //Lombok
@Getter //Lombok
public class Cliente {
	
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	
}
