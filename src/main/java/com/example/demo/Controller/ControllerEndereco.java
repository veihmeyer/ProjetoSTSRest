package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.Controller.model.Endereco;
import com.example.demo.Controller.repository.EnderecoRepository;

@RestController
@RequestMapping("/Endereco")
public class ControllerEndereco {

	//@GetMapping
	//public String teste() {
		//return "Fui chamado pelo metódo GET (Endereco)";
	//}
	@Autowired
	private EnderecoRepository repository;
	
	@PostMapping
	public Endereco salvar(@RequestBody Endereco endereco) {
		return this.repository.save(endereco);
	}
	
	@PutMapping("/{id}")
	public Endereco editar(@PathVariable("id") Long id, @RequestBody Endereco endereco) {
		Endereco clienteDoBancoDeDados = this.repository.findById(id).get();
		BeanUtils.copyProperties(endereco, clienteDoBancoDeDados, "id");
		this.repository.save(clienteDoBancoDeDados);
		return clienteDoBancoDeDados;

	}
	@GetMapping
	public List<Endereco> listar() {
		return this.repository.findAll();
	}
	
	@DeleteMapping("/{id}")
	public String deletar(@PathVariable("id") Long id) {
		this.repository.deleteById(id);
		return ("Usuário (id) " + (id) + ": apagado com sucesso");
	}
	
}
