package ua.kiev.prog;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MessageList {
    private static final MessageList msgList = new MessageList();

    private final Gson gson;
    private final List<Message> list = new LinkedList<>();

    public static MessageList getInstance() {
        return msgList;
    }

    private MessageList() {
        gson = new GsonBuilder().create();
    }

    public synchronized void add(Message m) {
        list.add(m);
    }

    public synchronized String toJSON(int n) {
        if (n >= list.size()) return null;
        return gson.toJson(new JsonMessages(list, n));
    }

    public synchronized String toJSON(int n, String toStr) {
        if (n >= list.size()) return null;

        List<Message> mylist = new LinkedList<>();

//        (new JsonMessages(list, n)).getList().forEach(m -> {
//            if (m.getFrom().equals(toStr) ||  m.getTo() == null || m.getTo().equals(toStr) )  { mylist.add(m); }
//        });

        for (int i = n; i < list.size(); i++) {
            if (list.get(i).getFrom().equals(toStr)
                    ||  list.get(i).getTo() == null
                    || list.get(i).getTo().equals(toStr) )  {
                mylist.add(list.get(i));
            }
        }
        return gson.toJson(new JsonMessages(mylist, 0));
    }


}
