package main.recommendation;

import java.util.Comparator;
import java.util.List;

public class VideoMS {
    // clasa care trateaza serialele si filmele la fel ca fiind video.uri
    private String titleV;
    private Double ratingV;
    private List<String> genreV;
    private Integer popularityNumberForFavorite;
    /**
     * getter pentru titlu
     */
    public String getTitleV() {
        return titleV;
    }
    /**
     * setter pentru titlu
     */
    public void setTitleV(final String titleV) {
        this.titleV = titleV;
    }
    /**
     * getter pt rating
     */
    public Double getRatingV() {
        return ratingV;
    }
    /**
     * setter pentru rating
     */
    public void setRatingV(final Double ratingV) {
        this.ratingV = ratingV;
    }
    /**
     * getter pentru gen
     */
    public List<String> getGenreV() {
        return genreV;
    }
    /**
     * setter pt gen
     */
    public void setGenreV(final List<String> genreV) {
        this.genreV = genreV;
    }
    /**
     * getter pentru cat de popular este
     */
    public Integer getPopularityNumberForFavorite() {
        return popularityNumberForFavorite;
    }
    /**
     * analog setter
     */
    public void setPopularityNumberForFavorite(final
                                               Integer popularityNumberForFavorite) {
        this.popularityNumberForFavorite = popularityNumberForFavorite;
    }

    private static Comparator<VideoMS> up = new Comparator<VideoMS>() {
        @Override
        public int compare(final VideoMS o1, final VideoMS o2) {
            return Double.compare(o1.getRatingV(), o2.getRatingV());
        }
    };

    private static Comparator<VideoMS> down = new Comparator<VideoMS>() {
        @Override
        public int compare(final VideoMS o1, final VideoMS o2) {
            return Double.compare(o2.getRatingV(), o1.getRatingV());
        }
    };
    private static Comparator<VideoMS> upName = new Comparator<VideoMS>() {
        @Override
        public int compare(final VideoMS o1, final VideoMS o2) {
            return o1.getTitleV().compareTo(o2.getTitleV());
        }
    };

    private static Comparator<VideoMS> downName = new Comparator<VideoMS>() {
        @Override
        public int compare(final VideoMS o1, final VideoMS o2) {
            return o2.getTitleV().compareTo(o1.getTitleV());
        }
    };
    private static Comparator<VideoMS> upPopularity = new Comparator<VideoMS>() {
        @Override
        public int compare(final VideoMS o1, final VideoMS o2) {
            return Double.compare(o1.getPopularityNumberForFavorite(),
                    o2.getPopularityNumberForFavorite());
        }
    };

    private static Comparator<VideoMS> downPopularity = new Comparator<VideoMS>() {
        @Override
        public int compare(final VideoMS o1, final VideoMS o2) {
            return Double.compare(o2.getPopularityNumberForFavorite(),
                    o1.getPopularityNumberForFavorite());
        }
    };

    public static Comparator<VideoMS> getUp() {
        return up;
    }

    public static Comparator<VideoMS> getDown() {
        return down;
    }

    public static Comparator<VideoMS> getUpName() {
        return upName;
    }

    public static Comparator<VideoMS> getDownName() {
        return downName;
    }

    public static Comparator<VideoMS> getUpPopularity() {
        return upPopularity;
    }

    public static Comparator<VideoMS> getDownPopularity() {
        return downPopularity;
    }
}
