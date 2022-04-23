package com.eventus.eventus.service.aws;


import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.eventus.eventus.model.FileS3;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class S3ManagerService {

    private static final String TABLE_NAME = "User";

    private final S3Manager s3Manager;

    public S3ManagerService(S3Manager s3Manager) {
        this.s3Manager = s3Manager;
    }

    public boolean uploadFileToS3(MultipartFile file) {

        if (file.isEmpty()) {
            throw new RuntimeException("El file esta vacio");
        } else {
            try {
                File sourceFile = new File("src/main/resources/static/fotoEventos/" + file.getOriginalFilename());
                sourceFile.createNewFile();

                try(FileOutputStream fout = new FileOutputStream(sourceFile)){
                    fout.write(file.getBytes());
                }catch (Exception e){
                    e.printStackTrace();
                }
                return true;
            } catch (Exception e) {
                e.getMessage();
            }
        return true;

        }
    }
}
