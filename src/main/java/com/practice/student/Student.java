package com.practice.student;

import com.practice.teacher.Teacher;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String surname;
    private int age;
    private String email;
    private String major;

    @ManyToMany(mappedBy = "students")
    private List<Teacher> teachers;

    public  void addTeacher(Teacher teacher){
        teachers.add(teacher);
    }
}
