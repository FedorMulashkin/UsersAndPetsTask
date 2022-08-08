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
public class PetDAOImpl implements PetDao{
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Pet> findAllPets() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Pet", Pet.class).getResultList();
    }

    @Override
    public Pet findPetById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Pet.class, id);
    }

    @Override
    public void savePet(Pet pet) {
        Session session = sessionFactory.getCurrentSession();
        session.save(pet);
        if(pet.getUserId() != 0){
            User user = session.get(User.class, pet.getUserId());
            user.addPetToUser(pet);
        }
    }

    @Override
    public void deletePetById(int petId) {
        Session session = sessionFactory.getCurrentSession();
        Query<Pet> query = session.createQuery("delete from Pet where id=:petId");
        query.setParameter("petId", petId);
        query.executeUpdate();
    }
    @Override
    public List<Pet> findAllUserPets(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Pet> query = session.createQuery("FROM Pet WHERE userId=:id");
        query.setParameter("id", id);
        List<Pet> petList = query.getResultList();
        return petList;
    }

    @Override
    public void updatePet(Pet pet) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(pet);
    }
}
