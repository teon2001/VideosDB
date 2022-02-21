package main.query;

import fileio.MovieInputData;
import fileio.UserInputData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ForVideoMostM {

    /**
     * @param year
     * @param movies
     * @return
     */
    public ArrayList<MovieInputData> sortByYear(final Integer year,
                                                final List<MovieInputData> movies) {
        ArrayList<MovieInputData> li = new ArrayList<>();
        for (MovieInputData m : movies) {
            if (m.getYear() == year) {
                li.add(m);
            }
        }
        return li;
    }

    /**
     * @param genres
     * @param series
     * @return
     */
    public ArrayList<MovieInputData> sortByGenre(final List<String> genres,
                                                 final List<MovieInputData> series) {
        ArrayList<MovieInputData> li = new ArrayList<>();
        for (MovieInputData m : series) {
            int contor = 0;
            for (String s : genres) {
                if (m.getGenres().contains(s)) {
                    contor++;
                }
            }
            if (contor == genres.size()) {
                li.add(m);
            }
        }
        return li;
    }

    /**
     * @param u
     * @param title
     * @return
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
     * Verifica daca a vazut acel video si intoarce
     * numarul de vizualizari
     */
    public int getView(final UserInputData u, final String title) {
        Map<String, Integer> history = u.getHistory();
        for (Map.Entry<String, Integer> entry : history.entrySet()) {
            if (entry.getKey().equals(title)) {
                return entry.getValue();
            }
        }
        return 0;
    }
    /**
     * list de filme cu multe vizualizari
     */
    public ArrayList<MovieInputData> listMovies(final List<UserInputData> users,
                                                final List<MovieInputData> movies) {
        ArrayList<MovieInputData> li = new ArrayList<>();
        for (MovieInputData s : movies) {
            int sumPerMovie = 0;
            for (UserInputData u : users) {
                if (isInHistory(u, s.getTitle()) == 1) {
                    sumPerMovie = sumPerMovie + getView(u, s.getTitle());
                }
            }
            s.setNrView(sumPerMovie);
            li.add(s);
        }
        return li;
    }

    /**
     * @param movies
     * @param users
     * @param filters
     * @param sortType
     * @param no
     * @return
     */
    public String queryVideoMostM(final List<MovieInputData> movies,
                                  final List<UserInputData> users,
                                  final List<List<String>> filters,
                                  final String sortType, final int no) {
        String mess = "Query result: [";
        List<String> years = filters.get(0);
        List<String> genres = filters.get(1);
        List<MovieInputData> goodSeries = listMovies(users, movies);

        int year = 0;
        if (years.get(0) != null) {
            year = Integer.parseInt(years.get(0));
        }
        // In continuare, 4 cazuri:
        if (years.get(0) != null && genres.get(0) != null) {
            ArrayList<MovieInputData> m = sortByYear(year, goodSeries);
            ArrayList<MovieInputData> mFinal = sortByGenre(genres, m);


            if (sortType.equals("asc")) {
                mFinal.sort(MovieInputData.getUpV().thenComparing(MovieInputData.getUpName()));
            } else {
                mFinal.sort(MovieInputData.getDownV().thenComparing(MovieInputData.getDownName()));
            }
            int c = 0;
            int contor = 0;
            for (MovieInputData entry : mFinal) {
                contor++;
                if (entry.getNrView() != 0) {
                    c++;
                    if (c == no || mFinal.size() == contor) {
                        mess = mess + entry.getTitle();
                        mess = mess + "]";
                        return mess;
                    } else {
                        mess = mess + entry.getTitle() + ", ";
                    }
                }
            }
        } else if (years.get(0) != null) {
            ArrayList<MovieInputData> m = sortByYear(year, goodSeries);

            if (sortType.equals("asc")) {
                m.sort(MovieInputData.getUpV().thenComparing(MovieInputData.getUpName()));
            } else {
                m.sort(MovieInputData.getDownV().thenComparing(MovieInputData.getDownName()));
            }
            int c = 0;
            int contor = 0;
            for (MovieInputData entry : m) {
                contor++;
                if (entry.getNrView() != 0) {
                    c++;
                    if (c == no || m.size() == contor) {
                        mess = mess + entry.getTitle();
                        mess = mess + "]";
                        return mess;
                    } else {
                        mess = mess + entry.getTitle() + ", ";
                    }
                }
            }
        } else if (genres.get(0) != null) {
            ArrayList<MovieInputData> mFinal = sortByGenre(genres, goodSeries);

            if (sortType.equals("asc")) {
                mFinal.sort(MovieInputData.getUpV().thenComparing(MovieInputData.getUpName()));
            } else {
                mFinal.sort(MovieInputData.getDownV().thenComparing(MovieInputData.getDownName()));
            }
            int c = 0;
            int contor = 0;
            for (MovieInputData entry : mFinal) {
                contor++;
                if (entry.getNrView() != 0) {
                    c++;
                    if (c == no || mFinal.size() == contor) {
                        mess = mess + entry.getTitle();
                        mess = mess + "]";
                        return mess;
                    } else {
                        mess = mess + entry.getTitle() + ", ";
                    }
                }
            }
        } else {
            if (sortType.equals("asc")) {
                goodSeries.sort(MovieInputData.getUpV().
                        thenComparing(MovieInputData.getUpName()));
            } else {
                goodSeries.sort(MovieInputData.getDownV().
                        thenComparing(MovieInputData.getDownName()));
            }
            int c = 0;
            int contor = 0;
            for (MovieInputData entry : goodSeries) {
                contor++;
                if (entry.getNrView() != 0) {
                    c++;
                    if (c == no || goodSeries.size() == contor) {
                        mess = mess + entry.getTitle();
                        mess = mess + "]";
                        return mess;
                    } else {
                        mess = mess + entry.getTitle() + ", ";
                    }
                }
            }
        }
        if (mess.lastIndexOf(" ") == mess.length() - 1) {
            mess = mess.substring(0, mess.length() - 2);
        }
        return mess + "]";
    }
}
