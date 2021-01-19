package kr.ac.skuniv.cosmos.infra;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class S3Uploader {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.dirName}")
    private String dirName;

    public String uploadFile(MultipartFile multipartFile, String fileName) {

        if (StringUtils.isEmpty(multipartFile.getOriginalFilename())) {
            throw new IllegalArgumentException("올바르지 않은 파일입니다");
        }

        try {
            fileName = dirName + "/" + fileName;

            return putS3(multipartFile.getInputStream(), fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> uploadFiles(List<MultipartFile> multipartFiles) {
        List<String> putUris = new ArrayList<>();

        multipartFiles.stream()
                .filter(multipartFile -> !StringUtils.isEmpty(multipartFile.getOriginalFilename()))
                .forEach(multipartFile -> {
                    try {
                        String fileName = dirName + "/" + UUID.randomUUID().toString();
                        putUris.add(putS3(multipartFile.getInputStream(), fileName));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        return putUris;
    }

    private String putS3(InputStream inputStream, String fileName) throws IOException {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        byte[] bytes = IOUtils.toByteArray(inputStream);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        objectMetadata.setContentType("text/plain");
        objectMetadata.setContentLength(bytes.length);
        objectMetadata.setContentDisposition(fileName);

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, fileName, byteArrayInputStream, objectMetadata);
        putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
        amazonS3Client.putObject(putObjectRequest);

        System.out.println(amazonS3Client.getUrl(bucket, fileName).toString());
        return amazonS3Client.getUrl(bucket, fileName).toString();
    }

}
