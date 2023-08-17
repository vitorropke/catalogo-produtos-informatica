package com.informatica.catalogo.controller;

import com.informatica.catalogo.dto.ProdutoDTO;
import com.informatica.catalogo.model.ProdutoModel;
import com.informatica.catalogo.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping("/produtos")
    public ResponseEntity<ProdutoModel> cadastrar(@RequestBody @Valid ProdutoDTO produtoDTO) {
        var produtoModel = new ProdutoModel();
        BeanUtils.copyProperties(produtoDTO, produtoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produtoModel));
    }
    // editar
    // excluir

    @GetMapping("/produtos")
    public ResponseEntity<List<ProdutoModel>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findAll());
    }

    // buscarPor
    @GetMapping("/produtos/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable(value = "id") UUID id) {
        Optional<ProdutoModel> produto = produtoRepository.findById(id);

        if (produto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto nao encontrado.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.findAll());
    }

}
