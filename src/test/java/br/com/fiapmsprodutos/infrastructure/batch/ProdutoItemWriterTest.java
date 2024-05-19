package br.com.fiapmsprodutos.infrastructure.batch;

import br.com.fiapmsprodutos.infrastructure.database.entity.ProdutoEntity;
import br.com.fiapmsprodutos.infrastructure.database.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.item.Chunk;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoItemWriterTest {

    @InjectMocks
    private ProdutoItemWriter produtoItemWriter;

    @Mock
    private ProdutoRepository produtoRepository;

    @Captor
    private ArgumentCaptor<List<ProdutoEntity>> captor;

    @Test
    public void testWrite() throws Exception {
        when(produtoRepository.saveAll(anyList())).thenReturn(Arrays.asList(
                new ProdutoEntity().setId(1L).setName("Produto 1"),
                new ProdutoEntity().setId(2L).setName("Produto 2")
        ));

        produtoItemWriter.write(new Chunk<>(Arrays.asList(
                new ProdutoEntity().setId(1L).setName("Produto 1"),
                new ProdutoEntity().setId(2L).setName("Produto 2")
        )));

        verify(produtoRepository, times(1)).saveAll(captor.capture());
        List<ProdutoEntity> savedProducts = captor.getValue();
        assertEquals(2, savedProducts.size());
        assertEquals("Produto 1", savedProducts.get(0).getName());
        assertEquals("Produto 2", savedProducts.get(1).getName());
    }

}