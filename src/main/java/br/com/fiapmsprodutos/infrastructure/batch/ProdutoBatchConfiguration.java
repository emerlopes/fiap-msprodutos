package br.com.fiapmsprodutos.infrastructure.batch;


import br.com.fiapmsprodutos.infrastructure.database.entity.ProdutoEntity;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;


@Configuration
public class ProdutoBatchConfiguration {

    @Autowired
    private ProdutoItemProcessor processor;

    @Autowired
    private ProdutoItemWriter writer;

    @Autowired
    private ProdutoItemReader produtoItemReader;

    @Bean
    public Job importProductJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new JobBuilder("importProductJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step(jobRepository, transactionManager))
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step", jobRepository)
                .<ProdutoEntity, ProdutoEntity>chunk(10, transactionManager)
                .reader(produtoItemReader.reader())
                .processor(processor)
                .writer(writer)
                .build();
    }

}