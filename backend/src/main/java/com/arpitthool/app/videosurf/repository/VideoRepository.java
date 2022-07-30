package com.arpitthool.app.videosurf.repository;

import com.arpitthool.app.videosurf.model.Video;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VideoRepository extends MongoRepository<Video, String> {
}
