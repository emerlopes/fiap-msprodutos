package br.com.fiapmsprodutos.application.entrypoint.controller;

import br.com.fiapmsprodutos.domain.entity.ProdutoDomainEntity;
import br.com.fiapmsprodutos.domain.usecase.BuscarProdutoPorIdUseCase;
import br.com.fiapmsprodutos.domain.usecase.BuscarProdutosUseCase;
import br.com.fiapmsprodutos.domain.usecase.impl.AtualizarProdutoPorIdUseCaseImpl;
import org.slf4j.Logger;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final JobLauncher jobLauncher;

    private final Job job;

    private final BuscarProdutosUseCase buscarProdutosUseCase;

    private final BuscarProdutoPorIdUseCase buscarProdutoPorIdUseCase;

    private final AtualizarProdutoPorIdUseCaseImpl atualizarProdutoPorIdUseCase;

    private final Logger logger;

    public ProdutoController(
            final JobLauncher jobLauncher,
            final Job job,
            final BuscarProdutosUseCase buscarProdutosUseCase,
            final BuscarProdutoPorIdUseCase buscarProdutoPorIdUseCase,
            final AtualizarProdutoPorIdUseCaseImpl atualizarProdutoPorIdUseCase,
            final Logger logger
    ) {
        this.jobLauncher = jobLauncher;
        this.job = job;
        this.buscarProdutosUseCase = buscarProdutosUseCase;
        this.buscarProdutoPorIdUseCase = buscarProdutoPorIdUseCase;
        this.atualizarProdutoPorIdUseCase = atualizarProdutoPorIdUseCase;
        this.logger = logger;
    }

    @PostMapping("/jobs")
    public List<ProdutoDomainEntity> executarJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        JobParameters jobParameters = new JobParametersBuilder()
                .addDate("timestamp", Calendar.getInstance().getTime())
                .toJobParameters();

        JobExecution jobExecution = jobLauncher.run(job, jobParameters);

        while (jobExecution.isRunning()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        return buscarProdutosUseCase.execute();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarProdutoPorId(
            @PathVariable("id") final String produto
    ) {
        logger.info("Requisição para buscar produto por id: {}", produto);

        final var entidadeDominioRequest = ProdutoDomainEntity.paraEntidadeDominio(produto);
        final var entidadeDominiuResponse = buscarProdutoPorIdUseCase.execute(entidadeDominioRequest);

        logger.info("Produto encontrado: {}", entidadeDominiuResponse);

        return ResponseEntity.status(HttpStatus.OK).body(ProdutoDomainEntity.paraDTO(entidadeDominiuResponse));
    }

    @PostMapping("atualizar/{idProduto}")
    public ResponseEntity<?> atualizarEstoqueProduto(
            @PathVariable("idProduto") final Long idProduto,
            @RequestBody final ProdutoRequestDTO produtoRequestDTO
    ) {
        logger.info("Requisição para atualizar estoque do produto: {}", idProduto);

        final var entidadeDominioRequest = ProdutoDomainEntity.paraEntidadeDominio(produtoRequestDTO, idProduto);

        final var entidadeDominioResponse = atualizarProdutoPorIdUseCase.execute(entidadeDominioRequest);

        logger.info("Estoque atualizado: {}", entidadeDominioResponse);

        return ResponseEntity.status(HttpStatus.OK).body(ProdutoDomainEntity.paraDTO(entidadeDominioResponse));
    }


}
