package com.practice.student;

import java.util.List;
import java.util.stream.Collectors;

public class StudentMapper {

    private StudentMapper() {
    }

    public static List<StudentInfoDto> mapStudentListToStudentInfoDtoList(List<Student> students) {
        return students.stream()
                .map(student -> StudentInfoDto.builder()
                        .id(student.getId())
                        .name(student.getName())
                        .surname(student.getSurname())
                        .age(student.getAge())
                        .email(student.getEmail())
                        .major(student.getMajor())
                        .build())
                .collect(Collectors.toList());

    }

    public static Student mapStudentInfoDtoToStudent(Long id, StudentInfoDto studentInfoDto) {
        return Student.builder()
                .name(studentInfoDto.getName())
                .surname(studentInfoDto.getSurname())
                .age(studentInfoDto.getAge())
                .email(studentInfoDto.getEmail())
                .major(studentInfoDto.getMajor())
                .build();
    }
}
