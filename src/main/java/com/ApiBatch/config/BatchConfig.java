package com.ApiBatch.config;

import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.context.annotation.Bean;

import com.ApiBatch.steps.ItemReaderStep;

public class BatchConfig {

	@Bean
	@JobScope
	ItemReaderStep itemReaderStep() {
		return new ItemReaderStep();
	}
}
