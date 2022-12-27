package com.example.bootapp.config.model;

import javax.persistence.*;
// создание POJO класса
@Entity //Сущность (entity) - это объект персистентной области. Как правило, сущность
//представляет таблицу в реляционной базе данных, и каждый экземпляр сущности
       // соответствует строке в этой таблице.
//Указывает, что данный бин (класс) является сущностью.
@Table(name ="users")//указывает на имя таблицы, которая будет отображаться в этой сущности.
public class User {

    @Id// уникальный ключ в базе данных
    @GeneratedValue(strategy = GenerationType.IDENTITY) //указывает, что данное свойство будет создаваться согласно указанной стратегии.
    @Column(name = "id")//указывает на имя колонки, которая отображается в свойство сущности.
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "age")
    private int age;

    public User() {
    }

    public User(int id, String name, String lastname, int age) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public int getAge() {
        return age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", age=" + age +
                '}';
    }



}
