package com.aws.file.aws.uploadFile.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.aws.file.aws.config.AwsS3Client;
import com.aws.file.generic.ResponseDto;
import com.aws.file.generic.ResponseMessage;

@Service
public class UploadAwsFileServiceImpl implements UploadAwsFileService{
	
	private static final Logger logger=LoggerFactory.getLogger(UploadAwsFileServiceImpl.class);
	
	@Autowired
	private AwsS3Client awsS3Client;

	@Override
	public ResponseEntity<?> uploadFile(MultipartFile file) {
		logger.info("Inside UploadAwsFileServiceImpl :: Inside uploadFile");
		try {
			String fileUrl=awsS3Client.uploadAwsFile(file);
			if(fileUrl!=null && !fileUrl.isEmpty()) {
				logger.info("File url is {}", fileUrl);
				return new ResponseEntity<>(new ResponseDto(ResponseMessage.SUCCESS_API_CODE,ResponseMessage.SUCCESS_API_MSG,fileUrl),HttpStatus.OK);
			}else {
				logger.info("No fileUrl data");
				return new ResponseEntity<>(new ResponseDto(ResponseMessage.ERROR_CODE,ResponseMessage.ERROR_CODE_MESSAGE,null),HttpStatus.OK);
			}
		}catch(Exception e) {
			logger.info("Error while uploadFile : UploadAwsFileServiceImpl");
			logger.info(e.getMessage(),e);
			return new ResponseEntity<>(new ResponseDto(ResponseMessage.ERROR_CODE,ResponseMessage.ERROR_CODE_MESSAGE,null),HttpStatus.OK);
					
		}
	}

}
