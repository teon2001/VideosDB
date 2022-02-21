package fileio;

import entertainment.Season;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Information about a tv show, retrieved from parsing the input test files
 * <p>
 * DO NOT MODIFY
 */
public final class SerialInputData extends ShowInput {
    /**
     * Number of seasons
     */
    private final int numberOfSeasons;
    /**
     * Season list
     */
    private final ArrayList<Season> seasons;
    /**
     * rated series
     */
    private final ArrayList<Season> ratedSeasons;
    /**
     * numar de vizualizari per Serial
     */
    private int nrView;

    public SerialInputData(final String title, final ArrayList<String> cast,
                           final ArrayList<String> genres,
                           final int numberOfSeasons, final ArrayList<Season> seasons,
                           final int year) {
        super(title, year, cast, genres);
        this.numberOfSeasons = numberOfSeasons;
        this.seasons = seasons;
        this.ratedSeasons = new ArrayList<>();
    }

    public int getNumberSeason() {
        return numberOfSeasons;
    }

    public ArrayList<Season> getSeasons() {
        return seasons;
    }
    public ArrayList<Season> getRatedSeasons() {
        return ratedSeasons;
    }
    /**
     * medie rating per Serial
     */
    public Double getAverageRating() {
        double sum = 0.0;
        int idx = 0;
        for (Season i : getRatedSeasons()) {
            idx++;
            sum = sum + i.getAverageRatingSeason();
        }
        if (getNumberSeason() != 0) {
            sum = sum / getNumberSeason();
            return sum;
        }
        return Double.NaN;
    }
    /**
     * Durata per serial = suma (durata sezoane)
     */
    public Integer getAllDuration() {
        int time = 0;
        for (Season season : getSeasons()) {
            time = time + season.getDuration();
        }
        return time;
    }

    public int getNrView() {
        return nrView;
    }

    public void setNrView(final int nrView) {
        this.nrView = nrView;
    }

    private static Comparator<SerialInputData> up = new Comparator<SerialInputData>() {
        @Override
        public int compare(final SerialInputData o1, final SerialInputData o2) {
            return Double.compare(o1.getAverageRating(), o2.getAverageRating());
        }
    };

    private static Comparator<SerialInputData> down = new Comparator<SerialInputData>() {
        @Override
        public int compare(final SerialInputData o1, final SerialInputData o2) {
            return Double.compare(o2.getAverageRating(), o1.getAverageRating());
        }
    };
    private static Comparator<SerialInputData> upV = new Comparator<SerialInputData>() {
        @Override
        public int compare(final SerialInputData o1, final SerialInputData o2) {
            return Double.compare(o1.getNrView(), o2.getNrView());
        }
    };

    private static Comparator<SerialInputData> downV = new Comparator<SerialInputData>() {
        @Override
        public int compare(final SerialInputData o1, final SerialInputData o2) {
            return Double.compare(o2.getNrView(), o1.getNrView());
        }
    };
    private static Comparator<SerialInputData> upName = new Comparator<SerialInputData>() {
        @Override
        public int compare(final SerialInputData o1, final SerialInputData o2) {
            return o1.getTitle().compareTo(o2.getTitle());
        }
    };

    private static Comparator<SerialInputData> downName = new Comparator<SerialInputData>() {
        @Override
        public int compare(final SerialInputData o1, final SerialInputData o2) {
            return o2.getTitle().compareTo(o1.getTitle());
        }
    };

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public static Comparator<SerialInputData> getUp() {
        return up;
    }

    public static Comparator<SerialInputData> getDown() {
        return down;
    }

    public static Comparator<SerialInputData> getUpV() {
        return upV;
    }

    public static Comparator<SerialInputData> getDownV() {
        return downV;
    }

    public static Comparator<SerialInputData> getUpName() {
        return upName;
    }

    public static Comparator<SerialInputData> getDownName() {
        return downName;
    }

    @Override
    public String toString() {
        return "SerialInputData{" + " title= "
                + super.getTitle() + " " + " year= "
                + super.getYear() + " cast {"
                + super.getCast() + " }\n" + " genres {"
                + super.getGenres() + " }\n "
                + " numberSeason= " + numberOfSeasons
                + ", seasons=" + seasons + "\n\n" + '}';
    }
}
