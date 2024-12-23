package com.sap.Conversession.services;

import java.util.List;

import com.sap.Conversession.model.User;

public interface UserService {
    public void register(User user);
    public User login(User user);
    public void logout(String email);
    public List<User> findAll();
    public void update(String email, User user);
    public boolean deleteByEmailAndPassword(String email, String password);
    public boolean updatePassword(String email, String oldPassword, String newPassword);
}
