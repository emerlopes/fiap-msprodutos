package br.com.fiapmsprodutos.infrastructure.batch;

import br.com.fiapmsprodutos.infrastructure.database.entity.ProdutoEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProdutoItemProcessorTest {

    @InjectMocks
    private ProdutoItemProcessor produtoItemProcessor;

    @Test
    public void testProcess() throws Exception {
        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setId(1L);
        produtoEntity.setName("Produto Teste");
        produtoEntity.setDescription("Descrição do Produto Teste");
        produtoEntity.setPrice(10.0);
        produtoEntity.setQuantity(5);

        ProdutoEntity result = produtoItemProcessor.process(produtoEntity);

        assertEquals(produtoEntity, result);
    }
}