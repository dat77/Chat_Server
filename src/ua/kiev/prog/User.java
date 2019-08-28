package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class User {

    public enum Status {AVAILABLE, NOTAVAILABLE, DONOTDESTURB, HIDDEN};

    private String login;
    private Status status;

    public User() {
    }

    public User(String login, Status status) {
        this.login = login;
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("login='").append(login).append('\'');
        sb.append(", status=").append(status);
        sb.append('}');
        return sb.toString();
    }

    public String toJSON() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

    public static User fromJSON(String s) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(s, User.class);
    }

}
