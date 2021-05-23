package com.ealves.poc.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
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

import com.ealves.poc.model.Categoria;
import com.ealves.poc.service.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping()
	public ResponseEntity<List<Categoria>> findAll() {
		List<Categoria> lista = categoriaService.listar();
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping("/{categoriaId}")
	public Categoria buscar(@PathVariable Long categoriaId) {
		return categoriaService.buscarOuFalhar(categoriaId);
	}
	
	@GetMapping("/consultarPorNome")
	public List<Categoria> consultarPorNome(String nome) {
		return categoriaService.consultarPorNome(nome);
	}
	

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Categoria adicionar(@RequestBody Categoria categoria) {
		return categoriaService.salvar(categoria);
	}
	
	@PutMapping("/{categoriaId}")
	public Categoria atualizar(@PathVariable Long categoriaId, @RequestBody Categoria categoria) {

		Categoria categoriaAtual = categoriaService.buscarOuFalhar(categoriaId);

		BeanUtils.copyProperties(categoria, categoriaAtual, "id");

		return categoriaService.salvar(categoria);
	}
	
	@DeleteMapping("/{categoriaId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT) // Em caso de sucesso retorne o status no Content
	public void remover(@PathVariable Long categoriaId) {
		categoriaService.excluir(categoriaId);

	}

}
