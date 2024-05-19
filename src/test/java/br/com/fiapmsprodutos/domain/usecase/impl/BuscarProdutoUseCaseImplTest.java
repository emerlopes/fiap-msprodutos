package br.com.fiapmsprodutos.domain.usecase.impl;

import br.com.fiapmsprodutos.domain.entity.ProdutoDomainEntity;
import br.com.fiapmsprodutos.domain.repository.ProdutoDomainRepository;
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
class BuscarProdutoUseCaseImplTest {

    @InjectMocks
    BuscarProdutoUseCaseImpl buscarProdutoUseCase;

    @Mock
    ProdutoDomainRepository produtoDomainRepository;

    @Test
    void deveRetornarProdutos() {
        // Arrange
        final var produtos = criarProdutos();
        Mockito.when(produtoDomainRepository.buscarProdutos()).thenReturn(produtos);

        // Act
        final var resultado = buscarProdutoUseCase.execute();

        // Assert
        Assertions.assertThat(resultado).isNotNull();
        Assertions.assertThat(resultado).isNotEmpty();
        Assertions.assertThat(resultado).hasSize(2);
        Assertions.assertThat(resultado).containsAll(produtos);
    }

    private List<ProdutoDomainEntity> criarProdutos() {
        return List.of(
                ProdutoDomainEntity.builder()
                        .id(1L)
                        .name("Produto 1")
                        .description("Descrição 1")
                        .price(10.0)
                        .quantity(10)
                        .build(),
                ProdutoDomainEntity.builder()
                        .id(2L)
                        .name("Produto 2")
                        .description("Descrição 2")
                        .price(20.0)
                        .quantity(20)
                        .build()
        );
    }

}