package com.informatica.catalogo;

import com.informatica.catalogo.controller.ProdutoController;
import com.informatica.catalogo.dto.ProdutoDTO;
import com.informatica.catalogo.model.ProdutoModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

@SpringBootTest
class CatalogoApplicationTests {

    @Test
    void contextLoads() {
        String fabricante = "Intel";
        String modelo = "Core i3-7100";
        String lote = "ABC123";
        int quantidade = 2;
        float preco = 293.54f;
        float desconto = 0.0f;
        String urlImagem = "sem fonte";

        ProdutoController produtoController = new ProdutoController();
        ProdutoDTO produtoDTO = new ProdutoDTO(fabricante, modelo, lote, quantidade, preco, desconto, urlImagem);
        ResponseEntity<ProdutoModel> produto = produtoController.cadastrar(produtoDTO);
        ResponseEntity<Object> resultadoEdicao = produtoController.editar(Objects.requireNonNull(produto.getBody()).getId(), produtoDTO);
        ResponseEntity<Object> resultadoExclusao = produtoController.excluir(produto.getBody().getId());
        ResponseEntity<List<ProdutoModel>> produtos = produtoController.listar();
        ResponseEntity<Object> produtoBusca = produtoController.buscarPorId(produto.getBody().getId());

        Assertions.assertEquals("Intel", resultadoEdicao.getBody());
        Assertions.assertEquals("Intel", resultadoExclusao.getBody());
        Assertions.assertEquals("Intel", Objects.requireNonNull(produtos.getBody()).get(0).getFabricante());
        Assertions.assertEquals("Intel", produtoBusca.getBody());
    }

}
