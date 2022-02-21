package main.query;

import fileio.SerialInputData;
import fileio.UserInputData;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;


public class ForVideoLongestS {

    /**
     * Analog
     * @param year
     * @param series
     * @return
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
     * Analog
     * @param genres
     * @param series
     * @return
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
     * Analog
     * @param series
     * @return
     */
    public Map<String, Integer> listFavs(final List<SerialInputData> series) {
        Map<String, Integer> favs = new HashMap<>();
        for (SerialInputData s : series) {
            int nr = s.getAllDuration();
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
    public String queryVideoLongestS(final List<SerialInputData> series,
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

        // In mod analog (avem aceleasi 4 cazuri)

        if (years.get(0) != null && genres.get(0) != null) {
            ArrayList<SerialInputData> m = sortByYear(year, series);
            ArrayList<SerialInputData> mFinal = sortByGenre(genres, m);
            Map<String, Integer> d = listFavs(mFinal);

            List<VideoForFavorite> f = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : d.entrySet()) {
                VideoForFavorite sFF = new VideoForFavorite();
                sFF.setTime(entry.getValue());
                sFF.setTitle(entry.getKey());
                f.add(sFF);
            }
            if (sortType.equals("asc")) {
                f.sort(VideoForFavorite.getUpT());
            } else {
                f.sort(VideoForFavorite.getDownT());
            }
            int c = 0;
            int contor = 0;
            for (VideoForFavorite entry : f) {
                contor++;
                if (entry.getTime() != 0) {
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
            Map<String, Integer> d = listFavs(m);

            List<VideoForFavorite> f = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : d.entrySet()) {
                VideoForFavorite sFF = new VideoForFavorite();
                sFF.setTime(entry.getValue());
                sFF.setTitle(entry.getKey());
                f.add(sFF);
            }

            if (sortType.equals("asc")) {
                f.sort(VideoForFavorite.getUpT().thenComparing(VideoForFavorite.getUpName()));
            } else {
                f.sort(VideoForFavorite.getDownT().thenComparing(VideoForFavorite.getDownName()));
            }
            int c = 0;
            int contor = 0;
            for (VideoForFavorite entry : f) {
                contor++;
                if (entry.getTime() != 0) {
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
            Map<String, Integer> d = listFavs(mFinal);

            List<VideoForFavorite> f = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : d.entrySet()) {
                VideoForFavorite sFF = new VideoForFavorite();
                sFF.setTime(entry.getValue());
                sFF.setTitle(entry.getKey());
                f.add(sFF);
            }

            if (sortType.equals("asc")) {
                f.sort(VideoForFavorite.getUpT().thenComparing(VideoForFavorite.getUpName()));
            } else {
                f.sort(VideoForFavorite.getDownT().thenComparing(VideoForFavorite.getDownName()));
            }
            int c = 0;
            int contor = 0;
            for (VideoForFavorite entry : f) {
                contor++;
                if (entry.getTime() != 0) {
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
            Map<String, Integer> d = listFavs(series);
            List<VideoForFavorite> f = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : d.entrySet()) {
                VideoForFavorite sFF = new VideoForFavorite();
                sFF.setTime(entry.getValue());
                sFF.setTitle(entry.getKey());
                f.add(sFF);
            }
            if (sortType.equals("asc")) {
                f.sort(VideoForFavorite.getUpT().thenComparing(VideoForFavorite.getUpName()));
            } else {
                f.sort(VideoForFavorite.getDownT().thenComparing(VideoForFavorite.getDownName()));
            }
            int c = 0;
            int contor = 0;
            for (VideoForFavorite entry : f) {
                contor++;
                if (entry.getTime() != 0) {
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
