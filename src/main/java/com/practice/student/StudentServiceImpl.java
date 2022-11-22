package com.practice.student;

import com.practice.teacher.Teacher;
import com.practice.teacher.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public StudentServiceImpl(StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }


    @Override
    public void addStudent(Student student) {
        //metody do walidacji tutaj lub w dto przez adnotacje
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

    @Override
    public void addTeacher(Long studentId, Long teacherId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow();
        Student student = getStudent(studentId);
        student.addTeacher(teacher);
        studentRepository.save(student);
    }
}
