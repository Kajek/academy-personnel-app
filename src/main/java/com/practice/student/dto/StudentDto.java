package com.practice.student.dto;

import com.practice.teacher.dto.TeacherInfoDto;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
public class StudentDto {
    @Id
    private Long id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private String major;
    private List<TeacherInfoDto> teachers;
}
