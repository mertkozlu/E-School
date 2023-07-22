package com.ESchool.dataAccess;

import com.ESchool.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    RefreshToken findByStudent_StudentId(Long userId);
}
