package com.ell.cms.model;

import java.math.BigDecimal;
import java.util.Date;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import lombok.Data;

@Data
@Table("t_user")
public class User {
    @Id(keyType = KeyType.Auto)
    private Long id;
    private String nickname;
    private String mobile;
    private BigDecimal balance;
    private Date createdAt;
    private Date updatedAt;
    @Column(isLogicDelete = true)
    private Boolean isDeleted;
    @Column(version = true, onUpdateValue = "version + 1")
    private Long version;
}