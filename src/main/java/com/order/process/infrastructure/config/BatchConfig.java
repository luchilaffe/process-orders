package com.order.process.infrastructure.config;

import com.order.process.domain.model.PurchaseOrder;
import com.order.process.infrastructure.batch.EmailService;
import com.order.process.infrastructure.batch.OrderProcessor;
import com.order.process.infrastructure.batch.OrderReader;
import com.order.process.infrastructure.batch.OrderWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public BatchConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public Job processOrdersJob() {
        return new JobBuilder("processOrdersJob", jobRepository)
                .start(processOrdersStep())
                .build();
    }

    @Bean
    public Step processOrdersStep() {
        return new StepBuilder("processOrdersStep", jobRepository)
                .<PurchaseOrder, PurchaseOrder>chunk(10, transactionManager)
                .reader(orderReader())
                .processor(orderProcessor())
                .writer(orderWriter())
                .build();
    }

    @Bean
    public OrderReader orderReader() {
        return new OrderReader();
    }

    @Bean
    public OrderProcessor orderProcessor() {
        return new OrderProcessor();
    }

    @Bean
    public OrderWriter orderWriter() {
        return new OrderWriter(new EmailService(new JavaMailSenderImpl(), new MustacheViewResolver()));
    }

}
