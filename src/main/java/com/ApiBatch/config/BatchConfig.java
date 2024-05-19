package com.ApiBatch.config;

import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.context.annotation.Bean;

import com.ApiBatch.steps.ItemDescompressStep;
import com.ApiBatch.steps.ItemProcesorStep;
import com.ApiBatch.steps.ItemReaderStep;
import com.ApiBatch.steps.ItemWriterStep;

public class BatchConfig {

	@Bean
	ItemDescompressStep itemDescompressStep() {
		return new ItemDescompressStep();
	}
	@Bean
	@JobScope
	ItemReaderStep itemReaderStep() {
		return new ItemReaderStep();
	}
	
	@Bean
	ItemProcesorStep itemProcesorStep() {
		return new ItemProcesorStep();
	}
	
	@Bean
	ItemWriterStep itemWriterStep() {
		return new ItemWriterStep();
	}
}
