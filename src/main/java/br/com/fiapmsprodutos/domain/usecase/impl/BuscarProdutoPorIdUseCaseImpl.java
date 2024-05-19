package br.com.fiapmsprodutos.domain.usecase.impl;

import br.com.fiapmsprodutos.domain.entity.ProdutoDomainEntity;
import br.com.fiapmsprodutos.domain.repository.ProdutoDomainRepository;
import br.com.fiapmsprodutos.domain.usecase.BuscarProdutoPorIdUseCase;
import org.springframework.stereotype.Service;

@Service
public class BuscarProdutoPorIdUseCaseImpl implements BuscarProdutoPorIdUseCase {

    private final ProdutoDomainRepository produtoDomainRepository;

    public BuscarProdutoPorIdUseCaseImpl(
            final ProdutoDomainRepository produtoDomainRepository
    ) {
        this.produtoDomainRepository = produtoDomainRepository;
    }

    @Override
    public ProdutoDomainEntity execute(ProdutoDomainEntity domainObject) {
        return produtoDomainRepository.buscarProdutoPorId(domainObject);
    }
}
