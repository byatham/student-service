package com.tech.eks.student.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tech.eks.student.entity.Student;
import com.tech.eks.student.request.CreateStudentRequest;
import com.tech.eks.student.request.InQueryRequest;
import com.tech.eks.student.request.UpdateStudentRequest;
import com.tech.eks.student.response.StudentResponse;
import com.tech.eks.student.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@RestController
//@Service //Resolved [org.springframework.web.servlet.resource.NoResourceFoundException: No static resource api/student/getAll.]
@RequestMapping("/student")
@Slf4j
public class StudentController {
	

	@Value("${spring.application.name}")
	public static String appName;

	@Autowired
	StudentService studentService;
	
	@GetMapping("getAll")
	public List<StudentResponse> getAllStudents () {
		log.info("inside getAllStudents () ");
		List<Student> studentList = studentService.getAllStudents();
		List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
		
		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		log.info("inside getAllStudents () ends  "+studentList);
		
		return studentResponseList;
	}
	
	@PostMapping("create")
	public StudentResponse createStudent (@Validated @RequestBody CreateStudentRequest createStudentRequest) {
		log.info("inside createStudent () ");

		Student student = studentService.createStudent(createStudentRequest);
		log.info("inside createStudent () "+student);
		
		return new StudentResponse(student);
	}
	
	@PutMapping("update")
	public StudentResponse updateStudent (@Validated @RequestBody UpdateStudentRequest updateStudentRequest) {
		log.info("inside updateStudent () ");

		Student student = studentService.updateStudent(updateStudentRequest);
		log.info("inside updateStudent () "+student);

		return new StudentResponse(student);
	}
	
	
	@DeleteMapping("delete/{id}")
	public String deleteStudent (@PathVariable long id) {
		log.info("inside deleteStudent () ");

		return studentService.deleteStudent(id);
	}
	
	@GetMapping("getByFirstName/{firstName}")
	public List<StudentResponse> getByFirstName (@PathVariable String firstName) {
		log.info("inside getByFirstName () ");

		List<Student> studentList = studentService.getByFirstName(firstName);
		
		List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
		
		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		log.info("inside getByFirstName ()  ends ");

		
		return studentResponseList;
	}
	
	@GetMapping("getByFirstNameAndLastName/{firstName}/{lastName}")
	public StudentResponse getByFirstNameAndLastName (@PathVariable String firstName, 
			@PathVariable String lastName) {
		return new StudentResponse(studentService.getByFirstNameAndLastName(firstName, lastName));
	}
	
	@GetMapping("getByFirstNameOrLastName/{firstName}/{lastName}")
	public List<StudentResponse> getByFirstNameOrLastName (@PathVariable String firstName, 
			@PathVariable String lastName) {
		
		log.info("inside getByFirstNameOrLastName () ");

		List<Student> studentList = studentService.getByFirstNameOrLastName(firstName, lastName);
		
		List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
		
		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		log.info("inside getByFirstNameOrLastName () ends  "+studentResponseList);

		return studentResponseList;
	}
	
	@GetMapping("getByFirstNameIn")
	public List<StudentResponse> getByFirstNameIn (
			@RequestBody InQueryRequest inQueryRequest) {
		log.info("inside getByFirstNameIn ()   ");

		List<Student> studentList = studentService.getByFirstNameIn(inQueryRequest);
		
		List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
		
		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		log.info("inside getByFirstNameIn () ends  "+studentResponseList);

		return studentResponseList;
	}
	
	@GetMapping("getAllWithPagination")
	public List<StudentResponse> getAllStudentsWithPagination (@RequestParam int pageNo,
			@RequestParam int pageSize) {
		log.info("inside getAllStudentsWithPagination ()  ");

		
		List<Student> studentList = studentService.
				getAllStudentsWithPagination(pageNo, pageSize);
		
		List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
		
		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		
		return studentResponseList;
	}
	
	@GetMapping("getAllWithSorting")
	public List<StudentResponse> getAllStudentsWithSorting () {
		
		List<Student> studentList = studentService.getAllStudentsWithSorting();
		
		List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
		
		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		
		return studentResponseList;
	}
	
	@PutMapping("updateFirstName/{id}/{firstName}")
	public String updateStudentWithJpql (@PathVariable Long id, @PathVariable String firstName) {
		return studentService.updateStudentWithJpql(id, firstName)+ " Student(s) updated";
	}
	
	@DeleteMapping("deleteByFirstName/{firstName}")
	public String deleteStudent (@PathVariable String firstName) {
		return studentService.deleteStudent(firstName) + " Student(s) deleted";
	}
	
	@GetMapping("/getByCity/{city}")
	public List<StudentResponse> getByCity(@PathVariable String city) {
		
		List<Student> studentList = studentService.getByCity(city);
		
		List<StudentResponse> studentResponseList = new ArrayList<StudentResponse>();
		
		studentList.stream().forEach(student -> {
			studentResponseList.add(new StudentResponse(student));
		});
		
		return studentResponseList;
	}
}
