package com.beatriz.petstore.application;

import com.beatriz.petstore.domain.Pet;
import com.beatriz.petstore.domain.StatusType;
import com.beatriz.petstore.interfaces.json.request.PetPostRequest;
import com.beatriz.petstore.interfaces.json.request.PetPutRequest;
import com.beatriz.petstore.interfaces.response.ImagesResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class PetService {

  private final ArrayList<Pet> petList = new ArrayList<>();

  public Pet create(PetPostRequest petRequest) {

    var petId = petList.size() + 1;
    var pet = new Pet(petId, petRequest.getName(), petRequest.getAge(), petRequest.getType());
    petList.add(pet);
    var teste = getImage();
    // validateDuplicatedPet(pet);
    return pet;
  }

  public Pet updatePet(PetPutRequest petPutRequest, Integer id) {

    var pet = getPetById(id);
    if (pet == null) {
      System.out.println("Pet not found");
    }
    pet.setName(petPutRequest.getName());
    pet.setStatus(petPutRequest.getStatus());
    return pet;
  }

  public void deletePet(Integer id) {
    var pet = getPetById(id);
    if (pet == null) {
      System.out.println("Pet not found");
      return;
    }
    petList.remove(pet);
  }

  public List<Pet> getAllPetsByStatus(StatusType status) {
    return petList.stream()
        .filter(pet -> pet.getStatus().equals(status))
        .collect(Collectors.toCollection(ArrayList::new));
  }

  public Pet getPetById(Integer id) {
    return petList.stream()
        .filter(pet -> pet.getId().equals(id))
        .findFirst()
        .orElse(null);
  }

  public ArrayList<Pet> getAllPets() {
    return petList;
  }

//TODO: implementar chamada para a API de imagens (random-animals-api)
  //todo como fazer o webclient seguir o redirect
  public Mono<ImagesResponse> getImage() {
    WebClient client = WebClient.create();
    return client.get()
        .uri("http://api.thecatapi.com/v1/images/search")
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .retrieve()
        .bodyToMono(ImagesResponse.class)
        .doOnError(e -> System.out.println("Error: " + e.getMessage()));
  }
}
