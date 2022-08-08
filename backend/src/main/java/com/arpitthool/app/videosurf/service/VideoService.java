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
        // get video
        var savedVideo = getVideoById(videoDto.getId());

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

    public String uploadThumbnail(MultipartFile file, String videoId) {
        // get video from db
        var savedVideo = getVideoById(videoId);

        // save thumbnail file to s3 bucket and get url for file
        String thumbnailUrl = s3Service.uploadFile(file);

        // save thumbnail url for the video in db
        savedVideo.setThumbnailUrl(thumbnailUrl);

        // save modified video to db
        videoRepository.save(savedVideo);

        return thumbnailUrl;
    }

    public Video getVideoById(String videoId) {
        // find video by id
        return videoRepository.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find video by id : " + videoId));

    }
}
