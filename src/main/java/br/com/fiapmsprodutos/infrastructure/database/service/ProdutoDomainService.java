package br.com.fiapmsprodutos.infrastructure.database.service;

import br.com.fiapmsprodutos.application.entrypoint.exception.BusinessException;
import br.com.fiapmsprodutos.domain.entity.ProdutoDomainEntity;
import br.com.fiapmsprodutos.domain.repository.ProdutoDomainRepository;
import br.com.fiapmsprodutos.infrastructure.database.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoDomainService implements ProdutoDomainRepository {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public List<ProdutoDomainEntity> buscarProdutos() {
        final var produtoEntities = produtoRepository.findAll();

        return ProdutoDomainEntity.paraEntidadeDominio(produtoEntities);
    }

    @Override
    public ProdutoDomainEntity buscarProdutoPorId(ProdutoDomainEntity produto) {
        final var irProduto = produto.getId();
        final var produtoEntity = produtoRepository.findById(irProduto);

        if (produtoEntity.isEmpty()) {
            throw new BusinessException("Produto não encontrado");
        }
        return ProdutoDomainEntity.paraEntidadeDominio(produtoEntity.get());

    }
}