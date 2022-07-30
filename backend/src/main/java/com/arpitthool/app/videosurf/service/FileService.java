package com.arpitthool.app.videosurf.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    // uploads file to cloud and return URL
    public String uploadFile(MultipartFile file);
}
