package main.query;

import fileio.ActorInputData;
import fileio.MovieInputData;
import fileio.SerialInputData;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class ForActorsAverage {
    static final int MAX = 100000; // un fel de INT_MAX din c
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
     * verifica daca e film
     */
    public Boolean isMovie(final String title, final List<MovieInputData> movies) {
        for (MovieInputData iter : movies) {
            if (iter.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

    /**
     * verifica daca e serial
     */
    public Boolean isSerial(final String title, final List<SerialInputData> series) {
        for (SerialInputData iter : series) {
            if (iter.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

    /**
     * calculeaza ratingul per actor, adica
     * parcurge filmografia actorului si vede daca sunt filme
     * sau seriale si aduna rating.ul la suma totala
     */
    public Double ratingPerActor(final ActorInputData a,
                                 final ArrayList<String> filmography,
                                 final List<MovieInputData> movies,
                                 final List<SerialInputData> series) {
        Double o11;
        Double o22;
        double sum = 0.0;
        int contor = 0;
        for (String i : filmography) {
            if (isMovie(i, movies)) {
                o11 = getMovie(i, movies).getAverageRating();
                if (o11 < MAX) {
                    contor++;
                    sum = sum + o11;
                }
            } else {
                if (isSerial(i, series)) {
                    o22 = getSerial(i, series).getAverageRating();
                    if (o22 < MAX) {
                        contor++;
                        sum = sum + o22;
                    }
                }
            }
        }
        sum = sum / contor;
        return sum;
    }

    /**
     * @param movies
     * @param series
     * @param actors
     * @param no
     * @param sortType
     * @return
     */
    public String queryActors(final List<MovieInputData> movies,
                               final List<SerialInputData> series,
                               final List<ActorInputData> actors,
                               final int no, final String sortType) {
        String mess = "Query result: [";
        Map<String, Double> goodActors = new HashMap<>();
        List<ActorInputData> list = new ArrayList<>();
        Double grade;
        for (ActorInputData actor : actors) {
            grade = ratingPerActor(actor, actor.getFilmography(), movies, series);
            if (grade.isNaN()) {
                actor.setRatingActor(-1.0);
            } else {
                actor.setRatingActor(grade);
            }
            if (grade != 0) {
                goodActors.put(actor.getName(), grade);
                list.add(actor);
            }
        }
        if (sortType.equals("asc")) {
            list.sort(ActorInputData.getUp().thenComparing(ActorInputData.getUpName()));
        } else {
            list.sort(ActorInputData.getDown().thenComparing(ActorInputData.getDownName()));
        }
        int c = 0;
        int contor = 0;
        for (ActorInputData entry : list) {
            contor++;
            if (entry.getRatingActor() != -1.0) {
                c++;
                if (c == no || list.size() == contor) {
                    mess = mess + entry.getName();
                    mess = mess + "]";
                    return mess;
                } else {
                    mess = mess + entry.getName() + ", ";
                }
            }
        }
        //se face o verificare in caz ca se termina cu virgula mesajul
        //si nu se mai ia in considerare
        if (mess.lastIndexOf(" ") == mess.length() - 1) {
            mess = mess.substring(0, mess.length() - 2);
        }
        return mess + "]";
    }
}
