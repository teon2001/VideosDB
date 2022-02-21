package main.recommendation;

import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;

import java.util.List;

public class RecoStandard {
    /**
     * @param name
     * @param users
     * @return
     */
    public UserInputData getUser(final String name,
                                 final List<UserInputData> users) {
        for (UserInputData u : users) {
            if (u.getUsername().equals(name)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Genereaza un video nevazut
     * cautand mai inati in movies si apoi in series
     */
    public String giveVideo(final UserInputData u,
                            final List<MovieInputData> movies,
                            final List<SerialInputData> series) {
        String video = null;
        for (MovieInputData m : movies) {
            if (!u.getHistory().containsKey(m.getTitle())) {
                video = m.getTitle();
                break;
            }
        }
        if (video == null) {
            for (SerialInputData s : series) {
                if (!u.getHistory().containsKey(s.getTitle())) {
                    video = s.getTitle();
                    break;
                }
            }
        }
        return video;
    }
    /**
     * @param name
     * @param users
     * @param movies
     * @param series
     * @return
     */
    public String recommendationStdr(final String name,
                                     final List<UserInputData> users,
                                     final List<MovieInputData> movies,
                                     final List<SerialInputData> series) {
        String mess = "StandardRecommendation result: ";
        UserInputData u = getUser(name, users);
        String aux = giveVideo(u, movies, series);
        if (aux != null) {
            return mess + giveVideo(u, movies, series);
        } else {
            return "StandardRecommendation cannot be applied!";
        }
    }
}
