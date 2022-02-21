package main.query;

import java.util.Comparator;

public class VideoForFavorite {
    //clasa care trateaza serialele si filmele la fel ca fiind video.uri

    private String title;
    /**
     * de cate ori apare in lista favorite a userilor
     */
    private Integer nr;
    private Integer time;
    /**
     * getter pentru titlu
     */
    public String getTitle() {
        return title;
    }
    /**
     * setter pt titlu
     */
    public void setTitle(final String title) {
        this.title = title;
    }
    /**
     * getter pentru numarl de aparitii
     */
    public Integer getNr() {
        return nr;
    }
    /**
     * setter pt numarul de aparitii
     */
    public void setNr(final Integer nr) {
        this.nr = nr;
    }
    /**
     * getter pentru durata unui video
     */
    public Integer getTime() {
        return time;
    }
    /**
     * setter pentru durata unui video
     */
    public void setTime(final Integer time) {
        this.time = time;
    }

    private static Comparator<VideoForFavorite> up = new Comparator<VideoForFavorite>() {
        @Override
        public int compare(final VideoForFavorite o1, final VideoForFavorite o2) {
            return Double.compare(o1.getNr(), o2.getNr());
        }
    };

    private static Comparator<VideoForFavorite> down = new Comparator<VideoForFavorite>() {
        @Override
        public int compare(final VideoForFavorite o1, final VideoForFavorite o2) {
            return Double.compare(o2.getNr(), o1.getNr());
        }
    };
    private static Comparator<VideoForFavorite> upT = new Comparator<VideoForFavorite>() {
        @Override
        public int compare(final VideoForFavorite o1, final VideoForFavorite o2) {
            return Double.compare(o1.getTime(), o2.getTime());
        }
    };

    private static Comparator<VideoForFavorite> downT = new Comparator<VideoForFavorite>() {
        @Override
        public int compare(final VideoForFavorite o1, final VideoForFavorite o2) {
            return Double.compare(o2.getTime(), o1.getTime());
        }
    };
    private static Comparator<VideoForFavorite> upName = new Comparator<VideoForFavorite>() {
        @Override
        public int compare(final VideoForFavorite o1, final VideoForFavorite o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    };

    private static Comparator<VideoForFavorite> downName = new Comparator<VideoForFavorite>() {
        @Override
        public int compare(final VideoForFavorite o1, final VideoForFavorite o2) {
            return o2.getTitle().compareTo(o1.getTitle());
        }
    };

    public static Comparator<VideoForFavorite> getUp() {
        return up;
    }

    public static Comparator<VideoForFavorite> getDown() {
        return down;
    }

    public static Comparator<VideoForFavorite> getUpT() {
        return upT;
    }

    public static Comparator<VideoForFavorite> getDownT() {
        return downT;
    }

    public static Comparator<VideoForFavorite> getUpName() {
        return upName;
    }

    public static Comparator<VideoForFavorite> getDownName() {
        return downName;
    }
}
