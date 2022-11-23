package com.practice.teacher;

import com.practice.teacher.dto.TeacherDto;
import com.practice.teacher.dto.TeacherInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

import static com.practice.teacher.dto.TeacherMapper.*;

@Slf4j
@RestController
public class TeacherController {

    private static final Long EMPTY_ID = null;
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @ResponseStatus(HttpStatus.OK) //działa
    @GetMapping("/teachers")
    public List<TeacherInfoDto> getTeachers() {
        return mapTeacherListToTeacherInfoDtoList(teacherService.getAllTeachers());

    }

//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/teachers")
//    public Page<TeacherInfoDto> getTeachers(
//            @RequestParam(required = false) int page
//            ) {
//        int pageNumber = page > 0 ? page : 1;
//        return mapTeacherListToTeacherInfoDtoList(teacherService
//                .getAllTeachers(pageNumber - 1));
//
//    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/teachers/students")
    public List<TeacherDto> getTeachersWithTheirStudents() {
        return mapTeacherListToTeacherDtoList(teacherService.getAllTeachers());
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/teachers/{teacherId}/students/{studentId}")
    public void addStudentToTeacher(@PathVariable Long teacherId,
                                       @PathVariable Long studentId){
        teacherService.addStudent(teacherId, studentId);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping ("/teachers/{teacherId}/students/{studentId}")
    public void removeStudentFromTeacher(@PathVariable Long teacherId,
                                    @PathVariable Long studentId){
        teacherService.removeStudent(teacherId, studentId);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/teachers")
    public Teacher addTeacher(@RequestBody @Valid TeacherInfoDto teacherInfoDto) {
        return teacherService.addTeacher(mapTeacherInfoDtoToTeacher(EMPTY_ID, teacherInfoDto));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/teachers/{id}")
    public Teacher editTeacher(@PathVariable Long id, @RequestBody @Valid TeacherInfoDto teacherInfoDto) {
        return teacherService.updateTeacher(mapTeacherInfoDtoToTeacher(id, teacherInfoDto));
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
