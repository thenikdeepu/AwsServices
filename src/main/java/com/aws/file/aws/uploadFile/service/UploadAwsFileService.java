package com.aws.file.aws.uploadFile.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UploadAwsFileService {

	ResponseEntity<?> uploadFile(MultipartFile file);

}
