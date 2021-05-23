package com.ealves.poc.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ealves.poc.exception.EntidadeNaoEncontradaException;
import com.ealves.poc.model.Categoria;
import com.ealves.poc.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private CategoriaRepository categoriaRepo;

	public List<Categoria> listar() {
		return categoriaRepo.findAll();
	}

	// -Consultar por nome....usando o like...
	public List<Categoria> consultarPorNome(String nome) {
		return manager.createQuery("from Categoria where nome like :nome", Categoria.class)
				.setParameter("nome", "%" + nome + "%").getResultList();
	}

	public Categoria buscarOuFalhar(Long categoriaId) {
		return categoriaRepo.findById(categoriaId).orElseThrow(() -> new EntidadeNaoEncontradaException(categoriaId));

	}

	public Categoria salvar(Categoria categoria) {
		return categoriaRepo.save(categoria);
	}

	public void excluir(Long cozinhaId) {
		try {
			categoriaRepo.deleteById(cozinhaId);

		} catch (EntidadeNaoEncontradaException e) {
			throw new EntidadeNaoEncontradaException(cozinhaId);

		}
	}
}
