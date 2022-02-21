package main.commands;

import fileio.UserInputData;

import java.util.List;
import java.util.Map;

public class View {
    /**
     * Cauta entitatea user dupa nume
     */
    public UserInputData getUser(final String name, final List<UserInputData> users) {
        for (UserInputData iter : users) {
            if (iter.getUsername().equals(name)) {
                return iter;
            }
        }
        return null;
    }
    /**
     * Marcare ca vazut
     */
    public int incrementHistory(final UserInputData u, final String title) {
        Map<String, Integer> history = u.getHistory();
        for (Map.Entry<String, Integer> entry: history.entrySet()) {
            if (entry.getKey().equals(title)) {
                int res = entry.getValue() + 1;
                return res;
            }
        }
        return 1;
    }
    /**
     * Verifica daca e deja vazut acel video
     */
    public int isInHistory(final UserInputData u, final String title) {
        Map<String, Integer> history = u.getHistory();
        for (Map.Entry<String, Integer> entry : history.entrySet()) {
            if (entry.getKey().equals(title)) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * @param user
     * @param users
     * @param title
     * @return
     */
    public String commandView(final String user, final List<UserInputData> users,
                              final String title) {
        UserInputData u = getUser(user, users);
        int res = incrementHistory(u, title);
        if (isInHistory(u, title) == 1) {
            int views = (int) u.getHistory().remove(title);
            u.getHistory().put(title, views + 1);
        } else {
            u.getHistory().put(title, 1);
        }
        String mess;
        mess = "success -> " + title + " was viewed with total views of " + res;
        return mess;
    }
}
