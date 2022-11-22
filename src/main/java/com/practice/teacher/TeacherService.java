package com.practice.teacher;

import com.practice.student.Student;

import java.util.List;

public interface TeacherService {

    void addTeacher(Teacher teacher);

    List<Teacher> getAllTeachers();

    List<Teacher> findAllByName(String name);

    List<Teacher> findAllBySurname(String surname);

    void updateTeacher(Teacher teacher);

    Teacher getTeacher(Long id);

    void deleteTeacher(Long id);

    void addStudent(Long teacherId, Long studentId);
}
