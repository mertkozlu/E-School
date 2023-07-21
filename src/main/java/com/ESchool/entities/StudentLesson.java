package com.ESchool.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private Student student;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lesson_id")
    @JsonIgnore
    private Lesson lesson;
}
