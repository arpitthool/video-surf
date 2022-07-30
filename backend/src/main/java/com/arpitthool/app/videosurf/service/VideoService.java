package com.arpitthool.app.videosurf.service;

import com.arpitthool.app.videosurf.model.Video;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final S3Service s3Service;

    public void uploadVideo(MultipartFile multipartFile) {
        // upload file to s3 bucket
        String videoUrl = s3Service.uploadFile(multipartFile);

        // create new video object
        var video = new Video();
        video.setVideoUrl(videoUrl);
    }
}
