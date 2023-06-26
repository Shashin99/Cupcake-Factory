package com.example.cupcake_factory;

public class User {
    private String username;
    private String email;
    private String phone;
    private String postalAddress;

    public User() {
        // Default constructor required for Firebase
    }

    public User(String username, String email, String phone, String postalAddress, String password1, String password2) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.postalAddress = postalAddress;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

}


