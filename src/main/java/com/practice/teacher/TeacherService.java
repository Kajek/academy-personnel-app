package com.practice.teacher;

import com.practice.teacher.dto.TeacherDto;
import com.practice.teacher.dto.TeacherInfoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeacherService {

    Teacher addTeacher(Teacher teacher);


    List<Teacher> findAllByNameAndSurname(String name , String surname);

    Page<Teacher> findAllTeachers(Pageable p);

    List<Teacher> findAllByName(String name);

    List<Teacher> findAllBySurname(String surname);

    Teacher updateTeacher(Teacher teacher);

    Teacher getTeacher(Long id);

    void deleteTeacher(Long id);

    void addStudent(Long teacherId, Long studentId);

    void removeStudent(Long teacherId, Long studentId);
}
