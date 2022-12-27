package com.example.bootapp.config.dao;

import com.example.bootapp.config.model.User;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository// Аннотация @Repository - это специализация аннотации @Component,
// которая используется для указания того, что класс предоставляет механизм для хранения,
// извлечения, обновления, удаления и поиска объектов. Хотя это специализация аннотации @Component,
// поэтому классы репозитория Spring автоматически определяются spring framework посредством сканирования пути
// к классам. Эта аннотация представляет собой аннотацию стереотипа общего назначения, которая очень близка
// к шаблону DAO, где классы DAO отвечают за обеспечение операций CRUD над таблицами базы данных.
public class UserDaoImpl implements UserDAO {
    //указывает на зависимость EntityManager в контейнере
    @PersistenceContext
    private EntityManager entityManager;// основной интерфуйс ORM служит для управления персистентными сущностями
    //


    public List<User> getUserList() {
        return entityManager.createQuery("select user from User user", User.class).getResultList();
        //createQuery() - создать объект Query для выполнения JPQL запроса.
    }

    @Override
    public User getUser(int id) {
        return entityManager.find(User.class, id);
        //find() либо JPQL запросами. В результате жадно загружены будут все не-lazy атрибуты представления.
        //Если в данный метод передать null, либо не вызывать его вообще, загрузка будет производиться в соответствие с правилами аннотаций сущностей.
        //Представления, явно переданные в метод find() или установленные в объекте Query имеют приоритет над установленным данным методом.
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
        //persist() - вводит новый экземпляр сущности в персистентный контекст.
        //При коммите транзакции командой SQL INSERT в БД будет создана соответствующая запись.
    }

    @Override
    public void updateUser(int id, User user) {
        User updateUser = getUser(id);
        updateUser.setName(user.getName());
        updateUser.setLastname(user.getLastname());
        updateUser.setAge(user.getAge());
      //  updateUser.setAge(user.getAge());
        entityManager.merge(updateUser);//- переносит состояние отсоединенного экземпляра сущности в персистентный контекст следующим образом:
        // из БД загружается экземпляр с тем же идентификатором, в него переносится состояние переданного Detached экземпляра и возвращается загруженный Managed экземпляр.
        // Далее надо работать именно с возвращенным Managed экземпляром.
        // При коммите транзакции командой SQL UPDATE в БД будет сохранено состояние данного экземпляра.
    }

    @Override
    public void deleteUser(int id) {
        entityManager.remove(getUser(id));
    }

}
