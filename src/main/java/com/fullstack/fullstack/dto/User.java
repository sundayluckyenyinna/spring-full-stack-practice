package com.fullstack.fullstack.dto;


public class User {
    private String email;
    private String firstName;
    private String lastName;
    private String department;
    
    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDepartment() {
        return department;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public User setDepartment(String department) {
        this.department = department;
        return this;
    }

    @Override
    public String toString() {
        return "User [department=" + department + ", email=" + email + ", firstName=" + firstName + ", lastName="
                + lastName + "]";
    }

    
}