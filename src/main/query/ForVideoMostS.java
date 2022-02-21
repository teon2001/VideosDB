package main.query;

import fileio.SerialInputData;
import fileio.UserInputData;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class ForVideoMostS {

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
     * list de seriale cu multe vizualizari
     */
    public ArrayList<SerialInputData> listSeries(final List<UserInputData> users,
                                                 final List<SerialInputData> series) {
        ArrayList<SerialInputData> li = new ArrayList<>();
        for (SerialInputData s : series) {
            int sumPerSerial = 0;
            for (UserInputData u : users) {
                if (isInHistory(u, s.getTitle()) == 1) {
                    sumPerSerial = sumPerSerial + getView(u, s.getTitle());
                }
            }
            s.setNrView(sumPerSerial);
            li.add(s);
        }
        return li;
    }

    /**
     * @param series
     * @param users
     * @param filters
     * @param sortType
     * @param no
     * @return
     */
    public String queryVideoMostS(final List<SerialInputData> series,
                                  final List<UserInputData> users,
                                  final List<List<String>> filters,
                                  final String sortType, final int no) {
        String mess = "Query result: [";
        List<String> years = filters.get(0);
        List<String> genres = filters.get(1);
        List<SerialInputData> goodSeries = listSeries(users, series);

        int year = 0;
        if (years.get(0) != null) {
            year = Integer.parseInt(years.get(0));
        }
        if (years.get(0) != null && genres.get(0) != null) {
            ArrayList<SerialInputData> m = sortByYear(year, goodSeries);
            ArrayList<SerialInputData> mFinal = sortByGenre(genres, m);


            if (sortType.equals("asc")) {
                mFinal.sort(SerialInputData.getUpV().
                        thenComparing(SerialInputData.getUpName()));
            } else {
                mFinal.sort(SerialInputData.getDownV().
                        thenComparing(SerialInputData.getDownName()));
            }
            int c = 0;
            int contor = 0;
            for (SerialInputData entry : mFinal) {
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
            ArrayList<SerialInputData> m = sortByYear(year, goodSeries);

            if (sortType.equals("asc")) {
                m.sort(SerialInputData.getUpV().
                        thenComparing(SerialInputData.getUpName()));
            } else {
                m.sort(SerialInputData.getDownV().
                        thenComparing(SerialInputData.getDownName()));
            }
            int c = 0;
            int contor = 0;
            for (SerialInputData entry : m) {
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
            ArrayList<SerialInputData> mFinal = sortByGenre(genres, goodSeries);

            if (sortType.equals("asc")) {
                mFinal.sort(SerialInputData.getUpV().thenComparing(SerialInputData.getUpName()));
            } else {
                mFinal.sort(SerialInputData.getDownV().
                        thenComparing(SerialInputData.getDownName()));
            }
            int c = 0;
            int contor = 0;
            for (SerialInputData entry : mFinal) {
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
                goodSeries.sort(SerialInputData.getUpV().
                        thenComparing(SerialInputData.getUpName()));
            } else {
                goodSeries.sort(SerialInputData.getDownV().
                        thenComparing(SerialInputData.getDownName()));
            }
            int c = 0;
            int contor = 0;
            for (SerialInputData entry : goodSeries) {
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
