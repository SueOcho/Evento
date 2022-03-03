package com.eventus.eventus.service.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class S3ManagerImpl implements S3Manager{

    private final AmazonS3 s3Client;

    public S3ManagerImpl(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }

    @Override
    public List<Bucket> getAll() {
        List<Bucket> buckets = s3Client.listBuckets();
        System.out.println("La cantidad de bucket es :"+buckets.size());
        return buckets;
    }

    @Override
    public boolean createBucket(String bucketName) {

        if(s3Client.doesBucketExistV2(bucketName)){
            System.out.println("El bucket: "+bucketName+" ya existe");
            return false;
        }else {
            s3Client.createBucket(bucketName);
            System.out.println("El bucket: "+bucketName+" se ha creado");
            return true;
        }
    }

    @Override
    public boolean uploadObject(String bucketName, String source, String target) {
        try{
            File sourceFile = new File(source);
            s3Client.putObject(bucketName, target, sourceFile);
            System.out.println("Se subió el archivo '"+ target +"' al bucket '"+bucketName+"' ");
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean downloadObject(String bucketName, String source, String target) {
        S3Object object = s3Client.getObject(bucketName, source);
        System.out.println(object.getKey() +"si soy");
        return  true;
//        S3ObjectInputStream objectContent = object.getObjectContent();
//        File targetFile = new File(target);
//
//        try {
//            Files.copy(objectContent, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//            System.out.println("Se descargó el archivo '"+source+ "' a la ruta '"+targetFile.toPath()+"' ");
//            return true;
//        } catch (IOException e) {
//            e.printStackTrace();
//            return false;
//        }
    }

    @Override
    public void deleteObject(String bucketName, String source) {
        s3Client.deleteObject(bucketName, source);
        System.out.println("Se eliminó el archivo '"+source+ "' del bucket '"+bucketName+"' ");
    }

    @Override
    public void deleteBucket(String bucketName) {
        if(s3Client.doesBucketExistV2(bucketName)){
            s3Client.deleteBucket(bucketName);
            System.out.println("El bucket: "+bucketName+" se ha eliminado");
        }
    }

    @Override
    public boolean uploadObjectV2(String bucketName, InputStream inputStream, String originalFilename) {

        try {
            s3Client.putObject(bucketName, originalFilename, inputStream, new ObjectMetadata());
            System.out.println("Se subió el archivo '"+ originalFilename +"' al bucket '"+bucketName+"' ");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<S3ObjectSummary> getObjectFromBucket(String bucketName) {

        ObjectListing listing = s3Client.listObjects(bucketName);
        List<S3ObjectSummary> objectSummaries = listing.getObjectSummaries();

        while (listing.isTruncated()){
            listing = s3Client.listNextBatchOfObjects(listing);
            objectSummaries.addAll(listing.getObjectSummaries());
        }

        return objectSummaries;
    }

}
