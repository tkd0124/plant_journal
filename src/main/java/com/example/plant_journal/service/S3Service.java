package com.example.plant_journal.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Client s3Client;
    private final String bucketName = System.getenv("AWS_S3_BUCKET");

    public String uploadFile(MultipartFile file) throws IOException {
        // ユニークなファイル名を生成
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        // S3 にアップロード
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .contentType(file.getContentType())
                .build();

        PutObjectResponse response = s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.getBytes()));

        if (response.sdkHttpResponse().isSuccessful()) {
            return "https://" + bucketName + ".s3.amazonaws.com/" + fileName;
        } else {
            throw new RuntimeException("S3へのアップロードに失敗しました");
        }
    }
}
