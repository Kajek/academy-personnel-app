package com.practice.student;

import com.practice.teacher.Teacher;
import com.practice.teacher.TeacherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public StudentServiceImpl(StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }


    @Override
    public Student addStudent(Student student) {
        studentRepository.save(student);
        return student;
    }

    @Override
    public List<Student> findAllByNameAndSurname(String name, String surname) {
        return studentRepository.findAllByNameAndSurname(name, surname);
    }

    @Override
    public Page<Student> findAllStudents(Pageable p) {
        return studentRepository.findAll(p);
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
    public Student updateStudent(Student student) {
        studentRepository.save(student);
        return student;
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
    @Override
    public void removeTeacher(Long studentId,Long teacherId){
        Student student = getStudent(studentId);
        student.getTeachers().removeIf(teacher -> Objects.equals(teacher.getId(), teacherId));
        studentRepository.save(student);
    }
}
