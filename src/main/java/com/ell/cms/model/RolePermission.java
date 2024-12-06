package com.ell.cms.model;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;

import lombok.Data;

@Data
@Table("t_role_permission")
public class RolePermission {
    @Id(keyType = KeyType.Auto)
    private Long id;
    private Long roleId;
    private Long permissionId;
}