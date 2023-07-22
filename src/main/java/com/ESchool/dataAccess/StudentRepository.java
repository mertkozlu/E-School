package com.ESchool.dataAccess;

import com.ESchool.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByStudentName(String studentName);

    @Query(value = "Select count (*) from students", nativeQuery = true)
    Integer countStudent();

}
