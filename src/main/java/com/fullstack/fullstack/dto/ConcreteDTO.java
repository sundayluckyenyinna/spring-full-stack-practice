package com.fullstack.fullstack.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class ConcreteDTO {

    @NotEmpty( message = "The userId must not be empty!")
    private String userId;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    private String roles;

    @NotEmpty
    private String authorities;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "ConcreteDTO [authorities=" + authorities + ", password=" + password + ", roles=" + roles + ", userId="
                + userId + ", username=" + username + "]";
    }


    
}
