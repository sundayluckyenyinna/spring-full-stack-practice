package com.fullstack.fullstack.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class AuthenticationDTO {

    private String userId;

    private String username;

    private String password;

    private String roles;

    private String authorities;

    public String getUserId() {
        return userId;
    }

    public AuthenticationDTO setUserId(String userId) {
        this.userId = userId; return this;
    }

    public String getUsername() {
        return username;
    }
    public AuthenticationDTO setUsername(String username) {
        this.username = username; return this;
    }
    public String getPassword() {
        return password;
    }
    public AuthenticationDTO setPassword(String password) {
        this.password = password; return this;
    }
    public String getRoles() {
        return roles;
    }
    public AuthenticationDTO setRoles(String roles) {
        this.roles = roles; return this;
    }
    public String getAuthorities() {
        return authorities;
    }
    public AuthenticationDTO setAuthorities(String authorities) {
        this.authorities = authorities; return this;
    }

    @Override
    public String toString() {
        return "AuthenticationDTO [authorities=" + authorities + ", password=" + password + ", roles=" + roles
                + ", userId=" + userId + ", username=" + username + "]";
    }
    
    
}
