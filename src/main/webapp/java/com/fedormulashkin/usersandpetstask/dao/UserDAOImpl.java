package com.fedormulashkin.usersandpetstask.dao;

import com.fedormulashkin.usersandpetstask.entity.Pet;
import com.fedormulashkin.usersandpetstask.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<User> findAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User findUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    @Override
    public User findByUsername(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("from User where username=:name").setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public void saveUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    public void deleteUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
    }

    @Override
    public void deleteUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("delete from User where userId=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public List<Pet> findAllUserPets(String name) {
        Session session = sessionFactory.getCurrentSession();
        User user = findByUsername(name);
        return user.getPets();
    }
}
