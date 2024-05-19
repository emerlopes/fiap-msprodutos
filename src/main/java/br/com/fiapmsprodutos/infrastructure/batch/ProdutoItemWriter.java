package br.com.fiapmsprodutos.infrastructure.batch;

import br.com.fiapmsprodutos.infrastructure.database.entity.ProdutoEntity;
import br.com.fiapmsprodutos.infrastructure.database.repository.ProdutoRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoItemWriter implements ItemWriter<ProdutoEntity> {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public void write(Chunk<? extends ProdutoEntity> chunk) throws Exception {
        produtoRepository.saveAll(chunk.getItems());
    }
}
