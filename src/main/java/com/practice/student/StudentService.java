package com.practice.student;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService{

    Student addStudent(Student student);


    List<Student> findAllByNameAndSurname(String name, String surname);

    Page<Student> findAllStudents(Pageable p);

    List<Student> findAllByName(String name);

    List<Student> findAllBySurname(String surname);

    Student updateStudent(Student student);

    Student getStudent(Long id);

    void deleteStudent(Long id);
    void addTeacher(Long studentId, Long teacherId);

    void removeTeacher(Long studentId, Long teacherId);
}
