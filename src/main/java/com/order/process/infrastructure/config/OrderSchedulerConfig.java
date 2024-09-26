package com.order.process.infrastructure.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class OrderSchedulerConfig {

    private final JobLauncher jobLauncher;
    private final Job processOrdersJob;


    public OrderSchedulerConfig(JobLauncher jobLauncher, Job processOrdersJob) {
        this.jobLauncher = jobLauncher;
        this.processOrdersJob = processOrdersJob;
    }

    @Scheduled(fixedRate = 60000) // Execute every 60 seconds
    public void launchJob() throws
            JobInstanceAlreadyCompleteException,
            JobExecutionAlreadyRunningException,
            JobParametersInvalidException,
            JobRestartException
    {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(processOrdersJob, jobParameters);
    }

}
