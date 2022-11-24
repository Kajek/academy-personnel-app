package com.practice.teacher;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long>{

    List<Teacher> findAllByName(String name);

    List<Teacher> findAllBySurname(String surname);

    List<Teacher> findAllByNameAndSurname(String name , String surname);

    Page<Teacher> findAll(Pageable pageable);


}
