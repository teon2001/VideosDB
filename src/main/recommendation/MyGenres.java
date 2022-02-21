package main.recommendation;

import java.util.Comparator;

public class MyGenres {
    // clasa pentru gen:
    // aceasta retine genul si numarul de vizionari per gen

    private String genre;
    private int noViewsOfVideos;
    /**
     * @return gen
     */
    public String getGenre() {
        return genre;
    }
    /**
     * @param genre
     */
    public void setGenre(final String genre) {
        this.genre = genre;
    }
    /**
     * @return nr de vizionari totale
     */
    public int getNoViewsOfVideos() {
        return noViewsOfVideos;
    }
    /**
     * @param noViewsOfVideos
     */
    public void setNoViewsOfVideos(final int noViewsOfVideos) {
        this.noViewsOfVideos = noViewsOfVideos;
    }

    private static Comparator<MyGenres> up = new Comparator<MyGenres>() {
        @Override
        public int compare(final MyGenres o1, final MyGenres o2) {
            return Double.compare(o1.getNoViewsOfVideos(), o2.getNoViewsOfVideos());
        }
    };

    private static Comparator<MyGenres> down = new Comparator<MyGenres>() {
        @Override
        public int compare(final MyGenres o1, final MyGenres o2) {
            return Double.compare(o2.getNoViewsOfVideos(), o1.getNoViewsOfVideos());
        }
    };

    public static Comparator<MyGenres> getUp() {
        return up;
    }

    public static Comparator<MyGenres> getDown() {
        return down;
    }
}
