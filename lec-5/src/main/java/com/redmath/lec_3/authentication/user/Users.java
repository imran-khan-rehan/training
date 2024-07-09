package com.redmath.lec_3.authentication.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDateTime;

@Getter
@Setter
@Entity(name = "users")
public class Users {
    @Id
    private Long userId;
    private String username;
    private String password;
    private String roles;
    private LocalDateTime createdAt;
}
