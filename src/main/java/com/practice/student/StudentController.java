package com.practice.student;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.practice.student.StudentMapper.mapStudentListToStudentInfoDtoList;
import static com.practice.student.StudentMapper.mapStudentInfoDtoToStudent;

@Slf4j
@RestController
public class StudentController {

    private static final Long EMPTY_ID = null;
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/students")
    public List<StudentInfoDto> getStudents() {
        return mapStudentListToStudentInfoDtoList(studentService.getAllStudents());

    }
    @ResponseStatus(HttpStatus.OK)//przerobić z listą nauczycieli, inne dto, mapujące z relacją
    @GetMapping("/students/teachers")
    public List<StudentInfoDto> getStudentsWithTeachers() {
        return mapStudentListToStudentInfoDtoList(studentService.getAllStudents());

    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/students")
    public void addStudent(@RequestBody @Valid StudentInfoDto studentInfoDto) {
        studentService.addStudent(mapStudentInfoDtoToStudent(EMPTY_ID, studentInfoDto));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/students/{id}")
    public void editStudent(@PathVariable Long id, @RequestBody @Valid StudentInfoDto studentInfoDto) {
        studentService.updateStudent(mapStudentInfoDtoToStudent(id, studentInfoDto));
    }

    @ResponseStatus(HttpStatus.OK) // na dto tylko to juz z listą nauczycieli
    @GetMapping("/students/{id}")
    public Student getStudentById(@PathVariable Long id) {

        return studentService.getStudent(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/students/name/{name}") //case sensitive
    public List<StudentInfoDto> getStudentsByName(@PathVariable String name) {
        return mapStudentListToStudentInfoDtoList(studentService.findAllByName(name));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/students/surname/{surname}") //case sensitive
    public List<StudentInfoDto> getStudentsBySurname(@PathVariable String surname) {
        return mapStudentListToStudentInfoDtoList(studentService.findAllBySurname(surname));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }


}
