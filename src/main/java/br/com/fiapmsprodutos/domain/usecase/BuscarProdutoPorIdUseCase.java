package br.com.fiapmsprodutos.domain.usecase;

import br.com.fiapmsprodutos.domain.entity.ProdutoDomainEntity;
import br.com.fiapmsprodutos.domain.shared.IExecuteArgs;
import br.com.fiapmsprodutos.domain.shared.IExecuteNoArgs;

public interface BuscarProdutoPorIdUseCase extends IExecuteArgs<ProdutoDomainEntity, ProdutoDomainEntity> {
}
