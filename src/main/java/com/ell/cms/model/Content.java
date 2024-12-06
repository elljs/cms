package com.ell.cms.model;

import java.time.LocalDateTime;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import lombok.Data;

@Data
@Table("t_content")
public class Content {
    @Id(keyType = KeyType.Auto)
    private Long id;
    private Long categoryId;
    private String title;
    private String content;
    private Boolean isPublished;
    private LocalDateTime publishedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Column(isLogicDelete = true)
    private Boolean isDeleted;
}