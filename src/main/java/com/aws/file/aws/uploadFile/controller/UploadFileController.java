package com.aws.file.aws.uploadFile.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aws.file.aws.uploadFile.service.UploadAwsFileService;

@RequestMapping("aws/file")
@RestController
public class UploadFileController {

	private static final Logger logger = LoggerFactory.getLogger(UploadFileController.class);

	@Autowired
	private UploadAwsFileService uploadAwsFileService;

	@PostMapping("/uploadFile")
	public ResponseEntity<?> uploadFile(@RequestPart("file") MultipartFile file, HttpServletRequest request) {
		logger.info("Inside UploadFileController :: Inside uploadFile");
		return uploadAwsFileService.uploadFile(file);
	}
}
