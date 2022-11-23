package com.practice.student;


import java.util.List;

public interface StudentService{

    Student addStudent(Student student);

    List<Student> getAllStudents();

    List<Student> findAllByName(String name);

    List<Student> findAllBySurname(String surname);

    Student updateStudent(Student student);

    Student getStudent(Long id);

    void deleteStudent(Long id);
    void addTeacher(Long studentId, Long teacherId);

    void removeTeacher(Long studentId, Long teacherId);
}
