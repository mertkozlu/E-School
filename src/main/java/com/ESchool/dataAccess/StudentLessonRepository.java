package com.ESchool.dataAccess;

import com.ESchool.entities.StudentLesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentLessonRepository extends JpaRepository<StudentLesson, Long> {
    StudentLesson findByStudent_StudentId(Long studentId);
}
