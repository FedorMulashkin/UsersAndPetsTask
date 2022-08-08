package com.fedormulashkin.usersandpetstask.dao;

import com.fedormulashkin.usersandpetstask.entity.Pet;

import java.util.List;

public interface PetDao {
    List<Pet> findAllPets();
    Pet findPetById(int id);
    void savePet(Pet pet);
    void deletePetById(int id);
    List<Pet> findAllUserPets(int id);
    void updatePet(Pet pet);
}
