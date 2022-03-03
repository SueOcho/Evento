package com.eventus.eventus.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.eventus.eventus.service.aws.S3Manager;
import com.eventus.eventus.service.aws.S3ManagerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class EventusConfig {

    AWSCredentials credentials = new BasicAWSCredentials("AKIA46CZA5EPN5SFTZEW",
            "tOZqffCkJLOv4U+6203+kJCZrIrj4YG+74Yqw1/8");

    @Bean
    public S3Manager getS3Manager(){

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.US_EAST_1)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();

        return new S3ManagerImpl(s3Client);
    }


}
