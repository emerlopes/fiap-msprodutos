package br.com.fiapmsprodutos.domain.entity;

import br.com.fiapmsprodutos.infrastructure.database.entity.ProdutoEntity;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ProdutoDomainEntity {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;

    public static List<ProdutoDomainEntity> paraEntidadeDominio(
            final List<ProdutoEntity> produtoEntities
    ) {
        return produtoEntities.stream()
                .map(produtoEntity -> ProdutoDomainEntity.builder()
                        .id(produtoEntity.getId())
                        .name(produtoEntity.getName())
                        .description(produtoEntity.getDescription())
                        .price(produtoEntity.getPrice())
                        .quantity(produtoEntity.getQuantity())
                        .build())
                .toList();
    }

}
