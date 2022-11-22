package com.practice.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository  extends JpaRepository<Student, Long> {

    List<Student> findAllByName(String name);

    List<Student> findAllBySurname(String surname);
}
