package com.ealves.poc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ealves.poc.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
