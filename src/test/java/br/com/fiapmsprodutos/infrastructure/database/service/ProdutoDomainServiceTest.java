package br.com.fiapmsprodutos.infrastructure.database.service;

import br.com.fiapmsprodutos.domain.entity.ProdutoDomainEntity;
import br.com.fiapmsprodutos.infrastructure.database.entity.ProdutoEntity;
import br.com.fiapmsprodutos.infrastructure.database.repository.ProdutoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProdutoDomainServiceTest {

    @InjectMocks
    ProdutoDomainService produtoDomainService;

    @Mock
    ProdutoRepository produtoRepository;

    @Test
    void deveBuscarProdutos() {
        // Arrange
        final var produtosEntidade = List.of(criarProdutoEntity());
        Mockito.when(produtoRepository.findAll()).thenReturn(produtosEntidade);

        // Act
        final var produtos = produtoDomainService.buscarProdutos();

        // Assert
        Assertions.assertThat(produtos).isNotEmpty();
        Assertions.assertThat(produtos).hasSize(1);
        Assertions.assertThat(produtos.get(0).getId()).isEqualTo(1L);
        Assertions.assertThat(produtos.get(0).getName()).isEqualTo("Produto 1");
        Assertions.assertThat(produtos.get(0).getDescription()).isEqualTo("Descrição do Produto 1");
        Assertions.assertThat(produtos.get(0).getPrice()).isEqualTo(10.0);
        Assertions.assertThat(produtos.get(0).getQuantity()).isEqualTo(10);
    }

    @Test
    void deveBuscarProdutoPorId() {
        // Arrange
        final var produtoEntidade = criarProdutoEntity();
        Mockito.when(produtoRepository.findById(1L)).thenReturn(java.util.Optional.of(produtoEntidade));

        // Act
        final var produto = produtoDomainService.buscarProdutoPorId(criarProdutosDomainEntity());

        // Assert
        Assertions.assertThat(produto).isNotNull();
        Assertions.assertThat(produto.getId()).isEqualTo(1L);
        Assertions.assertThat(produto.getName()).isEqualTo("Produto 1");
        Assertions.assertThat(produto.getDescription()).isEqualTo("Descrição do Produto 1");
        Assertions.assertThat(produto.getPrice()).isEqualTo(10.0);
        Assertions.assertThat(produto.getQuantity()).isEqualTo(10);
    }

    private ProdutoDomainEntity criarProdutosDomainEntity() {
        return ProdutoDomainEntity.builder()
                .id(1L)
                .name("Produto 1")
                .description("Descrição 1")
                .price(10.0)
                .quantity(10)
                .build();
    }

    private ProdutoEntity criarProdutoEntity() {
        return new ProdutoEntity()
                .setId(1L)
                .setName("Produto 1")
                .setDescription("Descrição do Produto 1")
                .setPrice(10.0)
                .setQuantity(10);
    }

}