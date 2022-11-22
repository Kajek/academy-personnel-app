package com.practice.teacher;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.practice.student.StudentMapper.mapStudentListToStudentInfoDtoList;
import static com.practice.teacher.TeacherMapper.*;

@Slf4j
@RestController
public class TeacherController {

    private static final Long EMPTY_ID = null;
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/teachers")
    public List<TeacherInfoDto> getTeachers() {
        return mapTeacherListToTeacherInfoDtoList(teacherService.getAllTeachers());

    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/teachers/students")
    public List<TeacherDto> getTeachersWithTheirStudents() {
        return mapTeacherListToTeacherDtoList(teacherService.getAllTeachers());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/teachers/{teacherId}/students/{studentId}")
    public void addStudentToTeacher(@PathVariable Long teacherId,
                                       @PathVariable Long studentId){
        teacherService.addStudent(teacherId, studentId);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/teachers")
    public void addTeacher(@RequestBody @Valid TeacherInfoDto teacherInfoDto) {
        teacherService.addTeacher(mapTeacherInfoDtoToTeacher(EMPTY_ID, teacherInfoDto));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/teachers/{id}")
    public void editTeacher(@PathVariable Long id, @RequestBody @Valid TeacherInfoDto teacherInfoDto) {
        teacherService.updateTeacher(mapTeacherInfoDtoToTeacher(id, teacherInfoDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/teachers/{id}")
    public TeacherDto getTeacherById(@PathVariable Long id) {
        return mapTeacherToTeacherDto(teacherService.getTeacher(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/teachers/name/{name}") //case sensitive
    public List<TeacherInfoDto> getTeachersByName(@PathVariable String name) {
        return mapTeacherListToTeacherInfoDtoList(teacherService.findAllByName(name));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/teachers/surname/{surname}") //case sensitive
    public List<TeacherInfoDto> getTeachersBySurname(@PathVariable String surname) {
        return mapTeacherListToTeacherInfoDtoList(teacherService.findAllBySurname(surname));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/teachers/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
    }


}
