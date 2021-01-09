package com.fastcampus.javaallinone.project3.mycontact.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class PersonDto {

    @NotBlank(message = "name is required value")
    private String name;
    //    private int age;
    private String hobby;
    //    private String bloodType;
    private String address;
    private LocalDate birthday;
    private String job;
    private String phoneNumber;
}
