package main.query;

import entertainment.Season;
import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ForUserActiv {

    /**
     * @param map
     * @param name
     * @return
     */
    public int numberPerVideo(final Map<String, Double> map, final String name) {
        int contor = 0;
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            if (entry.getKey().equals(name)) {
                contor++;
            }
        }
        return contor;
    }
    /**
     * intoare numarul de rating.uri pe care le a dat un singur user
     */
    public int numberPerUser(final List<MovieInputData> movies, final UserInputData u,
                             final List<SerialInputData> series) {
        int nr = 0;
        for (MovieInputData m : movies) {
            nr = nr + numberPerVideo(m.getUserToGrade(), u.getUsername());
        }
        for (SerialInputData s : series) {
            for (Season season : s.getRatedSeasons()) {
                nr = nr + numberPerVideo(season.getUserToGrade(), u.getUsername());
            }
        }
        return nr;
    }
    /**
     * Seteza acest numar si intoarce o lista cu useri
     * pentru a fi sortata ulterior pentru a afla cei mai activi useri
     */
    public ArrayList<UserInputData> usersNoOfRatings(final List<UserInputData> users,
                                                     final List<MovieInputData> movies,
                                                     final List<SerialInputData> series) {
        ArrayList<UserInputData> usersNo = new ArrayList<>();
        for (UserInputData u : users) {
            int nr = numberPerUser(movies, u, series);
            u.setNoGiveGrades(nr);
            usersNo.add(u);
        }
        return usersNo;
    }

    /**
     * @param users
     * @param sortType
     * @param no
     * @param movies
     * @param series
     * @return
     */
    public String queryUsersActivi(final List<UserInputData> users, final String sortType,
                                   final int no, final List<MovieInputData> movies,
                                   final List<SerialInputData> series) {
        String mess = "Query result: [";
        ArrayList<UserInputData> goodUsers = usersNoOfRatings(users, movies, series);
        if (sortType.equals("asc")) {
            goodUsers.sort(UserInputData.getUp().thenComparing(UserInputData.getUpName()));
        } else {
            goodUsers.sort(UserInputData.getDown().thenComparing(UserInputData.getDownName()));
        }

        int c = 0;
        int contor = 0;
        for (UserInputData entry : goodUsers) {
            contor++;
            if (entry.getNoGiveGrades() != 0) {
                c++;
                if (c == no || goodUsers.size() == contor) {
                    mess = mess + entry.getUsername();
                    mess = mess + "]";
                    return mess;
                } else {
                    mess = mess + entry.getUsername() + ", ";
                }
            }
        }
        if (mess.lastIndexOf(" ") == mess.length() - 1) {
            mess = mess.substring(0, mess.length() - 2);
            return mess + "]";
        }
        return mess + "]";
    }
}

