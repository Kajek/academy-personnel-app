package com.practice.student;

import com.practice.student.dto.StudentDto;
import com.practice.student.dto.StudentInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.practice.student.dto.StudentMapper.*;

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
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/students/teachers")
    public List<StudentDto> getStudentsWithTeachers() {
        return mapStudentListToStudentDtoList(studentService.getAllStudents());

    }
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/students/{studentId}/teachers/{teacherId}")
    public void addTeacherToStudent(@PathVariable Long studentId,
                                    @PathVariable Long teacherId){
        studentService.addTeacher(studentId, teacherId);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/students/{studentId}/teachers/{teacherId}")
    public void removeTeacherFromStudent(@PathVariable Long studentId,
                                    @PathVariable Long teacherId){
        studentService.removeTeacher(studentId, teacherId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/students")
    public Student addStudent(@RequestBody @Valid StudentInfoDto studentInfoDto) {
        return studentService.addStudent(mapStudentInfoDtoToStudent(EMPTY_ID, studentInfoDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/students/{id}")
    public Student editStudent(@PathVariable Long id, @RequestBody @Valid StudentInfoDto studentInfoDto) {
        return studentService.updateStudent(mapStudentInfoDtoToStudent(id, studentInfoDto));
    }

    @ResponseStatus(HttpStatus.OK) // na dto tylko to juz z listą nauczycieli
    @GetMapping("/students/{id}")
    public StudentDto getStudentById(@PathVariable Long id) {

        return mapStudentToStudentDto(studentService.getStudent(id));
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
