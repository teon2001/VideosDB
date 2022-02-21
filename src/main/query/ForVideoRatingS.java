package main.query;

import fileio.SerialInputData;

import java.util.ArrayList;
import java.util.List;

public class ForVideoRatingS {

    /**
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
     * @param sortMType
     * @param filters
     * @param series
     * @param no
     * @return
     */
    public String queryVideoRatingS(final String sortMType,
                                    final List<List<String>> filters,
                                    final List<SerialInputData> series,
                                    final Integer no) {
        String mess = "Query result: [";
        List<String> years = filters.get(0);
        List<String> genres = filters.get(1);
        int year = 0;
        if (years.get(0) != null) {
            year = Integer.parseInt(years.get(0));
        }
        if (years.get(0) != null && genres.get(0) != null) {
            ArrayList<SerialInputData> m = sortByYear(year, series);
            ArrayList<SerialInputData> mFinal = sortByGenre(genres, m);
            if (sortMType.equals("asc")) {
                mFinal.sort(SerialInputData.getUp());
            } else {
                mFinal.sort(SerialInputData.getDown());
            }

            int c = 0;
            int contor = 0;
            for (SerialInputData entry : mFinal) {
                contor++;
                if (!entry.getAverageRating().isNaN() && entry.getAverageRating() != 0) {
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
            ArrayList<SerialInputData> m = sortByYear(year, series);
            if (sortMType.equals("asc")) {
                m.sort(SerialInputData.getUp());
            } else {
                m.sort(SerialInputData.getDown());
            }

            int c = 0;
            int contor = 0;
            for (SerialInputData entry : m) {
                contor++;
                if (!entry.getAverageRating().isNaN() && entry.getAverageRating() != 0) {
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
            ArrayList<SerialInputData> mFinal = sortByGenre(genres, series);
            if (sortMType.equals("asc")) {
                mFinal.sort(SerialInputData.getUp());
            } else {
                mFinal.sort(SerialInputData.getDown());
            }

            int c = 0;
            int contor = 0;
            for (SerialInputData entry : mFinal) {
                contor++;
                if (!entry.getAverageRating().isNaN() && entry.getAverageRating() != 0) {
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
            if (sortMType.equals("asc")) {
                series.sort(SerialInputData.getUp());
            } else {
                series.sort(SerialInputData.getDown());
            }

            int c = 0;
            int contor = 0;
            for (SerialInputData entry : series) {
                contor++;
                if (!entry.getAverageRating().isNaN() && entry.getAverageRating() != 0) {
                    c++;
                    if (c == no || series.size() == contor) {
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
