package com.ESchool.security;

import com.ESchool.entities.Student;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class JwtUserDetails implements UserDetails {
    Long userId;
    String username;
    String password;
    public Collection<? extends GrantedAuthority> authorities;

    public JwtUserDetails(Long userId, String username, String password,
                          Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public static JwtUserDetails create(Student student) {
        List<GrantedAuthority> authoriesList = new ArrayList<>();
        authoriesList.add(new SimpleGrantedAuthority("user"));
        return new JwtUserDetails((long) student.getStudentId(), student.getStudentName(), student.getPassword(), authoriesList);

    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
