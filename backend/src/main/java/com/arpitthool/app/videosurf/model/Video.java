package com.arpitthool.app.videosurf.model;

import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Set;

public class Video {
    @Id
    private String id;
    private String title;
    private String description;
    private String userId;
    private String likes;
    private String dislikes;
    private Set<String> tags;
    private String videoUrl;
    private VideoStatus videoStatus;
    private Integer viewCount;
    private String thumbnailUrl;
    private List<Comment> commentList;

}
