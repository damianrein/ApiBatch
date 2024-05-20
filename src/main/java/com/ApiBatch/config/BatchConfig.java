package com.ApiBatch.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ApiBatch.steps.ItemDescompressStep;
import com.ApiBatch.steps.ItemProcesorStep;
import com.ApiBatch.steps.ItemReaderStep;
import com.ApiBatch.steps.ItemWriterStep;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	@Bean
	@JobScope
	ItemDescompressStep itemDescompressStep() {
		return new ItemDescompressStep();
	}
	@Bean
	@JobScope
	ItemReaderStep itemReaderStep() {
		return new ItemReaderStep();
	}
	
	@Bean
	@JobScope
	ItemProcesorStep itemProcesorStep() {
		return new ItemProcesorStep();
	}
	
	@Bean
	@JobScope
	ItemWriterStep itemWriterStep() {
		return new ItemWriterStep();
	}
	
	@Bean
	Step itemDescompressFile() {
		return stepBuilderFactory.get("itemDescompressStep")
				.tasklet(itemDescompressStep())
				.build();
	}
	
	@Bean
	Step itemReaderFile() {
		return stepBuilderFactory.get("itemReaderStep")
				.tasklet(itemReaderStep())
				.build();
	}
	
	@Bean
	Step itemProcessFile() {
		return stepBuilderFactory.get("itemProcessStep")
				.tasklet(itemProcesorStep())
				.build();
	}
	
	@Bean
	Step itemWriterFile() {
		return stepBuilderFactory.get("itemWriterStep")
				.tasklet(itemWriterStep())
				.build();
	}
	
	@Bean
	Job jobReadCSV() {
		return jobBuilderFactory.get("readCsvJob")
				.start(itemDescompressFile())
				.next(itemReaderFile())
				.next(itemProcessFile())
				.next(itemWriterFile())
				.build();
	}
}
