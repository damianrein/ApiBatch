package com.ApiBatch.controllers;

import java.io.File;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1")
public class BatchController {

	private JobLauncher jobLauncher;
	
	private Job job;
	
	public static final Logger log = LoggerFactory.getLogger(BatchController.class);
	
	@PostMapping
	public ResponseEntity<?> receiveFile(@RequestParam(name="file") MultipartFile multipart){
		
		String fileName = multipart.getOriginalFilename();
		
		try {
			Path path = Paths.get("src" + File.separator + "main" + File.separator + "resources" + File.separator + "files" + File.separator + fileName);
			
			Files.createDirectories(path.getParent());
			Files.copy(multipart.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			
			log.info("====================>>>Inicio proceso Batch<<<================Controller");
			
			JobParameters jobParameters = new JobParametersBuilder()
					.addDate("fecha", new Date())
					.addString("archivo", fileName)
					.toJobParameters();
			
			jobLauncher.run(job, jobParameters);
			
			Map <String, String> response = new HashMap<>();
			response.put("archivo", fileName);
			response.put("estado", "recibido");
			
			return ResponseEntity.ok(response);
		}catch(Exception e) {
			log.error("Error al iniciar el proceso Batch, Error {}",e.getMessage());
			throw new RuntimeException();
		}
		
	}
}
