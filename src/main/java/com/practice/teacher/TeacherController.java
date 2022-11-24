package com.practice.teacher;

import com.practice.teacher.dto.TeacherDto;
import com.practice.teacher.dto.TeacherInfoDto;
import com.practice.teacher.dto.TeacherMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.practice.teacher.dto.TeacherMapper.*;

@Slf4j
@RestController
public class TeacherController {

    private static final Long EMPTY_ID = null;
    public static final int PAGE_SIZE = 6;
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/teachers")
    public Page<TeacherInfoDto> getTeachers(
            @RequestParam(required = false, defaultValue = "0") String page,
            @RequestParam(required = false, defaultValue = "asc") String direction,
            @RequestParam(required = false, defaultValue = "name") String sortBy
            ) {
        var sortDirection = "desc".equals(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
        var sort = sortBy == null ? Sort.unsorted() : Sort.by(Sort.Order.by(sortBy).ignoreCase().with(sortDirection));

        Pageable pageRequest = PageRequest.of(Integer.parseInt(page), PAGE_SIZE, sort);
        Page<Teacher> teachers = teacherService.findAllTeachers(pageRequest);

        return teachers.map(TeacherMapper::mapTeacherToTeacherInfoDto);

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/teachers/students") // pagination and sorting can be added if needed. Like in getTeachers() method
    public Page<TeacherDto> getTeachersWithTheirStudents(
            @RequestParam(required = false, defaultValue = "0") String page
    ) {
        Page<Teacher> teachers = teacherService.findAllTeachers(PageRequest.of(Integer.parseInt(page), PAGE_SIZE));
        return teachers.map(TeacherMapper::mapTeacherToTeacherDto);

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
    @GetMapping("/teachers/name/{name}") //case sensitive variable
    public List<TeacherInfoDto> getTeachersByName(@PathVariable String name) {
        return mapTeacherListToTeacherInfoDtoList(teacherService.findAllByName(name));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/teachers/surname/{surname}") //case sensitive variable
    public List<TeacherInfoDto> getTeachersBySurname(@PathVariable String surname) {
        return mapTeacherListToTeacherInfoDtoList(teacherService.findAllBySurname(surname));
    }

    @ResponseStatus(HttpStatus.OK) // przetestować czy działa
    @GetMapping("/teachers/name/{name}/surname/{surname}") //case sensitive variables
    public List<TeacherInfoDto> getTeachersByNameAndSurname(@PathVariable String name,
                                                            @PathVariable String surname) {
        return mapTeacherListToTeacherInfoDtoList(teacherService.findAllByNameAndSurname(name, surname));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/teachers/{id}")
    public void deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
    }


}
