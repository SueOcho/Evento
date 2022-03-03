package com.eventus.eventus.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;

import java.io.File;
import java.util.List;

public class CargarArchivos {

    public static void main(String[] args) {


    //Esta instancia nos permite setear credenciales
    AWSCredentials credentials = new BasicAWSCredentials("AKIA46CZA5EPN5SFTZEW",
            "tOZqffCkJLOv4U+6203+kJCZrIrj4YG+74Yqw1/8");

    //Esta instancia permite crear un cliente con la credencial anterior
    AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
            .withRegion(Regions.US_EAST_1)
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .build();

    //Listar todos los buckets
    List<Bucket> buckets = s3Client.listBuckets();
//        buckets.forEach(System.out::println);
        System.out.println("La cantidad de bucket es :"+buckets.size());
    }

    File sourceFile = new File("01-aws-java-s3/src/main/resources/input/Hola.txt");
    String s3Object = "email/Message.txt";
        s3Client.putObject(BUCKET_NAME, s3Object, sourceFile);
        System.out.println("Se subió el archivo '"+s3Object+"' al bucket '"+BUCKET_NAME+"' ");
}
