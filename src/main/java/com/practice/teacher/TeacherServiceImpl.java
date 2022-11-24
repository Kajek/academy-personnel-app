package com.practice.teacher;

import com.practice.student.Student;
import com.practice.student.StudentRepository;
import com.practice.teacher.dto.TeacherDto;
import com.practice.teacher.dto.TeacherInfoDto;
import com.practice.teacher.dto.TeacherMapper;
import org.springframework.cglib.core.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;



@Service
public class TeacherServiceImpl implements TeacherService{


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

    @Override
    public Page<Teacher> findAllTeachers(Pageable p) {
        return teacherRepository.findAll(p);

    }


    @Override
    public List<Teacher> findAllByNameAndSurname(String name, String surname) {
        return teacherRepository.findAllByNameAndSurname(name, surname);
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
