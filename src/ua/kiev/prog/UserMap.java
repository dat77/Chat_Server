package ua.kiev.prog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMap {

    private static final UserMap userMap = new UserMap();

    private final Map<String, User> users = new HashMap<>();
    private final Gson gson;

    private UserMap() {
        gson = new GsonBuilder().create();
    }

    public static UserMap getInstance() {
        return userMap;
    }

    public synchronized void add(User user){
        if (users.containsKey(user.getLogin())) {
            throw new IllegalArgumentException("User exists. Try another login");
        }
        users.put(user.getLogin(), user);
    }

    public synchronized String toJSON(){
        List<User> userList = new ArrayList<>();
        users.forEach((k, v) -> { if (v.getStatus() != User.Status.HIDDEN) userList.add(v);});
        return gson.toJson(userList);
    }

}
