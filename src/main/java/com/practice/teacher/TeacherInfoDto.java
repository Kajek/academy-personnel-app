package com.practice.teacher;

import lombok.Builder;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Builder
public class TeacherInfoDto {

    // walidacje tutaj przez adnotacje
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
    @NotEmpty(message = "Course cannot be empty")
    private String course;
}
