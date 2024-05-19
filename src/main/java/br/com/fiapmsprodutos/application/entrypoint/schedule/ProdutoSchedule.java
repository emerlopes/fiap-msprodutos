package br.com.fiapmsprodutos.application.entrypoint.schedule;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
@EnableScheduling
public class ProdutoSchedule {

    private final JobLauncher jobLauncher;

    private final Job job;

    public ProdutoSchedule(
            final JobLauncher jobLauncher,
            final Job job
    ) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @Scheduled(cron = "0/10 * * * * *")
    public void executarJob() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        System.out.println("Executando job de importação de produtos");

        JobParameters jobParameters = new JobParametersBuilder()
                .addDate("timestamp", Calendar.getInstance().getTime())
                .toJobParameters();

        JobExecution jobExecution = jobLauncher.run(job, jobParameters);

        while (jobExecution.getStatus() != BatchStatus.COMPLETED) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            System.out.println("Job concluído com sucesso");
        } else {
            System.out.println("Job falhou");
        }
    }

}
