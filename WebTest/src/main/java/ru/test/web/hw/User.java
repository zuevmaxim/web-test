package ru.test.web.hw;

public class User {
    private final String name;
    private final String email;
    private final String login;
    private final String password;

    public User(String name, String email, String login, String password) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.password = password;
    }

    String getName() {
        return name;
    }

    String getEmail() {
        return email;
    }

    String getLogin() {
        return login;
    }

    String getPassword() {
        return password;
    }
}
