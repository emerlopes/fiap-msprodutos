package br.com.fiapmsprodutos.application.entrypoint.schedule;

import br.com.fiapmsprodutos.domain.entity.ProdutoDomainEntity;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProdutoScheduleTest {

    @InjectMocks
    ProdutoSchedule produtoSchedule;

    @Mock
    JobLauncher jobLauncher;

    @Mock
    Job job;

    @Test
    void deveExecutarJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        JobExecution jobExecution = new JobExecution(1L);
        jobExecution.setStatus(BatchStatus.COMPLETED);
        when(jobLauncher.run(Mockito.any(Job.class), Mockito.any(JobParameters.class))).thenReturn(jobExecution);

        // Execução do método a ser testado
        produtoSchedule.executarJob();

    }

}