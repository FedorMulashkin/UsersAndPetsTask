package com.fedormulashkin.usersandpetstask.entity;

import com.fedormulashkin.usersandpetstask.exceptionhandling.exceptions.PetNotFoundException;

import javax.persistence.*;
import java.sql.Date;
/**
 * Класс Pet предназначен для отображения строк из базы данных pets.
 * Этот класс связан с классом User.
 * При создании объекта класса Pet и добавлении новой строки в таблицу
 * pets производится проверка на доступность вида животного (см. массив kinds)
 * */
@Entity
@Table(name = "pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "petId")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "kind")
    private String kind;
    @Column(name = "birthday")
    private Date birthday;
    @Column(name = "sex")
    private char sex;
    @JoinColumn(name = "userId")
    private int userId;

    private static String[] kinds = {"cat", "dog", "fish"};


    public Pet() {
    }

    public Pet(String name, String kind, Date birthday, char sex, int userId_fk) {
        this.name = name;
        this.kind = kind;
        this.birthday = birthday;
        this.sex = sex;
        this.userId = userId_fk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        boolean checkKind = false;
        for (String s : kinds) {
            if(s.equals(kind)){
                checkKind = true;
                break;
            }
        }
        if(checkKind){
            this.kind = kind;
        } else {
            throw new PetNotFoundException("Unavailable kind of pet: " + kind);
        }

    }

    public String getBirthday() {
        return birthday.toString();
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pet pet = (Pet) o;

        if (getSex() != pet.getSex()) return false;
        if (getName() != null ? !getName().equals(pet.getName()) : pet.getName() != null) return false;
        if (getKind() != null ? !getKind().equals(pet.getKind()) : pet.getKind() != null) return false;
        return getBirthday() != null ? getBirthday().equals(pet.getBirthday()) : pet.getBirthday() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getKind() != null ? getKind().hashCode() : 0);
        result = 31 * result + (getBirthday() != null ? getBirthday().hashCode() : 0);
        result = 31 * result + (int) getSex();
        return result;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", kind='" + kind + '\'' +
                ", birthday=" + birthday +
                ", sex=" + sex +
                ", userId=" + userId +
                '}';
    }
}
