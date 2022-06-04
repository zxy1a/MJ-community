package com.majiang.community.model;

import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Component
public class Question {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer commentCount;
    private Integer viewCount;
    private Integer likeCount;

}
