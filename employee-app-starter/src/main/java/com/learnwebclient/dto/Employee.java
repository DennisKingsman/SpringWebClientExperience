package com.learnwebclient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private Integer empId;
    private int age;
    private String firstName;
    private String gender;
    private String lastName;
    private String role;

}
