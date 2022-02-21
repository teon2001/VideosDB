package main.recommendation;

import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;

import java.util.ArrayList;
import java.util.List;

public class RecoBestUnseen {
    /**
     * Intoarce entitatea user
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
     * converteste serialele si filmele in video.uri
     */
    public List<VideoMS> listMStoVideo(final List<MovieInputData> movies,
                                       final List<SerialInputData> series) {
        List<VideoMS> list = new ArrayList<>();
        for (MovieInputData m : movies) {
            VideoMS video = new VideoMS();
            video.setTitleV(m.getTitle());
            video.setRatingV(m.getAverageRating());
            video.setGenreV(m.getGenres());
            list.add(video);
        }
        for (SerialInputData s : series) {
            VideoMS video = new VideoMS();
            video.setTitleV(s.getTitle());
            video.setRatingV(s.getAverageRating());
            video.setGenreV(s.getGenres());
            list.add(video);
        }
        return list;
    }
    /**
     * il alege pe cel mai bun pe care nu l-a vazut
     */
    public String choice(final UserInputData u, final List<VideoMS> list) {
        String n = null;
        for (VideoMS v : list) {
            if (!u.getHistory().containsKey(v.getTitleV())) {
                n = v.getTitleV();
               System.out.println("titlu " + v.getTitleV() + " nr " + v.getRatingV());
                return n;
            }
        }
        return null;
    }
    /**
     * @param name
     * @param users
     * @param movies
     * @param series
     * @return
     */
    public String recommendationBUnseen(final String name, final List<UserInputData> users,
                                        final List<MovieInputData> movies,
                                        final List<SerialInputData> series) {
        String mess = "BestRatedUnseenRecommendation result: ";
        UserInputData u = getUser(name, users);
        List<VideoMS> list = listMStoVideo(movies, series);

        List<VideoMS> l1 = new ArrayList<>();
        List<VideoMS> l2 = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getRatingV() != 0 && !list.get(i).getRatingV().isNaN()) {
                l1.add(list.get(i));
            } else {
                l2.add(list.get(i));
            }
        }

        l1.sort(VideoMS.getDown());

        String aux = choice(u, l1);
        if (aux == null) {
            if (l2.size() != 0) {
                for (int i = 0; i < l2.size(); i++) {
                    if (!u.getHistory().containsKey(l2.get(i).getTitleV())) {
                        return mess + l2.get(i).getTitleV();
                    }
                }
            } else {
                return "BestRatedUnseenRecommendation cannot be applied!";
            }
        } else {
            return mess + aux;
        }
        return "BestRatedUnseenRecommendation cannot be applied!";
    }
}
