package com.practice.teacher;

import com.practice.student.Student;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String surname;
    private int age;
    private String email;
    private String course;


    @ManyToMany
    @JoinTable(name = "teachers_students",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students;

    public void addStudent(Student student){
        students.add(student);
    }

    public void removeStudent(Student student){
        // przekazywać student albo student id - chyba łatwiej
    }
}
