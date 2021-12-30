package com.aws.amazonservices.amazonservices.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;




@Service
public class S3Service {
	
	@Value("${application.bucket.name}")
	private String bucketName;
	
	@Autowired
	private AmazonS3 s3Client;

	
	public String uploadFile(MultipartFile file) {
		String fileName = System.currentTimeMillis()+"_"+file.getOriginalFilename();
		s3Client.putObject(bucketName, fileName, convertMultipartFileToFile(file));
		return "File "+fileName+" Uploaded";
	}
	
	public File convertMultipartFileToFile(MultipartFile file) {
		File convertedFile = new File(file.getOriginalFilename());
		try(FileOutputStream fos = new FileOutputStream(convertedFile)){
			fos.write(file.getBytes());
		}catch(IOException e) {
			e.printStackTrace();
		}
		return convertedFile;
	}
	
	public byte[] downloadFile(String fileName) {
		S3Object s3Obj = s3Client.getObject(bucketName, fileName);
		S3ObjectInputStream s3ios = s3Obj.getObjectContent();
		try {
			byte[] content = IOUtils.toByteArray(s3ios);
			return content;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String deleteFile(String fileName) {
		s3Client.deleteObject(bucketName, fileName);
		return "File "+fileName+" Deleted!!";
	}
	
}
