package com.beatriz.petstore.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Pet {

  private Integer id;

  private String name;

  private StatusType status;

  private String age;

  private PetType type;

  public Pet(Integer id, String name, String age, PetType type){
    this.id = id;
    this.name = name;
    this.status = StatusType.AVAILABLE;
    this.age = age;
    this.type = type;
  }

}
