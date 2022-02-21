package fileio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


/**
 * Information about a movie, retrieved from parsing the input test files
 * <p>
 * DO NOT MODIFY
 */
public final class MovieInputData extends fileio.ShowInput {
    /**
     * Duration in minutes of a season
     */
    private final int duration;
    /**
     * Mapa care contine un user si nota pe care a dat-o filmului
     */
    private Map<String, Double> userToGrade;
    /**
     * Numarul de vizualizari per film
     */
    private int nrView;

    public MovieInputData(final String title, final ArrayList<String> cast,
                          final ArrayList<String> genres, final int year,
                          final int duration) {
        super(title, year, cast, genres);
        this.duration = duration;
        this.userToGrade = new HashMap<>();
        this.nrView = 0;
    }

    public int getDuration() {
        return duration;
    }

    public Map<String, Double> getUserToGrade() {
        return userToGrade;
    }

    public void setUserToGrade(final Map<String, Double> userToGrade) {
        this.userToGrade = userToGrade;
    }
    /**
     * Medie rating film
     */
    public Double getAverageRating() {
        double sum = 0;
        int contor = 0;
        for (Map.Entry<String, Double> entry : getUserToGrade().entrySet()) {
            contor++;
            sum = sum + entry.getValue();
        }
        if (contor != 0) {
            sum = sum / contor;
            return sum;
        }
        return Double.NaN;
    }

    public int getNrView() {
        return nrView;
    }

    public void setNrView(final int nrView) {
        this.nrView = nrView;
    }

    @Override
    public String toString() {
        return "MovieInputData{" + "title= "
                + super.getTitle() + "year= "
                + super.getYear() + "duration= "
                + duration + "cast {"
                + super.getCast() + " }\n"
                + "genres {" + super.getGenres() + " }\n ";
    }
    private static Comparator<MovieInputData> up = new Comparator<MovieInputData>() {
        @Override
        public int compare(final MovieInputData o1, final MovieInputData o2) {
            return Double.compare(o1.getAverageRating(), o2.getAverageRating());
        }
    };

    private static Comparator<MovieInputData> down = new Comparator<MovieInputData>() {
        @Override
        public int compare(final MovieInputData o1, final MovieInputData o2) {
            return Double.compare(o2.getAverageRating(), o1.getAverageRating());
        }
    };
    private static Comparator<MovieInputData> upV = new Comparator<MovieInputData>() {
        @Override
        public int compare(final MovieInputData o1, final MovieInputData o2) {
            return Double.compare(o1.getNrView(), o2.getNrView());
        }
    };

    private static Comparator<MovieInputData> downV = new Comparator<MovieInputData>() {
        @Override
        public int compare(final MovieInputData o1, final MovieInputData o2) {
            return Double.compare(o2.getNrView(), o1.getNrView());
        }
    };
    private static Comparator<MovieInputData> upName = new Comparator<MovieInputData>() {
        @Override
        public int compare(final MovieInputData o1, final MovieInputData o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    };

    private static Comparator<MovieInputData> downName = new Comparator<MovieInputData>() {
        @Override
        public int compare(final MovieInputData o1, final MovieInputData o2) {
            return o2.getTitle().compareTo(o1.getTitle());
        }
    };

    public static Comparator<MovieInputData> getUp() {
        return up;
    }

    public static Comparator<MovieInputData> getDown() {
        return down;
    }

    public static Comparator<MovieInputData> getUpV() {
        return upV;
    }

    public static Comparator<MovieInputData> getDownV() {
        return downV;
    }

    public static Comparator<MovieInputData> getUpName() {
        return upName;
    }

    public static Comparator<MovieInputData> getDownName() {
        return downName;
    }
}
