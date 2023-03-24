package com.aws.file.aws.uploadFile.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;


public class DownloadFileReqDto {

	@NotNull(message="FileName can not be null.",groups=Default.class)
	@NotBlank(message="FileName can not be blank.",groups=Default.class)
	@NotEmpty(message="FileName can not be empty.",groups=Default.class)
	@Pattern(regexp = "^[A-za-z0-9.]{1,255}$",message="Invalid file name.",groups=Default.class)
	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
