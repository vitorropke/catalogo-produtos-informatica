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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<ProdutoModel> cadastrar(@RequestBody @Valid ProdutoDTO produtoDTO) {
        var produtoModel = new ProdutoModel();

        BeanUtils.copyProperties(produtoDTO, produtoModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produtoModel));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity<Object> editar(@PathVariable(value = "id") UUID id, @RequestBody @Valid ProdutoDTO produtoDTO) {
        Optional<ProdutoModel> produto = produtoRepository.findById(id);

        if (produto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto nao encontrado.");
        }

        var produtoModel = produto.get();

        BeanUtils.copyProperties(produtoDTO, produtoModel);

        return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produtoModel));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluir(@PathVariable(value = "id") UUID id) {
        Optional<ProdutoModel> produto = produtoRepository.findById(id);

        if (produto.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto nao encontrado.");
        }

        produtoRepository.delete(produto.get());

        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado com sucesso.");
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<ProdutoModel>> listar() {
        List<ProdutoModel> produtos = produtoRepository.findAll();

        if (!produtos.isEmpty()) {
            for (ProdutoModel produto : produtos) {
                produto.add(linkTo(methodOn(ProdutoController.class).buscarPorId(produto.getId())).withSelfRel());
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(produtos);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable(value = "id") UUID id) {
        Optional<ProdutoModel> produto = produtoRepository.findById(id);

        return produto.<ResponseEntity<Object>>map(produtoModel -> ResponseEntity.status(HttpStatus.OK).body(produtoModel)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto nao encontrado."));
    }

}
