package br.com.fiapmsprodutos.infrastructure.batch;

import br.com.fiapmsprodutos.infrastructure.database.entity.ProdutoEntity;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ProdutoItemProcessor implements ItemProcessor<ProdutoEntity, ProdutoEntity> {

    @Override
    public ProdutoEntity process(ProdutoEntity produtoEntity) throws Exception {
        return produtoEntity;
    }
}