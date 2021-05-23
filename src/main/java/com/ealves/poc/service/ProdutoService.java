package com.ealves.poc.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ealves.poc.exception.EntidadeNaoEncontradaException;
import com.ealves.poc.model.Produto;
import com.ealves.poc.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private ProdutoRepository produtoRepo;

	public List<Produto> listar() {
		return produtoRepo.findAll();
	}

	// -Consultar por nome....usando o like...
	public List<Produto> consultarPorNome(String nome) {
		return manager.createQuery("from Produto where nome like :nome", Produto.class)
				.setParameter("nome", "%" + nome + "%").getResultList();
	}

	public Produto buscarOuFalhar(Long produtoId) {
		return produtoRepo.findById(produtoId).orElseThrow(() -> new EntidadeNaoEncontradaException(produtoId));

	}

	public Produto salvar(Produto produto) {
		return produtoRepo.save(produto);
	}

	public void excluir(Long produtoId) {
		try {
			produtoRepo.deleteById(produtoId);

		} catch (EntidadeNaoEncontradaException e) {
			throw new EntidadeNaoEncontradaException(produtoId);

		}
	}
}
