package com.practice.student;


import com.practice.teacher.Teacher;

import java.util.List;

public interface StudentService{

    void addStudent(Student student);

    List<Student> getAllStudents();

    List<Student> findAllByName(String name);

    List<Student> findAllBySurname(String surname);

    List<Teacher> findAllTeachers(); // joinem custom zapytanie

    void updateStudent(Student student);

    Student getStudent(Long id);

    void deleteStudent(Long id);
    void addTeacher(Long studentId, Long teacherId);
}
