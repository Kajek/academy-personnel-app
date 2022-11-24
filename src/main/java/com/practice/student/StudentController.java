package com.practice.student;

import com.practice.student.dto.StudentDto;
import com.practice.student.dto.StudentInfoDto;
import com.practice.student.dto.StudentMapper;
import com.practice.teacher.dto.TeacherInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.practice.student.dto.StudentMapper.*;
import static com.practice.teacher.dto.TeacherMapper.mapTeacherListToTeacherInfoDtoList;

@Slf4j
@RestController
public class StudentController {

    private static final Long EMPTY_ID = null;
    public static final int PAGE_SIZE = 6;
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/students")
    public Page<StudentInfoDto> getStudents(
            @RequestParam(required = false, defaultValue = "0") String page,
            @RequestParam(required = false, defaultValue = "asc") String direction,
            @RequestParam(required = false, defaultValue = "name") String sortBy
    ) {
        var sortDirection = "desc".equals(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        var sort = sortBy == null ? Sort.unsorted() : Sort.by(Sort.Order.by(sortBy).ignoreCase().with(sortDirection));

        Pageable pageRequest = PageRequest.of(Integer.parseInt(page), PAGE_SIZE, sort);
        Page<Student> students = studentService.findAllStudents(pageRequest);

        return students.map(StudentMapper::mapStudentToStudentInfoDto);

    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/students/teachers") // pagination and sorting can be added if needed. Like in getStudents() method
    public Page<StudentDto> getStudentsWithTheirTeachers(
            @RequestParam(required = false, defaultValue = "0") String page
    ) {
        Page<Student> students = studentService.findAllStudents(PageRequest.of(Integer.parseInt(page), PAGE_SIZE));
        return students.map(StudentMapper::mapStudentToStudentDto);

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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/students/{id}")
    public StudentDto getStudentById(@PathVariable Long id) {

        return mapStudentToStudentDto(studentService.getStudent(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/students/name/{name}") //case sensitive variable
    public List<StudentInfoDto> getStudentsByName(@PathVariable String name) {
        return mapStudentListToStudentInfoDtoList(studentService.findAllByName(name));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/students/surname/{surname}") //case sensitive variable
    public List<StudentInfoDto> getStudentsBySurname(@PathVariable String surname) {
        return mapStudentListToStudentInfoDtoList(studentService.findAllBySurname(surname));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/students/name/{name}/surname/{surname}") //case sensitive variables
    public List<StudentInfoDto> getStudentsByNameAndSurname(@PathVariable String name,
                                                            @PathVariable String surname) {
        return mapStudentListToStudentInfoDtoList(studentService.findAllByNameAndSurname(name, surname));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }


}
