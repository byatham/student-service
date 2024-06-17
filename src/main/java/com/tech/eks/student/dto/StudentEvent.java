package com.tech.eks.student.dto;

import java.util.List;

import com.tech.eks.student.entity.Address;
import com.tech.eks.student.entity.Student;
import com.tech.eks.student.entity.Subject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentEvent {
	   // private String eventType;
	    private Student student;
	    private Address address;
	    private List<Subject> subjects;

}
