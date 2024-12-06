package com.ell.cms.model;

import java.time.LocalDateTime;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import lombok.Data;

@Data
@Table("t_category")
public class Category {
    @Id(keyType = KeyType.Auto)
    private Long id;
    private Long parentId;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Column(isLogicDelete = true)
    private Boolean isDeleted;
}