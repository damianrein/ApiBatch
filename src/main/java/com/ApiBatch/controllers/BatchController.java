package com.ApiBatch.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1")
public class BatchController {

	public static final Logger log = LoggerFactory.getLogger(BatchController.class);
	
	@PostMapping
	public ResponseEntity<?> receiveFile(@RequestParam(name="file") MultipartFile multipart){
		
		return null;
	}
}
