package fileio;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

/**
 * Information about an user, retrieved from parsing the input test files
 * <p>
 * DO NOT MODIFY
 */
public final class UserInputData {
    /**
     * User's username
     */
    private final String username;
    /**
     * Subscription Type
     */
    private final String subscriptionType;
    /**
     * The history of the movies seen
     */
    private final Map<String, Integer> history;
    /**
     * Movies added to favorites
     */
    private final ArrayList<String> favoriteMovies;
    /**
     * rated movies
     */
    private final ArrayList<MovieInputData> ratedMovies;
    /**
     * rated series
     */
    private final ArrayList<SerialInputData> ratedSeries;
    /**
     * numarul de note pe care le-a dat video.urilor
     */
    private int noGiveGrades;

    public UserInputData(final String username, final String subscriptionType,
                         final Map<String, Integer> history,
                         final ArrayList<String> favoriteMovies) {
        this.username = username;
        this.subscriptionType = subscriptionType;
        this.favoriteMovies = favoriteMovies;
        this.history = history;
        this.ratedSeries = new ArrayList<>();
        this.ratedMovies = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public Map<String, Integer> getHistory() {
        return history;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public ArrayList<String> getFavoriteMovies() {
        return favoriteMovies;
    }

    public ArrayList<MovieInputData> getRatedMovies() {
        return ratedMovies;
    }

    public ArrayList<SerialInputData> getRatedSeries() {
        return ratedSeries;
    }

    public int getNoGiveGrades() {
        return noGiveGrades;
    }

    public void setNoGiveGrades(final int noGiveGrades) {
        this.noGiveGrades = noGiveGrades;
    }

    private static Comparator<UserInputData> up = new Comparator<UserInputData>() {
        @Override
        public int compare(final UserInputData o1, final UserInputData o2) {
            return Double.compare(o1.getNoGiveGrades(), o2.getNoGiveGrades());
        }
    };

    private static Comparator<UserInputData> down = new Comparator<UserInputData>() {
        @Override
        public int compare(final UserInputData o1, final UserInputData o2) {
            return Double.compare(o2.getNoGiveGrades(), o1.getNoGiveGrades());
        }
    };
    private static Comparator<UserInputData> upName = new Comparator<UserInputData>() {
        @Override
        public int compare(final UserInputData o1, final UserInputData o2) {
            return o1.getUsername().compareTo(o2.getUsername());
        }
    };

    private static Comparator<UserInputData> downName = new Comparator<UserInputData>() {
        @Override
        public int compare(final UserInputData o1, final UserInputData o2) {
            return o2.getUsername().compareTo(o1.getUsername());
        }
    };

    public static Comparator<UserInputData> getUp() {
        return up;
    }

    public static Comparator<UserInputData> getDown() {
        return down;
    }

    public static Comparator<UserInputData> getUpName() {
        return upName;
    }

    public static Comparator<UserInputData> getDownName() {
        return downName;
    }

    @Override
    public String toString() {
        return "UserInputData{" + "username='"
                + username + '\'' + ", subscriptionType='"
                + subscriptionType + '\'' + ", history="
                + history + ", favoriteMovies="
                + favoriteMovies + '}';
    }
}
