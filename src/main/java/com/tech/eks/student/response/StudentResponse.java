package com.tech.eks.student.response;

import java.util.ArrayList;
import java.util.List;

import com.tech.eks.student.entity.Student;
import com.tech.eks.student.entity.Subject;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentResponse {

	private long id;

	//@JsonProperty("first_name")
	private String firstName;

	private String lastName;

	private String email;
	
	private String street;

	private String city;
	
	private List<SubjectResponse> learningSubjects;
	
	public StudentResponse(Student student) {
		this.id = student.getId();
		this.firstName = student.getFirstName();
		this.lastName = student.getLastName();
		this.email = student.getEmail();
		
		this.street = student.getAddress().getStreet();
		this.city = student.getAddress().getCity();
		
		if (student.getLearningSubjects() != null) {
			learningSubjects = new ArrayList<SubjectResponse>();
			for (Subject subject: student.getLearningSubjects()) {
				learningSubjects.add(new SubjectResponse(subject));
			}
		}
	}

}
