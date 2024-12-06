package com.ell.cms.model;

import java.time.LocalDateTime;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import lombok.Data;

@Data
@Table("t_admin")
public class Admin {
    @Id(keyType = KeyType.Auto)
    private Long id;
    private String email;
    private String password;
    private String nickname;
    private String avatar;
    private Boolean isActive;
    private Boolean isRoot;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @Column(isLogicDelete = true)
    private Boolean isDeleted;
}