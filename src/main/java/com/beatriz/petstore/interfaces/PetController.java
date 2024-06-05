package com.beatriz.petstore.interfaces;

import com.beatriz.petstore.application.PetService;
import com.beatriz.petstore.domain.Pet;
import com.beatriz.petstore.interfaces.json.request.PetPostRequest;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  @GetMapping
  ResponseEntity<Pet> getById(@RequestParam Integer id){
    return ResponseEntity.status(HttpStatus.OK).body(
        petService.getPetById(id)
    );
  }

  @GetMapping("/all")
  ResponseEntity<List<Pet>> getAllPets(){
    return ResponseEntity.status(HttpStatus.OK).body(
        petService.getAllPets()
    );
  }
}
