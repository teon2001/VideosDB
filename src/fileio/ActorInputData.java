package fileio;

import actor.ActorsAwards;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

/**
 * Information about an actor, retrieved from parsing the input test files
 * <p>
 * DO NOT MODIFY
 */
public final class ActorInputData {
    /**
     * actor name
     */
    private String name;
    /**
     * description of the actor's career
     */
    private String careerDescription;
    /**
     * videos starring actor
     */
    private ArrayList<String> filmography;
    /**
     * awards won by the actor
     */
    private Map<ActorsAwards, Integer> awards;
    /**
     * rating-ul per Actor
     */
    private Double ratingActor;

    public ActorInputData(final String name, final String careerDescription,
                          final ArrayList<String> filmography,
                          final Map<ActorsAwards, Integer> awards) {
        this.name = name;
        this.careerDescription = careerDescription;
        this.filmography = filmography;
        this.awards = awards;
        this.ratingActor = 0.0;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public ArrayList<String> getFilmography() {
        return filmography;
    }

    public void setFilmography(final ArrayList<String> filmography) {
        this.filmography = filmography;
    }

    public Map<ActorsAwards, Integer> getAwards() {
        return awards;
    }

    public String getCareerDescription() {
        return careerDescription;
    }

    public void setCareerDescription(final String careerDescription) {
        this.careerDescription = careerDescription;
    }

    public Double getRatingActor() {
        return ratingActor;
    }

    public void setRatingActor(final Double ratingActor) {
        this.ratingActor = ratingActor;
    }

    private static Comparator<ActorInputData> up = new Comparator<ActorInputData>() {
        @Override
        public int compare(final ActorInputData o1, final ActorInputData o2) {
            return Double.compare(o1.getRatingActor(), o2.getRatingActor());
        }
    };

    private static Comparator<ActorInputData> down = new Comparator<ActorInputData>() {
        @Override
        public int compare(final ActorInputData o1, final ActorInputData o2) {
            return Double.compare(o2.getRatingActor(), o1.getRatingActor());
        }
    };

    private static Comparator<ActorInputData> upName = new Comparator<ActorInputData>() {
        @Override
        public int compare(final ActorInputData o1, final ActorInputData o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    private static Comparator<ActorInputData> downName = new Comparator<ActorInputData>() {
        @Override
        public int compare(final ActorInputData o1, final ActorInputData o2) {
            return o2.getName().compareTo(o1.getName());
        }
    };

    public static Comparator<ActorInputData> getUp() {
        return up;
    }

    public static Comparator<ActorInputData> getDown() {
        return down;
    }

    public static Comparator<ActorInputData> getUpName() {
        return upName;
    }

    public static Comparator<ActorInputData> getDownName() {
        return downName;
    }

    @Override
    public String toString() {
        return "ActorInputData{"
                + "name='" + name + '\''
                + ", careerDescription='"
                + careerDescription + '\''
                + ", filmography=" + filmography + '}';
    }
}
