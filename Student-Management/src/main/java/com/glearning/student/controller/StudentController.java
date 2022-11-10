package com.glearning.student.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.glearning.student.model.Student;
import com.glearning.student.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/list")
	public String listStudents(Model theModel) {
		List<Student> theStudents=this.studentService.findAll();
		
		//add to the spring model
		theModel.addAttribute("Students", theStudents);
		return "list-students";
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Student student=new Student();
		
		//adding this student object to the model
		theModel.addAttribute("student", student);
		
		return "Student-form";
	}
	
	@RequestMapping("/showFormForUpdate")
	public String showFormForDelete(@RequestParam("studentId") int theId,Model theModel) {
		//getting the customer first whose records has to be updated
		Student student=this.studentService.findById(theId);
		
		// set Customer as a model attribute to pre-populate the form
		theModel.addAttribute("student", student);
		
		return "Student-form";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("studentId") int stuId) {
		//delete the student
		this.studentService.deleteById(stuId);
		
		//redirect to the list of students then
		return "redirect:/students/list";
	}
	
	@PostMapping("/save")
	public String saveCustomer(@RequestParam("studentId") int studentId,@RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName,@RequestParam("course") String course,@RequestParam("country") String country) {
		
		System.out.println(studentId);
		Student student=null;
		if(studentId!=0) {			
			//finding the customer by Id first in case of an update
			student=this.studentService.findById(studentId);
			student.setFirstName(firstName);
			student.setLastName(lastName);
			student.setCourse(course);
			student.setCountry(country);
		}else {
			//creating a new object in case for a new record
			student=new Student(firstName, lastName, course, country);
		}
	    
		//save the student
		this.studentService.save(student);
		
		// use a redirect to prevent duplicate submissions
		return "redirect:/students/list";		
	}
	
	@RequestMapping("/search")
	public String search(@RequestParam("firstName") String firstName,Model theModel) {
		
		//will check the first name of the student
		//if empty will return to the list of students
		if(firstName.trim().isEmpty()) {
			return "redirect:/students/list";
		}
		else {
			//otherwise search the student using their first name
			List<Student> students=this.studentService.searchBy(firstName);
			
			//add to the spring model
			theModel.addAttribute("Students", students);
			
			//send to the list of students page
			return "list-students";
		}
	}
	
	@RequestMapping(value = "/403")
	public ModelAndView accessDenied(Principal user) {
		
		ModelAndView model=new ModelAndView();
		
		if(user!=null) {
			model.addObject("msg","Hi "+user.getName()
			+ ", you do not have permission to access this page!");
		}else {
			model.addObject("msg"
			+ "You do not have permission to access this page!");
		}
		
		model.setViewName("403");
		return model;
	}
	
	
}
