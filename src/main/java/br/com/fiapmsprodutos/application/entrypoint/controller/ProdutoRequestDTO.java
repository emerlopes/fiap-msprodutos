package br.com.fiapmsprodutos.application.entrypoint.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

@Data
@Getter
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProdutoRequestDTO {

    @JsonProperty("quantidade_solicitada")
    private Integer quantidadeSolicitada;

    public ProdutoRequestDTO() {
        // Construtor padrão
    }

}
