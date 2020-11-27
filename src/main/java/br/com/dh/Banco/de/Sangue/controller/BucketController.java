package br.com.dh.Banco.de.Sangue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.dh.Banco.de.Sangue.service.AmazonClient;


@CrossOrigin
@RestController
@RequestMapping("/storage")
public class BucketController {

	    private AmazonClient amazonClient;

	    @Autowired
	    BucketController(AmazonClient amazonClient) {
	        this.amazonClient = amazonClient;
	    }

	    @PostMapping("/upload")
	    public String uploadFile(@RequestPart(value = "file") MultipartFile file) {
	        return this.amazonClient.uploadFile(file);
	    }

	    @DeleteMapping("/delete")
	    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
	        return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
	    }
}