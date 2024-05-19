package com.ApiBatch.steps;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.ApiBatch.entities.Person;
import com.ApiBatch.repositories.IPersonRepository;

public class ItemWriterStep implements Tasklet{

	private IPersonRepository personRepo;
	
	public static final Logger log = LoggerFactory.getLogger(ItemWriterStep.class);
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		log.info("==========================>>Inicio step de Escritura<<==================================");
		
		List<Person> listOfPersons = (List<Person>) chunkContext.getStepContext()
				.getStepExecution()
				.getJobExecution()
				.getExecutionContext()
				.get("personsFinal");
		
		personRepo.saveAll(listOfPersons);
		
		log.info("==========================>>Fin step de Escritura<<==================================");
		
		return RepeatStatus.FINISHED;
	}

}
