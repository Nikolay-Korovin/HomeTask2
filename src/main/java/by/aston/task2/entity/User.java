package by.aston.task2.entity;

public class User {
    private long id;
    private String username;
    private String login;
    private String password;

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
