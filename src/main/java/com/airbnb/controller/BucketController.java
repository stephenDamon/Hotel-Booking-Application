package com.airbnb.controller;

import com.airbnb.service.S3Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/airbnb/v1/S3Bucket")
public class BucketController {
    @Value("${aws.s3.bucket}")
    private String bucketName;
    @Value("${aws.s3.access-key}")
    private String keyName;

    private S3Service s3Service;

    public BucketController(S3Service s3Service) {
        this.s3Service = s3Service;
    }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String url = s3Service.uploadFile(bucketName, keyName, file);
            return new ResponseEntity<>(url, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to upload file",HttpStatus.OK) ;
        }
    }
}
