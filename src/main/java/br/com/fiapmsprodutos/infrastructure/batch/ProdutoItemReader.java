package br.com.fiapmsprodutos.infrastructure.batch;

import br.com.fiapmsprodutos.infrastructure.database.entity.ProdutoEntity;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;


@Configuration
public class ProdutoItemReader {

    @Bean
    public FlatFileItemReader<ProdutoEntity> reader() {
        FlatFileItemReader<ProdutoEntity> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("products.csv"));
        reader.setLinesToSkip(1);
        reader.setLineMapper(new DefaultLineMapper<>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("name", "description", "price", "quantity");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                setTargetType(ProdutoEntity.class);
            }});
        }});
        return reader;
    }
}
