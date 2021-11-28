package ua.com.alevel.service.entity;

public class Access extends BaseEntity {

    private String login;
    private String password;

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

    @Override
    public String toString() {
        return "Credentials: " +
                "Client id='" + super.getId() + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'';

    }
}