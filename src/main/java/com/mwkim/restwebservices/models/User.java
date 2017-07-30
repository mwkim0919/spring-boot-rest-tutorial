package com.mwkim.restwebservices.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@ApiModel(description = "All details about the user")
public class User {

    private Integer id;

    @Size(min = 2, message = "Name should have at least 2 characters.")
    @ApiModelProperty(notes = "Name should contain at least 2 characters.")
    private String name;

    @ApiModelProperty(notes = "Birth date should be in the past.")
    private LocalDateTime birthDate;

    protected User() {}

    public User(Integer id, String name, LocalDateTime birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }
}
