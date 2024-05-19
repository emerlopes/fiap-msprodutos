package br.com.fiapmsprodutos.domain.entity;

import br.com.fiapmsprodutos.application.entrypoint.controller.ProdutoRequestDTO;
import br.com.fiapmsprodutos.application.entrypoint.controller.ProdutoResponseDTO;
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

    public static ProdutoDomainEntity paraEntidadeDominio(
            final ProdutoRequestDTO produtoRequestDTO, Long idProduto
    ) {
        return ProdutoDomainEntity.builder()
                .id(idProduto)
                .quantity(produtoRequestDTO.getQuantidadeSolicitada())
                .build();
    }

    public static ProdutoResponseDTO paraDTO(
            final ProdutoDomainEntity produtoDomainEntity
    ) {
        return ProdutoResponseDTO.builder()
                .id(produtoDomainEntity.getId())
                .nome(produtoDomainEntity.getName())
                .descricao(produtoDomainEntity.getDescription())
                .preco(produtoDomainEntity.getPrice())
                .quantidade(produtoDomainEntity.getQuantity())
                .build();
    }

    public static ProdutoDomainEntity paraEntidadeDominio(
            final String idProduto
    ) {
        return ProdutoDomainEntity.builder()
                .id(Long.parseLong(idProduto))
                .build();
    }

    public static ProdutoDomainEntity paraEntidadeDominio(
            final ProdutoEntity produtoEntity
    ) {
        return ProdutoDomainEntity.builder()
                .id(produtoEntity.getId())
                .name(produtoEntity.getName())
                .description(produtoEntity.getDescription())
                .price(produtoEntity.getPrice())
                .quantity(produtoEntity.getQuantity())
                .build();
    }

    public static ProdutoEntity paraEntidade(
            final ProdutoDomainEntity produtoDomainEntity
    ) {
        return new ProdutoEntity()
                .setId(produtoDomainEntity.getId())
                .setName(produtoDomainEntity.getName())
                .setDescription(produtoDomainEntity.getDescription())
                .setPrice(produtoDomainEntity.getPrice())
                .setQuantity(produtoDomainEntity.getQuantity());
    }

    @Override
    public String toString() {
        return "ProdutoDomainEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
