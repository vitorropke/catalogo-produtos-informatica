package com.informatica.catalogo.dto;

import jakarta.validation.constraints.NotBlank;

public record ProdutoDTO(@NotBlank String fabricante, @NotBlank String modelo, @NotBlank String lote, int quantidade,
                         float preco, float desconto, @NotBlank String urlImagem) {
}
