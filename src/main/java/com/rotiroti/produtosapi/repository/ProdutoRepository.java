package com.rotiroti.produtosapi.repository;

import com.rotiroti.produtosapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List; // para um método de consulta personalizado

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    // Criar busca personalizada depois
    // List<Produto> findByNomeContainingIgnoreCase(String nome);
}