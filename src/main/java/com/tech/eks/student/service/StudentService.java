package com.tech.eks.student.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tech.eks.student.entity.Address;
import com.tech.eks.student.entity.Student;
import com.tech.eks.student.entity.Subject;
import com.tech.eks.student.global.exception.ResourceNotFoundException;
import com.tech.eks.student.repository.AddressRepository;
import com.tech.eks.student.repository.StudentRepository;
import com.tech.eks.student.repository.SubjectRepository;
import com.tech.eks.student.request.CreateStudentRequest;
import com.tech.eks.student.request.CreateSubjectRequest;
import com.tech.eks.student.request.InQueryRequest;
import com.tech.eks.student.request.UpdateStudentRequest;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StudentService {

	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	@Autowired
	SubjectRepository subjectRepository;
	
	List<Student> studentsList=new ArrayList<>();
	
	
	  //KafkaTemplate<String, Object> kafkaTemplate;
	//@Autowired
	//KafkaTemplate<String, StudentEvent> kafkaTemplate;
		/*
		 * private KafkaTemplate<String,Object> kafkaTemplate;
		 * 
		 * public StudentService(KafkaTemplate<String, Object> kafkaTemplate) {
		 * this.kafkaTemplate = kafkaTemplate; }
		 */
	 
	 
	
	public Student createStudent (CreateStudentRequest createStudentRequest) throws InterruptedException, ExecutionException, JsonProcessingException {
		log.debug("inside createStudent ");
		//String studentReferenceId = UUID.randomUUID().toString();

		Student student = new Student(createStudentRequest);
		
		Address address = new Address();
		address.setStreet(createStudentRequest.getStreet());
		address.setCity(createStudentRequest.getCity());
		
		address = addressRepository.save(address);
		
		student.setAddress(address);
		student = studentRepository.save(student);
		
		List<Subject> subjectsList = new ArrayList<Subject>();
		
		if(createStudentRequest.getSubjectsLearning() != null) {
			for (CreateSubjectRequest createSubjectRequest : 
					createStudentRequest.getSubjectsLearning()) {
				Subject subject = new Subject();
				subject.setSubjectName(createSubjectRequest.getSubjectName());
				subject.setMarksObtained(createSubjectRequest.getMarksObtained());
				subject.setStudent(student);
				
				subjectsList.add(subject);
			}
			
			subjectRepository.saveAll(subjectsList);
			
		}
		
		student.setLearningSubjects(subjectsList);
		//kafka related things commenting for now.
		/*
		 * StudentEvent studentEvent=new StudentEvent(student,address,subjectsList);
		 * 
		 * log.info("Object before JSON Mapper "+student); SendResult<String,Object>
		 * sendResult =
		 * kafkaTemplate.send("student-events-topic",studentReferenceId,studentEvent).
		 * get();
		 * log.info("data sent to kafka topic -> "+sendResult.getRecordMetadata().topic(
		 * )); log.info("data saved in kafka partion ->"+sendResult.getRecordMetadata().
		 * partition());
		 * log.info("data saved in kafka partion and assigned with offset id -> "
		 * +sendResult.getRecordMetadata().partition());
		 */
		return student;
	}
	
	public List<Student> getAllStudents () {
		return studentRepository.findAll();
	}
	
	public Student updateStudent (UpdateStudentRequest updateStudentRequest) {
		Student student = studentRepository.findById(updateStudentRequest.getId()).get();
		
		if (updateStudentRequest.getFirstName() != null && 
				!updateStudentRequest.getFirstName().isEmpty()) {
			student.setFirstName(updateStudentRequest.getFirstName());
		}
		
		student = studentRepository.save(student);
		return student;
	}
	
	public String deleteStudent (long id) {
		studentRepository.deleteById(id);
		return "Student has been deleted successfully";
	}
	
	public List<Student> getByFirstName (String firstName) {
		
			studentsList = studentRepository.findByFirstName(firstName);
			 if(!studentsList.isEmpty())
			 {
				 return studentsList;
			 }
			 else
			 {
				 log.info("List is Empty....");
				 throw new ResourceNotFoundException("Student not found with given details");
			 }	
	}
	
	public Student getByFirstNameAndLastName (String firstName, String lastName) {
		return studentRepository.getByLastNameAndFirstName(lastName, firstName);
	}
	
	public List<Student> getByFirstNameOrLastName (String firstName, String lastName) {
		return studentRepository.findByFirstNameOrLastName(firstName, lastName);
	}
	
	public List<Student> getByFirstNameIn (InQueryRequest inQueryRequest) {
		return studentRepository.findByFirstNameIn(inQueryRequest.getFirstNames());
	}
	
	public List<Student> getAllStudentsWithPagination (int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		
		return studentRepository.findAll(pageable).getContent();
	}
	
	public List<Student> getAllStudentsWithSorting () {
		Sort sort = Sort.by(Sort.Direction.ASC, "firstName", "lastName", "email");
		
		return studentRepository.findAll(sort);
	}
	
	public Integer updateStudentWithJpql (Long id, String firstName) {
		return studentRepository.updateFirstName(id, firstName);
	}
	
	public Integer deleteStudent (String firstName) {
		return studentRepository.deleteByFirstName(firstName);
	}
	
	public List<Student> getByCity (String city) {
		return studentRepository.findByAddressCity(city);
	}
}
