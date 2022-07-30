package com.arpitthool.app.videosurf.controller;

import com.arpitthool.app.videosurf.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController // tells spring boot to make this a REST API
@RequestMapping("/api/videos") // tell spring boot where to put this API
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;

    @PostMapping // this accepts post request
    @ResponseStatus(HttpStatus.CREATED) // on success sends CREATED status code
    public void uploadVideo(@RequestParam("file")MultipartFile file) {
        videoService.uploadVideo(file);
    }
}
