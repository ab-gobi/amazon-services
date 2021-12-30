package com.aws.amazonservices.amazonservices.controller;

import com.aws.amazonservices.amazonservices.service.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class S3Controller {

	@Autowired
	private S3Service storageService;
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam(value="file") MultipartFile file) {
		return new ResponseEntity<String>(storageService.uploadFile(file), HttpStatus.OK);
	}
	
	@GetMapping("/download/{fileName}")
	public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) {
		System.out.println("Inside controller");
		byte[] data = storageService.downloadFile(fileName);
		ByteArrayResource resource = new ByteArrayResource(data);
		return ResponseEntity.ok().contentLength(data.length)
				.header("Content-type","application/octet-stream")
				.header("Content-disposition", "attachment; filename=\"" +fileName + "\"")
				.body(resource);
	}
	
	@DeleteMapping("delete/{fileName}")
	public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
		return new ResponseEntity<String>(storageService.deleteFile(fileName), HttpStatus.ACCEPTED);
	}
	
}
