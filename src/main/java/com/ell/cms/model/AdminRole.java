package com.ell.cms.model;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import lombok.Data;

@Data
@Table("t_admin_role")
public class AdminRole {
    @Id(keyType = KeyType.Auto)
    private Long id;
    private Long adminId;
    private Long roleId;
}