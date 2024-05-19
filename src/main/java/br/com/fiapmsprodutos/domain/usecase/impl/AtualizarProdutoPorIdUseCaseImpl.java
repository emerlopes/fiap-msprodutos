package br.com.fiapmsprodutos.domain.usecase.impl;

import br.com.fiapmsprodutos.domain.entity.ProdutoDomainEntity;
import br.com.fiapmsprodutos.domain.repository.ProdutoDomainRepository;
import br.com.fiapmsprodutos.domain.usecase.AtualizarProdutoPorIdUseCase;
import org.springframework.stereotype.Service;

@Service
public class AtualizarProdutoPorIdUseCaseImpl implements AtualizarProdutoPorIdUseCase {

    private final ProdutoDomainRepository produtoDomainRepository;

    public AtualizarProdutoPorIdUseCaseImpl(
            final ProdutoDomainRepository produtoDomainRepository
    ) {
        this.produtoDomainRepository = produtoDomainRepository;
    }

    @Override
    public ProdutoDomainEntity execute(ProdutoDomainEntity produto) {
        return produtoDomainRepository.atualizarProduto(produto);
    }
}
