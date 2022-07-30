package com.arpitthool.app.videosurf.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController // tells spring boot to make this a REST API
@RequestMapping("/api/videos") // tell spring boot where to put this API
public class VideoController {

    @PostMapping // this accepts post request
    @ResponseStatus(HttpStatus.CREATED) // on success sends CREATED status code
    public void uploadVideo(@RequestParam("file")MultipartFile file) {

    }
}
