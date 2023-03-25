package com.aws.file.aws.uploadFile.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.aws.file.aws.config.AwsS3Client;
import com.aws.file.aws.uploadFile.dto.DownloadFileReqDto;
import com.aws.file.generic.ResponseDto;
import com.aws.file.generic.ResponseMessage;
import com.amazonaws.util.IOUtils;

@Service
public class UploadAwsFileServiceImpl implements UploadAwsFileService {

	private static final Logger logger = LoggerFactory.getLogger(UploadAwsFileServiceImpl.class);

	@Autowired
	private AwsS3Client awsS3Client;

	@Override
	public ResponseEntity<?> uploadFile(MultipartFile file) {
		logger.info("Inside UploadAwsFileServiceImpl :: Inside uploadFile");
		try {
			String fileUrl = awsS3Client.uploadAwsFile(file);
			if (fileUrl != null && !fileUrl.isEmpty()) {
				logger.info("File url is {}", fileUrl);
				return new ResponseEntity<>(
						new ResponseDto(ResponseMessage.SUCCESS_API_CODE, ResponseMessage.SUCCESS_API_MSG, fileUrl),
						HttpStatus.OK);
			} else {
				logger.info("No fileUrl data");
				return new ResponseEntity<>(
						new ResponseDto(ResponseMessage.ERROR_CODE, ResponseMessage.ERROR_CODE_MESSAGE, null),
						HttpStatus.OK);
			}
		} catch (Exception e) {
			logger.info("Error while uploadFile : UploadAwsFileServiceImpl");
			logger.info(e.getMessage(), e);
			return new ResponseEntity<>(
					new ResponseDto(ResponseMessage.ERROR_CODE, ResponseMessage.ERROR_CODE_MESSAGE, null),
					HttpStatus.OK);

		}
	}

	@Override
	public ResponseEntity<?> downloadFile(DownloadFileReqDto downloadFileReqDto) {
		logger.info("Inside UploadAwsFileServiceImpl :: Inside downloadfile");
		try {
			byte[] content = null;
			logger.info("Inside downloadFile : file name is {}", downloadFileReqDto.getFileName());
			S3Object s3object = awsS3Client.downloadFile(downloadFileReqDto.getFileName());
			final S3ObjectInputStream stream = s3object.getObjectContent();
			content = IOUtils.toByteArray(stream);
			s3object.close();
		logger.info("File downloaded successfully");
			return new ResponseEntity<>(
					new ResponseDto(ResponseMessage.SUCCESS_API_CODE, ResponseMessage.SUCCESS_API_MSG, content),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.info("Error while downloadfile");
			logger.info(e.getMessage(), e);
			return new ResponseEntity<>(
					new ResponseDto(ResponseMessage.ERROR_CODE, ResponseMessage.ERROR_CODE_MESSAGE, null),
					HttpStatus.OK);
		}
	}
}
