package main.recommendation;

import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RecoSearch {
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
     * @param movies
     * @param series
     * @return
     */
    public List<VideoMS> listMStoVideo(final List<MovieInputData> movies,
                                       final List<SerialInputData> series) {
        List<VideoMS> list = new ArrayList<>();
        for (MovieInputData m : movies) {
            VideoMS video = new VideoMS();
            video.setTitleV(m.getTitle());
            if (m.getAverageRating().isNaN()) {
                video.setRatingV(0.0);
            } else {
                video.setRatingV(m.getAverageRating());
            }
            video.setGenreV(m.getGenres());
            list.add(video);
        }
        for (SerialInputData s : series) {
            VideoMS video = new VideoMS();
            video.setTitleV(s.getTitle());
            if (s.getAverageRating().isNaN()) {
                video.setRatingV(0.0);
            } else {
                video.setRatingV(s.getAverageRating());
            }
            video.setGenreV((s.getGenres()));
            list.add(video);
        }
        return list;
    }
    /**
     * @param movies
     * @param series
     * @param genre
     * @return
     */
    public List<VideoMS> allVideosFromAGenre(final List<MovieInputData> movies,
                                             final List<SerialInputData> series,
                                             final String genre) {
        List<VideoMS> videos = listMStoVideo(movies, series);
        List<VideoMS> videoWithOneGenre = new ArrayList<>();

        for (VideoMS v : videos) {
            if (v.getGenreV().contains(genre)) {
                videoWithOneGenre.add(v);
            }
        }
        return videoWithOneGenre;
    }
    /**
     * @return lista cu toate genurile
     */
    public List<String> getAllGenres() {
        List<String> li = new ArrayList<>();
        li.add("Action");
        li.add("Adventure");
        li.add("Drama");
        li.add("Comedy");
        li.add("Crime");
        li.add("Romance");
        li.add("War");
        li.add("History");
        li.add("Thriller");
        li.add("Mystery");
        li.add("Family");
        li.add("Horror");
        li.add("Fantasy");
        li.add("Science Fiction");
        li.add("Action & Adventure");
        li.add("Sci-Fi & Fantasy");
        li.add("Animation");
        li.add("Kids");
        li.add("Western");
        li.add("TV Movie");
        return li;
    }

    /**
     * User's username
     */
    public String recommendationSearch(final String name,
                                       final List<UserInputData> users,
                                       final List<MovieInputData> movies,
                                       final List<SerialInputData> series,
                                       final String genre) {
        String mess = "SearchRecommendation result: [";
        UserInputData u = getUser(name, users);
        if (!u.getSubscriptionType().equals("PREMIUM")) {
            return "SearchRecommendation cannot be applied!";
        }
        List<String> allGenres = getAllGenres();
        if (!allGenres.contains(genre)) {
            return "SearchRecommendation cannot be applied!";
        }
        List<VideoMS> list = allVideosFromAGenre(movies, series, genre);
        Collections.sort(list, VideoMS.getUp().thenComparing(VideoMS.getUpName()));
        //dupa ce sorteaza, verifica sa nu fie in istoric si nici la
        //favorite pentru acel user
        for (VideoMS v : list) {
            if (!u.getHistory().containsKey(v.getTitleV())
                    && !u.getFavoriteMovies().contains(v.getTitleV())) {
                mess = mess + v.getTitleV() + ", ";
            }
        }
        if (mess.lastIndexOf(" ") == mess.length() - 1) {
            mess = mess.substring(0, mess.length() - 2);
            return mess + "]";
        }
        return "SearchRecommendation cannot be applied!";
    }
}
