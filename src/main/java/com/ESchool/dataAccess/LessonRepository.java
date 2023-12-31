package com.ESchool.dataAccess;

import com.ESchool.entities.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    boolean existsByLessonId(Long lessonId);

    boolean existsByLessonName(String lessonName);

}
