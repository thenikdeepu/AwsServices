package com.aws.file.aws.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Component
public class AwsS3Client {

	private static final Logger logger = LoggerFactory.getLogger(AwsS3Client.class);

	private AmazonS3 s3client;

	@Value("${amazonProperties.endpointUrl}")
	private String endpointUrl;

	@Value("${amazonProperties.bucketName}")
	private String bucketName;

	@Value("${amazonProperties.accessKey}")
	private String accessKey;

	@Value("${amazonProperties.secretKey}")
	private String secretKey;

	@PostConstruct
	private void initializeAmazon() {
		AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
		this.s3client = new AmazonS3Client(credentials);
	}

	private void uploadFileTos3bucket(String fileName, File file) {
		s3client.putObject(
				new PutObjectRequest(bucketName, fileName, file).withCannedAcl(CannedAccessControlList.PublicRead));
	}

	public String uploadAwsFile(MultipartFile file) {
		logger.info("Inside AwsS3Client :: Inside uploadAwsFile");
		String fileUrl = "";
		try {
			File files = convertMultiPartToFile(file);
			String fileName = generateFileName(file);
			fileUrl = endpointUrl + "/" + bucketName + "/" + fileName;
			logger.info("File url is {}", fileUrl);
			uploadFileTos3bucket(fileName, files);
		//	URL s3Url = s3client.getUrl(bucketName, fileName);
			logger.info("File uploaded successfully");
			files.delete();
			return fileUrl;
		} catch (Exception e) {
			logger.info("Error while uploadAwsFile");
			logger.info(e.getMessage(), e);
			return null;
		}

	}

	private String generateFileName(MultipartFile multiPart) {
		return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
	}

	private File convertMultiPartToFile(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

}
