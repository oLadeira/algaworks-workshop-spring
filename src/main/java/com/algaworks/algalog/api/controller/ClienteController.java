package com.algaworks.algalog.api.controller;

import java.util.Arrays;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.algaworks.algalog.domain.model.Cliente;

@RestController //dizendo ao Spring que esta classe é um controlador 
public class ClienteController {

	@GetMapping("/clientes") //mapeamento, quando a requisição "/clientes" for solicitada, esta função será executada
	public List<Cliente> listar() {
		Cliente c1 = new Cliente();
		Cliente c2 = new Cliente();
		
		c1.setId(1L);
		c1.setNome("João");
		c1.setTelefone("11922833");
		c1.setEmail("joaodascolves@algaworks.com");
		
		c2.setId(2L);
		c2.setNome("Maria");
		c2.setTelefone("11933853");
		c2.setEmail("mariasilva@algaworks.com");
		
		return Arrays.asList(c1, c2);		
	}
	
}
