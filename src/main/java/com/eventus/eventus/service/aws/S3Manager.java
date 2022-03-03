package com.eventus.eventus.service.aws;

import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.S3ObjectSummary;

import java.io.InputStream;
import java.util.List;

public interface S3Manager {

    List<Bucket> getAll();

    boolean createBucket(String bucketName);

    boolean uploadObject(String bucketName, String source, String target);

    boolean downloadObject(String bucketName, String source, String target);

    void deleteObject(String bucketName, String source);

    void deleteBucket(String bucketName);

    boolean uploadObjectV2(String bucketName, InputStream inputStream, String originalFilename);

    List<S3ObjectSummary> getObjectFromBucket(String bucketName);
}
