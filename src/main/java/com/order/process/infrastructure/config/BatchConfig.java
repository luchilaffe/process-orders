package com.order.process.infrastructure.config;

import com.order.process.domain.model.PurchaseOrder;
import com.order.process.infrastructure.batch.OrderReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final OrderReader orderReader;
    private final ItemProcessor<PurchaseOrder, PurchaseOrder> purchaseOrderItemProcessor;
    private final ItemWriter<PurchaseOrder> orderItemWriter;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public BatchConfig(
            OrderReader orderReader,
            ItemProcessor<PurchaseOrder, PurchaseOrder> itemProcessor,
            ItemWriter<PurchaseOrder> orderItemWriter,
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager
    ) {
        this.orderReader = orderReader;
        this.purchaseOrderItemProcessor = itemProcessor;
        this.orderItemWriter = orderItemWriter;
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public Job processOrdersJob() {
        return new JobBuilder("processOrdersJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(processOrdersStep())
                .build();
    }

    @Bean
    public Step processOrdersStep() {
        return new StepBuilder("processOrdersStep", jobRepository)
                .<PurchaseOrder, PurchaseOrder>chunk(10, transactionManager)
                .reader(orderReader)
                .processor(purchaseOrderItemProcessor)
                .writer(orderItemWriter)
                .build();
    }

}
