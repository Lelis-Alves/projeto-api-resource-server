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

import com.ealves.poc.model.Produto;
import com.ealves.poc.service.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping()
	public ResponseEntity<List<Produto>> findAll() {
		List<Produto> lista = produtoService.listar();
		return ResponseEntity.ok().body(lista);
	}

	@GetMapping("/{produtoId}")
	public Produto buscar(@PathVariable Long produtoId) {
		return produtoService.buscarOuFalhar(produtoId);
	}

	@GetMapping("/consultarPorNome")
	public List<Produto> consultarPorNome(String nome) {
		return produtoService.consultarPorNome(nome);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Produto adicionar(@RequestBody Produto categoria) {
		return produtoService.salvar(categoria);
	}

	@PutMapping("/{produtoId}")
	public Produto atualizar(@PathVariable Long produtoId, @RequestBody Produto produto) {

		Produto produtoAtual = produtoService.buscarOuFalhar(produtoId);

		BeanUtils.copyProperties(produto, produtoAtual, "id");

		return produtoService.salvar(produto);
	}

	@DeleteMapping("/{produtoId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT) // Em caso de sucesso retorne o status no Content
	public void remover(@PathVariable Long produtoId) {
		produtoService.excluir(produtoId);

	}

}
