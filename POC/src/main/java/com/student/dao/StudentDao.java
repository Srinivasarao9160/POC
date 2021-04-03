package com.student.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.details.Student;

public interface StudentDao  extends JpaRepository<Student, Integer>{

}
