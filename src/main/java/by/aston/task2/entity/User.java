package by.aston.task2.entity;

import jakarta.persistence.*;

import java.io.Serializable;

//@Entity
//@Table(name = "users")
public class User implements Serializable {
    //private static final long serialVersionUID = 1L;

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
//    @Column(name = "username")
    private String username;
//    @Column(name = "login")
    private String login;
//    @Column(name = "password")
    private String password;

    public User() {

    }

    public User(String name, String login, String password) {
        this.username = name;
        this.login = login;
        this.password = password;
    }

    public User(long id, String username, String login, String password) {
        this.id = id;
        this.username = username;
        this.login = login;
        this.password = password;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
