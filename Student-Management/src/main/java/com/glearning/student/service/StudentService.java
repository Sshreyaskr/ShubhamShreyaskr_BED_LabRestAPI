package com.glearning.student.service;

import java.util.List;

import com.glearning.student.model.Student;

public interface StudentService {
	
	List<Student> findAll();

	Student findById(int studentId);

	public void save(Student theStudent);

	void deleteById(int studentId);
	
	public List<Student> searchBy(String firstName);

}
