package com.beatriz.petstore.interfaces.json.request;

import com.beatriz.petstore.domain.StatusType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetPutRequest {

  private String name;

  private StatusType status;
}
