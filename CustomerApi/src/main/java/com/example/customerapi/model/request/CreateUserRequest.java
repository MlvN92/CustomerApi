package com.example.customerapi.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;

public class CreateUserRequest {

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

   @NotNull
   @JsonProperty("firstName")
    private String firstName;
    @NotNull
    @JsonProperty("lastName")
    private String lastName;

}
