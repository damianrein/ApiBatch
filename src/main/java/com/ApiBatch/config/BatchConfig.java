package com.ApiBatch.config;

import org.springframework.context.annotation.Bean;

import com.ApiBatch.steps.ItemReaderStep;

public class BatchConfig {

	@Bean
	ItemReaderStep itemReaderStep() {
		return new ItemReaderStep();
	}
}
