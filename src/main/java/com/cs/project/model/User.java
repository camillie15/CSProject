package com.cs.project.model;

/**
 * Modelo del modulo de usuarios del sistema
 *
 * @author Erick Cordova
 */
public class User {

    private String name;
    private String lastName;
    private String email;
    private String userName;
    private String password;
    private int rol;

    //Constructores
    public User() {
    }

    public User(String name, String lastName, String email, String userName, String password, int rol) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.rol = rol;
    }

    //Metodos Get & Set
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    //Metodo ToString
    @Override
    public String toString() {
        return "User{" + "name=" + name + ", lastName=" + lastName + ", email=" + email + ", userName=" + userName + ", password=" + password + '}';
    }

}
