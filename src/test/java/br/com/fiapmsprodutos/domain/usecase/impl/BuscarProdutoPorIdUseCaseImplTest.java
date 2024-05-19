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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BuscarProdutoPorIdUseCaseImplTest {

    @InjectMocks
    private BuscarProdutoPorIdUseCaseImpl buscarProdutoPorIdUseCase;

    @Mock
    private ProdutoDomainRepository produtoDomainRepository;

    @Test
    void deveBuscarProdutoPorId() {
        // Arrange
        Mockito.when(produtoDomainRepository.buscarProdutoPorId(Mockito.any(ProdutoDomainEntity.class))).thenReturn(Mockito.any(ProdutoDomainEntity.class));

        // Act
        final var produto = buscarProdutoPorIdUseCase.execute(criarProduto());

        // Assert
        Assertions.assertThat(produto).isNull();
    }

    private ProdutoDomainEntity criarProduto() {
        return ProdutoDomainEntity.builder()
                .id(1L)
                .name("Produto 1")
                .description("Descrição do produto 1")
                .price(10.0)
                .quantity(10)
                .build();
    }
}