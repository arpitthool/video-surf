package com.arpitthool.app.videosurf.controller;

import com.arpitthool.app.videosurf.dto.UploadVideoResponse;
import com.arpitthool.app.videosurf.dto.VideoDto;
import com.arpitthool.app.videosurf.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController // tells spring boot to make this a REST API
@RequestMapping("/api/videos") // tell spring boot where to put this API
@RequiredArgsConstructor
public class VideoController {

    /*
    API endpoint for uploading video file
     */
    private final VideoService videoService;

    @PostMapping // this accepts post request
    @ResponseStatus(HttpStatus.CREATED) // on success sends CREATED status code
    public UploadVideoResponse uploadVideo(@RequestParam("file")MultipartFile file) {
        return videoService.uploadVideo(file);
    }

    /*
    API endpoint for uploading thumbnail file
     */
    @PostMapping("/thumbnail") // this accepts post request
    @ResponseStatus(HttpStatus.CREATED) // on success sends CREATED status code
    public String uploadThumbnail(@RequestParam("file") MultipartFile file,
                                @RequestParam("videoId") String videoId ) {
        return videoService.uploadThumbnail(file, videoId);
    }

    /*
    API endpoint for editing video
     */
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public VideoDto editVideoMetadata(@RequestBody VideoDto videoDto) {
        return videoService.editVideo(videoDto);
    }

    /*
    API endpoint to fetch video
     */
    @GetMapping("/{videoId}")
    @ResponseStatus(HttpStatus.OK)
    public VideoDto getVideoDetails(@PathVariable String videoId) {
        return videoService.getVideoDetails(videoId);
    }
}
