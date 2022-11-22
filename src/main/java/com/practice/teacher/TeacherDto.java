package com.practice.teacher;

import com.practice.student.Student;
import com.practice.student.StudentInfoDto;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
public class TeacherDto {

    @Id
    private Long id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private String course;
    private List<StudentInfoDto> students;
}
