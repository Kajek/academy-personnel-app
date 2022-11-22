package com.practice.student;

import com.practice.teacher.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public void addStudent(Student student) {
        //metody do walidacji
        studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> findAllByName(String name) {
        return studentRepository.findAllByName(name);
    }

    @Override
    public List<Student> findAllBySurname(String surname) {
        return studentRepository.findAllBySurname(surname);
    }

    @Override
    public List<Teacher> findAllTeachers() {
        return null;
    }

    @Override
    public void updateStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student getStudent(Long id) {
        return studentRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
