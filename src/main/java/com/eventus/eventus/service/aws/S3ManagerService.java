package com.eventus.eventus.service.aws;


import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.eventus.eventus.model.FileS3;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class S3ManagerService {

    private static final String TABLE_NAME = "User";

    private final S3Manager s3Manager;

    public S3ManagerService(S3Manager s3Manager ){
        this.s3Manager = s3Manager;
    }

    public boolean uploadFileToS3(MultipartFile file) {

        if (file.isEmpty()){
            throw new RuntimeException("El file esta vacio");
        }

        try(InputStream inputStream = file.getInputStream()) {
            String originalFilename = file.getOriginalFilename();
            return s3Manager.uploadObjectV2("flicker-cats", inputStream, originalFilename);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public List<FileS3> getFileFromS3() {
        List<FileS3> s3List = new ArrayList<>();
        List<S3ObjectSummary> summaryList = s3Manager.getObjectFromBucket("flicker-cats");
        summaryList.forEach(s -> {
            FileS3 fileS3 = new FileS3("https://flicker-cats.s3.amazonaws.com/".concat(s.getKey()), s.getKey());
            s3List.add(fileS3);
        });
        return s3List;
    }

//    public void saveUser(User user) {
//        Table table = dynamoDB.getTable(TABLE_NAME);
//        Item item = new Item()
//                .withPrimaryKey("userID", UUID.randomUUID().toString())
//                .withString("name", user.getFirstName())
//                .withString("lastName", user.getLastName())
//                .withNumber("age", user.getAge())
//                .withString("email", user.getEmail())
//                .withString("phoneNumber", user.getMobile());
//
//        table.putItem(item);
//        System.out.println("Se registro satisfactoriamente el usuario con uuid '"+user.getUserId()+"'");
//    }
}
