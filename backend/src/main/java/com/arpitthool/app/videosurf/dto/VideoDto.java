package com.arpitthool.app.videosurf.dto;

import com.arpitthool.app.videosurf.model.Comment;
import com.arpitthool.app.videosurf.model.VideoStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoDto {
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
