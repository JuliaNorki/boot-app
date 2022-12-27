package com.example.bootapp.config.dao;

import com.example.bootapp.config.model.User;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;


    public List<User> getUserList() {
        return entityManager.createQuery("select user from User user", User.class).getResultList();

    }

    @Override
    public User getUser(int id) {
        return entityManager.find(User.class, id);

    }

    @Override
    public void save(User user) {
        entityManager.persist(user);

    }

    @Override
    public void updateUser(int id, User user) {
        User updateUser = getUser(id);
        updateUser.setName(user.getName());
        updateUser.setLastname(user.getLastname());
        updateUser.setAge(user.getAge());
      //  updateUser.setAge(user.getAge());
        entityManager.merge(updateUser);
    }

    @Override
    public void deleteUser(int id) {
        entityManager.remove(getUser(id));
    }

}
