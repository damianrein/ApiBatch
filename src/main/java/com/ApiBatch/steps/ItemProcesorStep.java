package com.ApiBatch.steps;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import com.ApiBatch.entities.Person;

public class ItemProcesorStep implements Tasklet{

	public static final Logger log = LoggerFactory.getLogger(ItemProcesorStep.class);
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		log.info("===========================Inicio del step Proceso=======================");
		
		List<Person> listOfPersons = (List<Person>) chunkContext.getStepContext()
												.getStepExecution()
												.getJobExecution()
												.getExecutionContext()
												.get("Persons");
		
        List<Person> personsFinalList = listOfPersons.stream().map(person -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            person.setInsertionDate(formatter.format(LocalDateTime.now()));
            return person;
        }).collect(Collectors.toList());

        chunkContext.getStepContext()
                    .getStepExecution()
                    .getJobExecution()
                    .getExecutionContext()
                    .put("personsFinal", personsFinalList);
		
		log.info("============================Fin del step Proceso==========================");
		return null;
	}

}
