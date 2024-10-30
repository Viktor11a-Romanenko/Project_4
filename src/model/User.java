package model;

import utils.MyArrayList;
import utils.MyList;

public class User {
    private String name;
    private String email;
    private Role role;
    private final MyList<Book> userBooks;
    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.role = Role.USER;
        this.userBooks = new MyArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public MyList<Book> getUserBooks() {
        return userBooks;
    }

    public void setEmail(String email) {
        this.email = email;

    }
}






