package br.com.fiapmsprodutos.application.entrypoint.controller;

import br.com.fiapmsprodutos.domain.entity.ProdutoDomainEntity;
import br.com.fiapmsprodutos.domain.usecase.BuscarProdutoUseCase;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProdutoControllerTest {

    @InjectMocks
    private ProdutoController produtoController;

    @Mock
    private JobLauncher jobLauncher;

    @Mock
    private Job job;

    @Mock
    private BuscarProdutoUseCase buscarProdutoUseCase;

    @Test
    void deveExecutarJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        // Configuração do mock do UseCase para retornar uma lista de produtos
        List<ProdutoDomainEntity> produtosEsperados = criarProdutos();
        when(buscarProdutoUseCase.execute()).thenReturn(produtosEsperados);

        // Configuração do mock do JobLauncher para retornar um JobExecution COMPLETED
        JobExecution jobExecution = new JobExecution(1L);
        jobExecution.setStatus(BatchStatus.COMPLETED);
        when(jobLauncher.run(Mockito.any(Job.class), Mockito.any(JobParameters.class))).thenReturn(jobExecution);

        // Execução do método a ser testado
        List<ProdutoDomainEntity> produtos = produtoController.executarJob();

        // Verificações dos resultados
        Assertions.assertThat(produtos).isNotNull();
        Assertions.assertThat(produtos).isNotEmpty();
        Assertions.assertThat(produtos).hasSize(2);
        Assertions.assertThat(produtos).containsAll(produtosEsperados);
        Assertions.assertThat(produtos).containsExactlyElementsOf(produtosEsperados);
        Assertions.assertThat(produtos).containsExactlyInAnyOrderElementsOf(produtosEsperados);
    }

    @Test
    void shouldHandleJobExecutionException() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        when(jobLauncher.run(any(Job.class), any(JobParameters.class))).thenThrow(new JobExecutionAlreadyRunningException("Job execution exception"));

        assertThrows(JobExecutionAlreadyRunningException.class, () -> produtoController.executarJob());
    }

    private List<ProdutoDomainEntity> criarProdutos() {
        return List.of(
                ProdutoDomainEntity.builder()
                        .id(1L)
                        .name("Produto 1")
                        .description("Descrição 1")
                        .price(10.0)
                        .quantity(10)
                        .build(),
                ProdutoDomainEntity.builder()
                        .id(2L)
                        .name("Produto 2")
                        .description("Descrição 2")
                        .price(20.0)
                        .quantity(20)
                        .build()
        );
    }

}
