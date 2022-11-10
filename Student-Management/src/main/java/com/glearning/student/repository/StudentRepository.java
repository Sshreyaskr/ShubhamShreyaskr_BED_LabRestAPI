package com.glearning.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import com.glearning.student.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer>{
   
	public List<Student> findByFirstNameStartingWith(String prefix);
}
