package ua.kiev.prog;

import java.util.ArrayList;

public class LoginList {
    private ArrayList<String> list = new ArrayList<>();
    private static final LoginList loginList = new LoginList();

    public static LoginList getLoginList() {
        return loginList;
    }

    public synchronized void add(String m) {
        if (!list.contains(m)) {
            list.add(m);
        }
    }

    public ArrayList<String> getList() {
        return list;
    }
}
