package com.example.bootapp.config.dao;


import com.example.bootapp.config.model.User;

import java.util.List;

public interface UserDAO {
    public List<User> getUserList();

    User getUser(int id);

    void save(User user);

    void updateUser(int id, User user);

    void deleteUser(int id);


}
