package com.practice.teacher.dto;

import com.practice.teacher.Teacher;

import java.util.List;
import java.util.stream.Collectors;

import static com.practice.student.dto.StudentMapper.mapStudentListToStudentInfoDtoList;

public class TeacherMapper {

    private TeacherMapper(){}

    public static List<TeacherInfoDto> mapTeacherListToTeacherInfoDtoList(List<Teacher> teachers) {
        return teachers.stream()
                .map(teacher -> TeacherInfoDto.builder()
                        .id(teacher.getId())
                        .name(teacher.getName())
                        .surname(teacher.getSurname())
                        .age(teacher.getAge())
                        .email(teacher.getEmail())
                        .course(teacher.getCourse())
                        .build())
                .collect(Collectors.toList());
        }

    public static Teacher mapTeacherInfoDtoToTeacher(Long id, TeacherInfoDto teacherInfoDto) {
        return Teacher.builder()
                .id(id)
                .name(teacherInfoDto.getName())
                .surname(teacherInfoDto.getSurname())
                .age(teacherInfoDto.getAge())
                .email(teacherInfoDto.getEmail())
                .course(teacherInfoDto.getCourse())
                .build();
    }

    public static TeacherInfoDto mapTeacherToTeacherInfoDto(Teacher teacher) {
        return TeacherInfoDto.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .surname(teacher.getSurname())
                .age(teacher.getAge())
                .email(teacher.getEmail())
                .course(teacher.getCourse())
                .build();
    }

    public static List<TeacherDto> mapTeacherListToTeacherDtoList(List<Teacher> teachers) {
        return teachers.stream()
                .map(TeacherMapper::mapTeacherToTeacherDto)
                .collect(Collectors.toList());
    }

    public static TeacherDto mapTeacherToTeacherDto(Teacher teacher) {
        return TeacherDto.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .surname(teacher.getSurname())
                .age(teacher.getAge())
                .email(teacher.getEmail())
                .course(teacher.getCourse())
                .students(mapStudentListToStudentInfoDtoList(teacher.getStudents()))
                .build();
    }





}
