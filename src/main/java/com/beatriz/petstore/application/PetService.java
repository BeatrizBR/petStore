package com.beatriz.petstore.application;

import com.beatriz.petstore.domain.Pet;
import com.beatriz.petstore.interfaces.json.request.PetPostRequest;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

@Service
public class PetService {

  private final ArrayList<Pet> petList = new ArrayList<>();

  public Pet create(PetPostRequest petRequest) {

    var petId = petList.size() + 1;
    var pet = new Pet(petId, petRequest.getName(), petRequest.getAge(), petRequest.getType());
    petList.add(pet);
    // validateDuplicatedPet(pet);
    return pet;
  }

  public Pet getPetById(Integer id){
    return petList.stream()
        .filter(pet -> pet.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

  public ArrayList<Pet> getAllPets(){
    return petList;
  }
}
