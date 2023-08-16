package com.informatica.catalogo.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "produtos")
public class ProdutoModel {

    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column
    private String fabricante;
    @Column
    private String modelo;
    @Column
    private String lote;
    @Column
    private int quantidade;
    @Column
    private float preco;
    @Column
    private float desconto;
    @Column(name = "url_imagem")
    private String urlImagem;

}
