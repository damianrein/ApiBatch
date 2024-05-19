package com.ApiBatch.steps;

import java.io.FileReader;
import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

import com.ApiBatch.entities.Person;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;


public class ItemReaderStep implements Tasklet{

	@Autowired
	private ResourceLoader rl;
	
	private static final Logger log = LoggerFactory.getLogger(ItemReaderStep.class);
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		log.info("=============================>>Inicio del step de Lectura<<=============================================");
		
		Reader reader = new FileReader(rl.getResource("C:\\Users\\usuario\\Desktop\\prueba java jdk-17").getFile());
		
		CSVParser parser = new CSVParserBuilder()
				.withSeparator(',')
				.build();
		
		CSVReader csvReader = new CSVReaderBuilder(reader)
				.withCSVParser(parser)
				.withSkipLines(1)
				.build();
		
		List<Person> listOfPersons = new LinkedList<>();
		
		String [] linea;
		while((linea = csvReader.readNext())!=null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date fecha = dateFormat.parse(linea[5]);
			Person person = new Person(Long.parseLong(linea[0]),
					linea[1],
					linea[2],
					linea[3],
					Boolean.parseBoolean(linea[4]),
					fecha);
			listOfPersons.add(person);
		}
		
		csvReader.close();
		reader.close();
		
		
		chunkContext.getStepContext()
					.getStepExecution()
					.getJobExecution()
					.getExecutionContext()
					.put("Persons", listOfPersons);
		
		log.info("=============================>>Fin del step de Lectura<<================================================");
		return RepeatStatus.FINISHED;
	}

}
