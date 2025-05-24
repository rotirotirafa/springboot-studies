package com.rotiroti.produtosapi.controller;

import com.rotiroti.produtosapi.model.Produto;
import com.rotiroti.produtosapi.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    @Autowired
    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public Produto salvar(@RequestBody Produto produto){
        return produtoService.criarProduto(produto);
    }

    @GetMapping("/{produtoId}")
    public ResponseEntity<Optional<Produto>> buscarProduto(@PathVariable Integer produtoId){
        return ResponseEntity.ok(produtoService.buscarProdutoPorId(produtoId));
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        return ResponseEntity.ok(produtoService.listarTodosProdutos());
    }

    @PutMapping("/{produtoId}")
    public ResponseEntity<Optional<Produto>> atualizar(@PathVariable  Integer produtoId, @RequestBody Optional<Produto> produto){
        produto = produtoService.atualizarProduto(produtoId, produto.orElse(null));
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{produtoId}")
    public ResponseEntity<Void> deletar(@PathVariable  Integer produtoId){
        produtoService.deletarProduto(produtoId);
        return ResponseEntity.noContent().build();
    }
}
