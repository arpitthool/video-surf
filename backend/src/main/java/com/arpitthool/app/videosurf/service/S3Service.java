package com.arpitthool.app.videosurf.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class S3Service implements FileService {

    @Override
    public String uploadFile(MultipartFile file) {
        // upload file to s3

        // get file
        // get file extension
        var fileNameExtension = StringUtils.getFilenameExtension(file.getOriginalFilename());

        // a unique key string for the file being uploaded
        var key = UUID.randomUUID().toString() + fileNameExtension;

        // we need metadata for s3 object
        var metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        return null;
    }
}
