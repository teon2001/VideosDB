package main.commands;

import fileio.UserInputData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Favorite {
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
     * Verifica daca e deja la favorite acel video
     */
    public int isAlreadyFav(final UserInputData u, final String title) {
        ArrayList<String> fav = u.getFavoriteMovies();
        for (String iter : fav) {
            if (iter.equals(title)) {
                return 1;
            }
        }
        return 0;
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
     * @return String care reprezinta mesajul din arrayResult
     */
    public String commandFavorite(final String user, final List<UserInputData> users,
                                  final String title) {
        UserInputData u = getUser(user, users);
        ArrayList<String> fav = u.getFavoriteMovies();
        String mess;
        //verifica daca este deja la favorite
        if (isAlreadyFav(u, title) == 1) {
            mess = "error -> " + title + " is already in favourite list";
            return mess;
        }
        //verifica daca este in istoric, deci dupa if.ul anterior
        //nu are cum sa fie nici in favorite
        if (isInHistory(u, title) == 1) {
            fav.add(title);
            mess = "success -> " + title + " was added as favourite";
        } else {
            mess = "error -> " + title + " is not seen";
        }
        return mess;
    }
}
