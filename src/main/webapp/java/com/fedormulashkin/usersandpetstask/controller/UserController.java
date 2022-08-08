package com.fedormulashkin.usersandpetstask.controller;

import com.fedormulashkin.usersandpetstask.entity.Pet;
import com.fedormulashkin.usersandpetstask.entity.User;
import com.fedormulashkin.usersandpetstask.service.PetService;
import com.fedormulashkin.usersandpetstask.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Контроллер для различных действий авторизованного пользователя
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final PetService petService;

    public UserController(UserService userService, PetService petService) {
        this.userService = userService;
        this.petService = petService;
    }

    @GetMapping("/user")
    public User userData(Principal principal) {
        return (User) userService.loadUserByUsername(principal.getName());
    }

    @GetMapping("/allPets")
    public List<Pet> showAllPets() {
        return petService.findAllPets();
    }

    @GetMapping("/user/pets")
    public List<Pet> showAllUserPets(Principal principal) {
        return userService.findAllUserPets(principal.getName());
    }


    @GetMapping("/user/pets/{petId}")
    public Pet showPetById(@PathVariable int petId) {
        return petService.findPetById(petId);
    }

    @PostMapping("/user/pets")
    public void postPet(@RequestBody Pet pet, Principal principal) {
        petService.savePetToUser(pet, principal.getName());
    }

    @PutMapping("/user/pets/")
    public Pet updatePet(@RequestBody Pet pet, Principal principal) {
        petService.updatePet(pet, principal.getName());
        return pet;
    }

    @DeleteMapping("/user/pets/{petId}")
    public void deletePetById(@PathVariable int petId, Principal principal) {
        petService.deleteUserPetById(petId, principal.getName());
    }
}
