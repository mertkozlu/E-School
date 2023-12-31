package com.ESchool.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "RefreshToken")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long refreshTokenId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    Student student;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;

}
