package com.beatriz.petstore.interfaces;


import com.beatriz.petstore.application.PetService;
import com.beatriz.petstore.domain.Pet;
import com.beatriz.petstore.domain.StatusType;
import com.beatriz.petstore.interfaces.json.request.PetPostRequest;
import com.beatriz.petstore.interfaces.json.request.PetPutRequest;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pets")
public class PetController {

  private final PetService petService = new PetService();

  @PostMapping
  ResponseEntity<Pet> post(@RequestBody @Validated PetPostRequest petRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(
        petService.create(petRequest)
    );
  }

  @PutMapping("{id}")
  ResponseEntity<Pet> updatePet(@RequestBody @Validated PetPutRequest petPutRequest, @PathVariable Integer id){
    return ResponseEntity.status(HttpStatus.OK).body(
        petService.updatePet(petPutRequest, id)
    );
  }

  @GetMapping("{id}")
  ResponseEntity<Pet> getById(@PathVariable Integer id){
    return ResponseEntity.status(HttpStatus.OK).body(
        petService.getPetById(id)
    );
  }

  @GetMapping
  ResponseEntity<List<Pet>> getAllPets(){
    return ResponseEntity.status(HttpStatus.OK).body(
        petService.getAllPets()
    );
  }

  @GetMapping("/byStatus/{status}")
  ResponseEntity<List<Pet>> getAllPetsByStatus(@PathVariable String status){
    return ResponseEntity.status(HttpStatus.OK).body(
        petService.getAllPetsByStatus(StatusType.valueOf(status.toUpperCase()))
    );
  }

  @DeleteMapping("{id}")
  ResponseEntity<Pet> deletePet(@PathVariable Integer id){
    petService.deletePet(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
