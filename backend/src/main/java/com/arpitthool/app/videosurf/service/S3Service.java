package com.arpitthool.app.videosurf.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service implements FileService {

    private final AmazonS3Client amazonS3Client;
    public static final String AWS_S3_BUCKET_NAME = "videosurf";

    // upload file to s3
    @Override
    public String uploadFile(MultipartFile file) {
        // get file extension
        var fileNameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());

        // create a unique key string for the file being uploaded
        var key = UUID.randomUUID().toString() + "." + fileNameExtension;

        // we need metadata for s3 object
        var metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        // upload file to s3
        try {
            amazonS3Client.putObject(AWS_S3_BUCKET_NAME, key, file.getInputStream(), metadata);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An exception occurred while uploading the file");
        }

        // publicly expose s3 file
        amazonS3Client.setObjectAcl(AWS_S3_BUCKET_NAME, key, CannedAccessControlList.PublicRead);

        // return file's public url
        return amazonS3Client.getResourceUrl(AWS_S3_BUCKET_NAME, key);
    }
}
