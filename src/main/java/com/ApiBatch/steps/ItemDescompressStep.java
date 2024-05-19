package com.ApiBatch.steps;

import java.io.File;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class ItemDescompressStep implements Tasklet{

	public static final Logger log = LoggerFactory.getLogger(ItemDescompressStep.class);
	
	private ResourceLoader rl;
	
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		
		log.info("===========================Inicio del step de Descompresion=======================");
		Resource resource = (Resource) rl.getResource("C:\\Users\\usuario\\Desktop\\prueba java jdk-17\\Persons.zip").getFile();
		
		String zipFilePath = resource.getFile().getAbsolutePath();
		
		try (ZipFile zip = new ZipFile(zipFilePath)) {
			File destDir = new File(resource.getFile().getParentFile(),"descompresDir");
			
			if(!destDir.exists()) {
				destDir.mkdir();
			}
			
			Enumeration<? extends ZipEntry> entries = zip.entries();
		}
		
		log.info("===========================Fin del step de Descompresion=======================");
		
		return null;
	}

}
