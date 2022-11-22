package com.practice.teacher;

import com.practice.student.Student;
import com.practice.student.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TeacherServiceImpl implements TeacherService{

    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public void addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public List<Teacher> findAllByName(String name) {
        return teacherRepository.findAllByName(name);
    }

    @Override
    public List<Teacher> findAllBySurname(String surname) {
        return teacherRepository.findAllBySurname(surname);
    }


    @Override
    public void updateTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public Teacher getTeacher(Long id) {
        return teacherRepository.findById(id).orElseThrow();
    }


    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public void addStudent(Long teacherId, Long studentId) {
        Teacher teacher = getTeacher(teacherId);
        Student student = studentRepository.findById(studentId).orElseThrow();
        teacher.addStudent(student);
        teacherRepository.save(teacher);
    }
}
