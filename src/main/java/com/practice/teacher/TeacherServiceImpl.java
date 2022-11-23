package com.practice.teacher;

import com.practice.student.Student;
import com.practice.student.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


@Service
public class TeacherServiceImpl implements TeacherService{

    public static final int PAGE_SIZE = 2;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Teacher addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
        return teacher;
    }

    @Override //działa
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }
//    @Override
//    public Page getAllTeachers(int page) {
//        return teacherRepository.findAll(PageRequest.of(page, PAGE_SIZE));
////                Sort.Direction.ASC, sortBy.orElse("id"))));
//    }

    @Override
    public List<Teacher> findAllByName(String name) {
        return teacherRepository.findAllByName(name);
    }

    @Override
    public List<Teacher> findAllBySurname(String surname) {
        return teacherRepository.findAllBySurname(surname);
    }


    @Override
    public Teacher updateTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
        return teacher;
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

    @Override
    public void removeStudent(Long teacherId, Long studentId){
        Teacher teacher = getTeacher(teacherId);
        teacher.getStudents().removeIf(st -> Objects.equals(st.getId(), studentId));
        teacherRepository.save(teacher);
    }
}
