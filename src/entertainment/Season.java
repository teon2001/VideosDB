package entertainment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Information about a season of a tv show
 * <p>
 * DO NOT MODIFY
 */
public final class Season {
    /**
     * Number of current season
     */
    private final int currentSeason;
    /**
     * Duration in minutes of a season
     */
    private int duration;
    /**
     * List of ratings for each season
     */
    private List<Double> ratings;
    /**
     * Mapa care contine un user si nota pe care a dat-o
     */
    private Map<String, Double> usertoGrade;

    public Season(final int currentSeason, final int duration) {
        this.currentSeason = currentSeason;
        this.duration = duration;
        this.ratings = new ArrayList<>();
        this.usertoGrade = new HashMap<>();
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(final int duration) {
        this.duration = duration;
    }

    public List<Double> getRatings() {
        return ratings;
    }

    public int getCurrentSeason() {
        return currentSeason;
    }
    /**
     * Media pentru un sezon
     */
    public Double getAverageRatingSeason() {
        Double sum = 0.0;
        for (Double iter : getRatings()) {
            sum = sum + iter;
        }
        sum = sum / getRatings().size();
        return sum;
    }

    public Map<String, Double> getUserToGrade() {
        return usertoGrade;
    }

    @Override
    public String toString() {
        return "Episode{"
                + "currentSeason="
                + currentSeason
                + ", duration="
                + duration
                + '}';
    }
}

