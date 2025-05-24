// Exemplo num futuro ProdutoService
package com.rotiroti.produtosapi.service;

import com.rotiroti.produtosapi.model.Produto;
import com.rotiroti.produtosapi.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto criarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Optional<Produto> buscarProdutoPorId(Integer id) {
        return produtoRepository.findById(id);
    }

    public List<Produto> listarTodosProdutos() {
        return produtoRepository.findAll();
    }

    public void deletarProduto(Integer id) {
        produtoRepository.deleteById(id);
    }

    @Transactional //Garante atomicidade da operação
    public Optional<Produto> atualizarProduto(Integer id, Produto produtoComNovosDados) {
        Optional<Produto> produtoExistente = produtoRepository.findById(id);
        if (produtoExistente.isPresent()) {
            Produto produtoParaAtualizar = produtoExistente.get();
            produtoParaAtualizar.setNome(produtoComNovosDados.getNome());
            produtoParaAtualizar.setDescricao(produtoComNovosDados.getDescricao());
            produtoParaAtualizar.setPreco(produtoComNovosDados.getPreco());

            if (produtoParaAtualizar.getPreco() != null && produtoParaAtualizar.getPreco().compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("O preço do produto não pode ser negativo.");
            }

            Produto produtoSalvo = produtoRepository.save(produtoParaAtualizar);
            return Optional.of(produtoSalvo);
        } else {
            return Optional.empty();
        }
    }
}