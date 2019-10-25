package com.ycz.service;

import com.ycz.dao.UserMapper;
import com.ycz.pojo.User;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectAllUsers() {

        return userMapper.queryAllUsers();
    }

    @Override
    public Integer insertUser(User user) {
        user.setTimes(new Date());
        String salt = UUID.randomUUID().toString();
        String newPwd = new Sha256Hash(user.getPassword(), salt, 10000).toBase64();
        user.setSalt(salt);
        user.setPassword(newPwd);
        return userMapper.insertUser(user);
    }

    @Override
    public User queryUserByUsername(String username) {

        return userMapper.queryUserByUsername(username);
    }

    @Override
    public User queryUserByEmail(String email) {

        return userMapper.queryUserByEmail(email);
    }
}
