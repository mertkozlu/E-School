package com.ESchool.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student_lesson")
public class StudentLesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long studentLessonId;
    private Long pointOne;
    private Long pointTwo;
    private double grade;
    private Long discontinuity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;
}
