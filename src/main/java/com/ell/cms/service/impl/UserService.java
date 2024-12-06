package com.ell.cms.service.impl;

import org.springframework.stereotype.Service;

import com.ell.cms.mapper.UserMapper;
import com.ell.cms.model.User;
import com.ell.cms.service.IUserService;
import com.mybatisflex.spring.service.impl.ServiceImpl;

@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IUserService {

}
