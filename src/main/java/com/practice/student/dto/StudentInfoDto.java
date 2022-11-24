package com.practice.student.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Builder
public class StudentInfoDto {


    @Id
    private Long id;
    @Length(min = 3)
    private String name;
    @Length(min = 3)
    private String surname;
    @Min(19)
    private int age;
    @Email(message = "Not a valid email adress")
    private String email;
    @NotEmpty(message = "Major cannot be empty")
    private String major;
}
