package main.commands;

import entertainment.Season;
import fileio.SerialInputData;
import fileio.UserInputData;
import fileio.MovieInputData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Rating {
    /**
     * Cauta entitatea user dupa numet
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
     * Cauta entitatea serial dupa nume
     */
    public SerialInputData getSerial(final String title, final List<SerialInputData> series) {
        for (SerialInputData iter : series) {
            if (iter.getTitle().equals(title)) {
                return iter;
            }
        }
        return null;
    }
    /**
     * Cauta entitatea film dupa nume
     */
    public MovieInputData getMovie(final String title, final List<MovieInputData> movies) {
        for (MovieInputData iter : movies) {
            if (iter.getTitle().equals(title)) {
                return iter;
            }
        }
        return null;
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
     * Verifica daca un film a mai primit rating de la un anume user
     */
    public int hasBeenRatedM(final UserInputData u, final MovieInputData m) {
        Map<String, Double> map = m.getUserToGrade();
        for (Map.Entry<String, Double> entry : map.entrySet()) {
            if (entry.getKey().equals(u.getUsername())) {
                return 1;
            }
        }
        return 0;
    }
    /**
     * Verifica daca un sezon dintr-un serial a mai primit rating de la un anume user
     */
    public int hasBeenRatedS(final UserInputData u, final Season season) {
        if (season.getUserToGrade().containsKey(u.getUsername())) {
            return 1;
        }
        return 0;
    }

    /**
     * @param user
     * @param users
     * @param title
     * @param grade
     * @param movies
     * @return mesajul de pus in array
     */
    public String commandRatingMovie(final String user, final List<UserInputData> users,
                                     final String title, final Double grade,
                                     final List<MovieInputData> movies) {
        UserInputData u = getUser(user, users);
        MovieInputData m = getMovie(title, movies);
        String mess;
        if (isInHistory(u, title) == 0) {
            mess = "error -> " + title + " is not seen";
        } else {
            if (hasBeenRatedM(u, m) == 1) {
                mess = "error -> " + title + " has been already rated";
            } else {
                m.getUserToGrade().put(user, grade);
                u.getRatedMovies().add(m);
                mess = "success -> " + title + " was rated with " + grade
                        + " by " + user;
            }
        }
        return mess;
    }
    /**
     * Cauta in lista de sezoane care au primit rating, ca este
     * si un anume sezon
     */
    public Boolean isSeasonRated(final ArrayList<Season> rated, final Season s) {
        for (Season i : rated) {
            if (i.getCurrentSeason() == s.getCurrentSeason()) {
                return true;
            }
        }
        return false;
    }
    /**
     * Verifica daca prin lista user.ului exista si serialul s
     */
    public Boolean hasGiveRating(final UserInputData u, final SerialInputData s) {
        ArrayList<SerialInputData> se = u.getRatedSeries();
        for (SerialInputData i : se) {
            if (i.getTitle().equals(s.getTitle())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param user
     * @param users
     * @param title
     * @param series
     * @param grade
     * @param season
     * @return
     */
    public String commandRatingSeries(final String user, final List<UserInputData> users,
                                      final String title, final List<SerialInputData> series,
                                      final Double grade, final Season season) {
        UserInputData u = getUser(user, users);
        SerialInputData s = getSerial(title, series);
        String mess;
        if (isInHistory(u, title) == 0) {
            mess = "error -> " + title + " is not seen";
        } else {
            if (hasBeenRatedS(u, season) == 1) {
                mess = "error -> " + title + " has been already rated";
            } else {
                if (!hasGiveRating(u, s)) {
                    u.getRatedSeries().add(s);
                }
                season.getUserToGrade().put(user, grade);
                season.getRatings().add(grade);
                if (!isSeasonRated(s.getRatedSeasons(), season)) {
                    s.getRatedSeasons().add(season);
                }
                mess = "success -> " + title + " was rated with " + grade + " by "
                        + user;
            }
        }
        return mess;
    }
}
