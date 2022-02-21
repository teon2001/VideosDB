package main.query;

import fileio.SerialInputData;
import fileio.UserInputData;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;


public class ForVideoFavoriteS {
    /**
     * Sortarea serialelor dupa an
     */
    public ArrayList<SerialInputData> sortByYear(final Integer year,
                                                 final List<SerialInputData> series) {
        ArrayList<SerialInputData> li = new ArrayList<>();
        for (SerialInputData m : series) {
            if (m.getYear() == year) {
                li.add(m);
            }
        }
        return li;
    }

    /**
     * Sortarea serialelor dupa gen
     */
    public ArrayList<SerialInputData> sortByGenre(final List<String> genres,
                                                  final List<SerialInputData> series) {
        ArrayList<SerialInputData> li = new ArrayList<>();
        for (SerialInputData m : series) {
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
     * de cate ori apare un serial in lista de favorite
     * a tuturor user.ilor
     */
    public int nrFavoritePerSerial(final SerialInputData s,
                                   final List<UserInputData> users) {
        int c = 0;
        for (UserInputData u : users) {
            if (u.getFavoriteMovies().contains(s.getTitle())) {
                c++;
            }
        }
        return c;
    }

    /**
     * intoarce lista cu serialele favorite
     */
    public Map<String, Integer> listFavs(final List<SerialInputData> series,
                                         final List<UserInputData> users) {
        Map<String, Integer> favs = new HashMap<>();
        for (SerialInputData s : series) {
            int nr = nrFavoritePerSerial(s, users);
            favs.put(s.getTitle(), nr);
        }
        return favs;
    }

    /**
     * @param series
     * @param users
     * @param filters
     * @param sortType
     * @param no
     * @return
     */
    public String queryVideoFavoriteS(final List<SerialInputData> series,
                                      final List<UserInputData> users,
                                      final List<List<String>> filters,
                                      final String sortType, final int no) {
        String mess = "Query result: [";
        List<String> years = filters.get(0);
        List<String> genres = filters.get(1);
        int year = 0;
        if (years.get(0) != null) {
            year = Integer.parseInt(years.get(0));
        }

        //  Exista 4 cazuri:
        //      1. are si gen si an
        //      2. are doar gen, nu si an
        //      3. are doar an, nu si gen
        //      4. nu are nici gen nici an

        if (years.get(0) != null && genres.get(0) != null) {
            ArrayList<SerialInputData> m = sortByYear(year, series);
            ArrayList<SerialInputData> mFinal = sortByGenre(genres, m);
            Map<String, Integer> favs = listFavs(mFinal, users);
            List<VideoForFavorite> f = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : favs.entrySet()) {
                VideoForFavorite sFF = new VideoForFavorite();
                sFF.setNr(entry.getValue());
                sFF.setTitle(entry.getKey());
                f.add(sFF);
            }
            if (sortType.equals("asc")) {
                f.sort(VideoForFavorite.getUp().thenComparing(VideoForFavorite.getUpName()));
            } else {
                f.sort(VideoForFavorite.getDown().thenComparing(VideoForFavorite.getDownName()));
            }
            int c = 0;
            int contor = 0;
            for (VideoForFavorite entry : f) {
                contor++;
                if (entry.getNr() != 0) {
                    c++;
                    if (c == no || f.size() == contor) {
                        mess = mess + entry.getTitle();
                        mess = mess + "]";
                        return mess;
                    } else {
                        mess = mess + entry.getTitle() + ", ";
                    }
                }
            }
        } else if (years.get(0) != null) {
            ArrayList<SerialInputData> m = sortByYear(year, series);
            Map<String, Integer> favs = listFavs(m, users);
            List<VideoForFavorite> f = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : favs.entrySet()) {
                VideoForFavorite sFF = new VideoForFavorite();
                sFF.setNr(entry.getValue());
                sFF.setTitle(entry.getKey());
                f.add(sFF);
            }
            if (sortType.equals("asc")) {
                f.sort(VideoForFavorite.getUp().thenComparing(VideoForFavorite.getUpName()));
            } else {
                f.sort(VideoForFavorite.getDown().thenComparing(VideoForFavorite.getDownName()));
            }
            int c = 0;
            int contor = 0;
            for (VideoForFavorite entry : f) {
                contor++;
                if (entry.getNr() != 0) {
                    c++;
                    if (c == no || f.size() == contor) {
                        mess = mess + entry.getTitle();
                        mess = mess + "]";
                        return mess;
                    } else {
                        mess = mess + entry.getTitle() + ", ";
                    }
                }
            }
        } else if (genres.get(0) != null) {
            ArrayList<SerialInputData> mFinal = sortByGenre(genres, series);
            Map<String, Integer> favs = listFavs(mFinal, users);

            List<VideoForFavorite> f = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : favs.entrySet()) {
                VideoForFavorite sFF = new VideoForFavorite();
                sFF.setNr(entry.getValue());
                sFF.setTitle(entry.getKey());
                f.add(sFF);
            }

            if (sortType.equals("asc")) {
                f.sort(VideoForFavorite.getUp().thenComparing(VideoForFavorite.getUpName()));
            } else {
                f.sort(VideoForFavorite.getDown().thenComparing(VideoForFavorite.getDownName()));
            }
            int c = 0;
            int contor = 0;
            for (VideoForFavorite entry : f) {
                contor++;
                if (entry.getNr() != 0) {
                    c++;
                    if (c == no || f.size() == contor) {
                        mess = mess + entry.getTitle();
                        mess = mess + "]";
                        return mess;
                    } else {
                        mess = mess + entry.getTitle() + ", ";
                    }
                }
            }
        } else {
            Map<String, Integer> favs = listFavs(series, users);
            List<VideoForFavorite> f = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : favs.entrySet()) {
                VideoForFavorite sFF = new VideoForFavorite();
                sFF.setNr(entry.getValue());
                sFF.setTitle(entry.getKey());
                f.add(sFF);
            }
            if (sortType.equals("asc")) {
                f.sort(VideoForFavorite.getUp().thenComparing(VideoForFavorite.getUpName()));
            } else {
                f.sort(VideoForFavorite.getDown().thenComparing(VideoForFavorite.getDownName()));
            }
            int c = 0;
            int contor = 0;
            for (VideoForFavorite entry : f) {
                contor++;
                if (entry.getNr() != 0) {
                    c++;
                    if (c == no || f.size() == contor) {
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
