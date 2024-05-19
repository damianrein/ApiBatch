package com.ApiBatch.steps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class ItemProcesorStep implements Tasklet{

	public static final Logger log = LoggerFactory.getLogger(ItemProcesorStep.class);
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		log.info("===========================Inicio del step Proceso=======================");
		
		
		
		log.info("============================Fin del step Proceso==========================");
		return null;
	}

}
