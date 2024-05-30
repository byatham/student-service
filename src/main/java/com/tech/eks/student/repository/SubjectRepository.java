package com.tech.eks.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tech.eks.student.entity.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

}
