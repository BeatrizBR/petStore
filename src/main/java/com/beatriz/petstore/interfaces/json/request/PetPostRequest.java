package com.beatriz.petstore.interfaces.json.request;

import com.beatriz.petstore.domain.PetType;
import lombok.Getter;
import lombok.Setter;

//TODO pergunta: qual melhor forma deo rganizar as request/responses PTE/TRID?
@Getter
@Setter
public class PetPostRequest {

  private String name;

  private String age;

  private PetType type;

}
