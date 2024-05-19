package br.com.fiapmsprodutos.domain.repository;


import br.com.fiapmsprodutos.domain.entity.ProdutoDomainEntity;

import java.util.List;

public interface ProdutoDomainRepository {
    List<ProdutoDomainEntity> buscarProdutos();
}
