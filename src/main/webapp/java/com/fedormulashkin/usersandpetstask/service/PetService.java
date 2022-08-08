package com.fedormulashkin.usersandpetstask.service;

import com.fedormulashkin.usersandpetstask.entity.Pet;

import java.util.List;

public interface PetService {
    List<Pet> findAllPets();
    Pet findPetById(int id);
    void savePet(Pet pet);
    void deletePetById(int id);
    List<Pet> findAllUserPets(int id);
    void savePetToUser(Pet pet, String username);
    void deleteUserPetById(int petId, String username);
    void updatePet(Pet pet, String username);
}
