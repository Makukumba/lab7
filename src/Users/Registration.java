package Users;

public class Registration {
    public String login;
    public String password;

    public Registration(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }


    public String toString() {
        return "Login: " + login + "  Password: " + password;
    }


}