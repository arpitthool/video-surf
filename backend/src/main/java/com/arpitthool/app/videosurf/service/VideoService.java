package com.arpitthool.app.videosurf.service;

import com.arpitthool.app.videosurf.dto.VideoDto;
import com.arpitthool.app.videosurf.model.Video;
import com.arpitthool.app.videosurf.repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final S3Service s3Service;
    private final VideoRepository videoRepository;

    public void uploadVideo(MultipartFile multipartFile) {
        // upload file to s3 bucket
        String videoUrl = s3Service.uploadFile(multipartFile);

        // create new video object
        var video = new Video();
        video.setVideoUrl(videoUrl);

        // save video to mongodb database
        videoRepository.save(video);
    }

    public VideoDto editVideo(VideoDto videoDto) {
        // find video by id
        Video savedVideo = videoRepository.findById(videoDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Cannot find video by id : " + videoDto.getId()));
        // map videoDto fields to Video
        savedVideo.setThumbnailUrl(videoDto.getThumbnailUrl());
        savedVideo.setVideoStatus(videoDto.getVideoStatus());
        savedVideo.setTags(videoDto.getTags());
        savedVideo.setDescription(videoDto.getDislikes());
        savedVideo.setTitle(videoDto.getTitle());
        // save the video to database
        videoRepository.save(savedVideo);
        return videoDto;
    }
}
