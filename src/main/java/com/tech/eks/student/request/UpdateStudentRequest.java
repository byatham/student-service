package com.tech.eks.student.request;



import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateStudentRequest {
	
	//@NotNull(message = "Student Id is required")
	private Long id;
	
	private String firstName;

	private String lastName;

	private String email;

}
