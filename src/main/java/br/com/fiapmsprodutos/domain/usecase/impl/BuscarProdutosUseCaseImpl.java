package br.com.fiapmsprodutos.domain.usecase.impl;

import br.com.fiapmsprodutos.domain.entity.ProdutoDomainEntity;
import br.com.fiapmsprodutos.domain.repository.ProdutoDomainRepository;
import br.com.fiapmsprodutos.domain.usecase.BuscarProdutosUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarProdutosUseCaseImpl implements BuscarProdutosUseCase {

    private final ProdutoDomainRepository produtoDomainRepository;

    public BuscarProdutosUseCaseImpl(ProdutoDomainRepository produtoDomainRepository) {
        this.produtoDomainRepository = produtoDomainRepository;
    }

    @Override
    public List<ProdutoDomainEntity> execute() {
        return produtoDomainRepository.buscarProdutos();
    }
}
