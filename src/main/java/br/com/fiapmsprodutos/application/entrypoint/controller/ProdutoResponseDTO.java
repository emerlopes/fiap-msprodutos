package br.com.fiapmsprodutos.application.entrypoint.controller;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProdutoResponseDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private Integer quantidade;
}
