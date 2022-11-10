package com.glearning.student.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.glearning.student.model.Student;
import com.glearning.student.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService{
	

	private StudentRepository studentRepository;
	
	@Autowired
	 public StudentServiceImpl(StudentRepository studentRepository) {
		 this.studentRepository=studentRepository;
	 }
		

	@Override
	public List<Student> findAll() {
		return this.studentRepository.findAll();
	}

	@Override
	public Student findById(int studentId) {
		return this.studentRepository.findById(studentId).get();
	}

	@Override
	public void save(Student theStudent) {
		this.studentRepository.save(theStudent);		
	}

	@Override
	public void deleteById(int studentId) {
		this.studentRepository.deleteById(studentId);
	}

	@Override
	public List<Student> searchBy(String firstName) {
		return this.studentRepository.findByFirstNameStartingWith(firstName);
	}

}
