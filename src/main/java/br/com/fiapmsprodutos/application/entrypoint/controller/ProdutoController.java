package br.com.fiapmsprodutos.application.entrypoint.controller;

import br.com.fiapmsprodutos.domain.entity.ProdutoDomainEntity;
import br.com.fiapmsprodutos.domain.usecase.BuscarProdutosUseCase;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final JobLauncher jobLauncher;

    private final Job job;

    private final BuscarProdutosUseCase buscarProdutosUseCase;

    public ProdutoController(
            final JobLauncher jobLauncher,
            final Job job,
            final BuscarProdutosUseCase buscarProdutosUseCase
    ) {
        this.jobLauncher = jobLauncher;
        this.job = job;
        this.buscarProdutosUseCase = buscarProdutosUseCase;
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


}
