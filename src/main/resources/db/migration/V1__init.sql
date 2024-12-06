CREATE TABLE IF NOT EXISTS `t_admin` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '管理员ID',
    `email` VARCHAR(50) NOT NULL UNIQUE COMMENT '邮箱',
    `password` VARCHAR(255) NOT NULL COMMENT '密码',
    `nickname` VARCHAR(255) COMMENT '昵称',
    `avatar` VARCHAR(255) COMMENT '头像',
    `is_active` TINYINT(1) NOT NULL DEFAULT 1 COMMENT '是否激活',
    `is_root` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否超管',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='管理员表';
 
CREATE TABLE IF NOT EXISTS `t_role` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '角色ID',
    `name` VARCHAR(50) NOT NULL UNIQUE COMMENT '角色名',
    `description` TEXT COMMENT '描述',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色表';

CREATE TABLE IF NOT EXISTS `t_admin_role` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    `admin_id` BIGINT NOT NULL COMMENT '管理员ID',
    `role_id` BIGINT NOT NULL COMMENT '角色ID',
    INDEX (`admin_id`),
    INDEX (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='管理员角色关系表';
 
CREATE TABLE IF NOT EXISTS `t_permission` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '权限ID',
    `name` VARCHAR(100) NOT NULL UNIQUE COMMENT '权限名',
    `description` TEXT COMMENT '描述',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='权限表';
 
CREATE TABLE IF NOT EXISTS `t_role_permission` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    `role_id` BIGINT NOT NULL COMMENT '角色ID',
    `permission_id` BIGINT NOT NULL COMMENT '权限ID',
    INDEX (`role_id`),
    INDEX (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色权限关系表';
 
CREATE TABLE IF NOT EXISTS `t_user` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    `mobile` VARCHAR(20) UNIQUE COMMENT '手机',
    `nickname` VARCHAR(255) COMMENT '昵称',
    `avatar` VARCHAR(255) COMMENT '头像',
    `balance` DECIMAL(10,2) NOT NULL DEFAULT 0 COMMENT '余额',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
    `version` BIGINT NOT NULL DEFAULT 0 COMMENT '乐观锁'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';
 
CREATE TABLE IF NOT EXISTS `t_category` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '目录ID',
    `parent_id` BIGINT DEFAULT NULL COMMENT '父目录ID',
    `name` VARCHAR(100) NOT NULL COMMENT '目录名',
    `description` TEXT COMMENT '描述',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
    INDEX (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='目录表';
 
CREATE TABLE IF NOT EXISTS `t_content` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '内容ID',
    `category_id` BIGINT NOT NULL COMMENT '目录ID',
    `title` VARCHAR(255) NOT NULL COMMENT '标题',
    `content` TEXT NOT NULL COMMENT '内容文本',
    `is_published` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否发布',
    `published_at` DATETIME COMMENT '发布时间',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
    INDEX (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='内容表';
