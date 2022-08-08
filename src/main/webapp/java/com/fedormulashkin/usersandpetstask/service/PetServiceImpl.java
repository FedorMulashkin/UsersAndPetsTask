package com.fedormulashkin.usersandpetstask.service;

import com.fedormulashkin.usersandpetstask.dao.PetDao;
import com.fedormulashkin.usersandpetstask.dao.UserDAO;
import com.fedormulashkin.usersandpetstask.entity.Pet;
import com.fedormulashkin.usersandpetstask.entity.User;
import com.fedormulashkin.usersandpetstask.exceptionhandling.exceptions.PetNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    private final PetDao petDao;
    private final UserDAO userDao;
    private final PetNotFoundException petNotFoundException = new PetNotFoundException("This pet doesn't exists");
    private final PetNotFoundException petBelongsToAnotherUserException = new PetNotFoundException("This pet belongs to another user");


    public PetServiceImpl(PetDao petDao, UserDAO userDao) {
        this.petDao = petDao;
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public List<Pet> findAllPets() {
        return petDao.findAllPets();
    }

    @Override
    @Transactional
    public Pet findPetById(int id) {
        Pet pet = petDao.findPetById(id);
        if (pet == null) {
            throw petNotFoundException;
        }
        return pet;
    }

    @Override
    @Transactional
    public void savePet(Pet pet) {
        petDao.savePet(pet);
    }

    @Override
    @Transactional
    public void deletePetById(int id) {
        findPetById(id); //Для обработки исключений
        petDao.deletePetById(id);
    }

    @Override
    @Transactional
    public List<Pet> findAllUserPets(int id) {
        return petDao.findAllUserPets(id);
    }

    @Override
    @Transactional
    public void savePetToUser(Pet pet, String username) {
        User user = userDao.findByUsername(username);
        pet.setUserId(user.getId());
        petDao.savePet(pet);
    }

    @Override
    @Transactional
    public void deleteUserPetById(int petId, String username) {
        List<Pet> pets = userDao.findAllUserPets(username);
        Pet pet = petDao.findPetById(petId);
        boolean notFound = true;
        for (Pet tempPet : pets) {
            if (tempPet.equals(pet)) {
                deletePetById(petId);
                notFound = false;
            }
        }
        if (notFound) {
            throw petBelongsToAnotherUserException;
        }
    }

    @Override
    @Transactional
    public void updatePet(Pet pet, String username) {
        User user = userDao.findByUsername(username);
        pet.setUserId(user.getId());
        List<Pet> pets = user.getPets();
        Pet prevPet = petDao.findPetById(pet.getId());
        boolean notFound = true;
        for (Pet tempPet : pets) {
            if (tempPet.equals(prevPet)) {
                petDao.updatePet(pet);
                notFound = false;
                break;
            }
        }
        if (notFound) {
            throw petBelongsToAnotherUserException;
        }
    }
}
