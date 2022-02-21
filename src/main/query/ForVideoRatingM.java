package main.query;

import fileio.MovieInputData;

import java.util.ArrayList;
import java.util.List;

public class ForVideoRatingM {

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
     * @param movies
     * @return
     */
    public ArrayList<MovieInputData> sortByGenre(final List<String> genres,
                                                 final List<MovieInputData> movies) {
        ArrayList<MovieInputData> li = new ArrayList<>();
        for (MovieInputData m : movies) {
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
     * @param sortMType
     * @param filters
     * @param movies
     * @param no
     * @return
     */
    public String queryVideoRatingM(final String sortMType,
                                    final List<List<String>> filters,
                                    final List<MovieInputData> movies,
                                    final Integer no) {
        String mess = "Query result: [";
        List<String> years = filters.get(0);
        List<String> genres = filters.get(1);
        Integer year = 0;
        if (years.get(0) != null) {
            year = Integer.parseInt(years.get(0));
        }
        if (years.size() != 0) {
            //sorteaza dupa criterii
            ArrayList<MovieInputData> m = sortByYear(year, movies);
            ArrayList<MovieInputData> mFinal = sortByGenre(genres, m);
            //ordoneaza acs sau desc
            if (sortMType.equals("asc")) {
                mFinal.sort(MovieInputData.getUp());
            } else {
                mFinal.sort(MovieInputData.getDown());
            }
            int c = 0;
            int contor = 0;

            for (MovieInputData entry : mFinal) {
                contor++;
                if (!entry.getAverageRating().isNaN()) {
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
        }
        if (mess.lastIndexOf(" ") == mess.length() - 1) {
            mess = mess.substring(0, mess.length() - 2);
        }
        return mess + "]";
    }
}
