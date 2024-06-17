package com.tech.eks.student.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateSubjectRequest {

	private String subjectName;
	
	private Double marksObtained;
}
