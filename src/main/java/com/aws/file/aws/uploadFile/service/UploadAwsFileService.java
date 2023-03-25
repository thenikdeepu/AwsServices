package com.aws.file.aws.uploadFile.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.aws.file.aws.uploadFile.dto.DownloadFileReqDto;

public interface UploadAwsFileService {

	ResponseEntity<?> uploadFile(MultipartFile file);

	ResponseEntity<?> downloadFile(DownloadFileReqDto downloadFileReqDto);

}
