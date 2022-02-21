package main.recommendation;

import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;

import java.util.ArrayList;
import java.util.List;

public class RecoFavorite {
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
     * converteste la video orice serial sau film
     */
    public List<VideoMS> listMStoVideo(final List<MovieInputData> movies,
                                       final List<SerialInputData> series) {
        List<VideoMS> list = new ArrayList<>();
        for (MovieInputData m : movies) {
            VideoMS video = new VideoMS();
            video.setTitleV(m.getTitle());
            video.setRatingV(m.getAverageRating());
            video.setGenreV(m.getGenres());
            video.setPopularityNumberForFavorite(0);
            list.add(video);
        }
        for (SerialInputData s : series) {
            VideoMS video = new VideoMS();
            video.setTitleV(s.getTitle());
            video.setRatingV(s.getAverageRating());
            video.setGenreV(s.getGenres());
            video.setPopularityNumberForFavorite(0);
            list.add(video);
        }
        return list;
    }
    /**
     * Calculeaza de cate ori apare la favorite un video
     */
    public int givePopularityNumber(final VideoMS v,
                                    final List<UserInputData> users) {
        int nr = 0;
        for (UserInputData u : users) {
            if (u.getFavoriteMovies().contains(v.getTitleV())) {
                nr++;
            }
        }
        return nr;
    }
    /**
     * face o lista, care ulterior va fi sortata
     * pentru a vedea care e cel mai favorit video
     */
    public List<VideoMS> listFav(final List<UserInputData> users,
                                 final List<MovieInputData> movies,
                                 final List<SerialInputData> series) {
        List<VideoMS> videos = listMStoVideo(movies, series);
        for (VideoMS v : videos) {
            int nrPopularity = givePopularityNumber(v, users);
            v.setPopularityNumberForFavorite(nrPopularity);
        }
        return videos;
    }
    /**
     * @param name
     * @param users
     * @param movies
     * @param series
     * @return
     */
    public String recommendationFavorite(final String name,
                                         final List<UserInputData> users,
                                         final List<MovieInputData> movies,
                                         final List<SerialInputData> series) {
        String mess = "FavoriteRecommendation result: ";
        UserInputData u = getUser(name, users);
        if (!u.getSubscriptionType().equals("PREMIUM")) {
            return "FavoriteRecommendation cannot be applied!";
        }
        List<VideoMS> list = listFav(users, movies, series);
        List<VideoMS> l1 = new ArrayList<>();
        List<VideoMS> l2 = new ArrayList<>();

        for (int i  = 0; i < list.size(); i++) {
            if (list.get(i).getPopularityNumberForFavorite() != 0) {
                l1.add(list.get(i));
            } else {
                l2.add(list.get(i));
            }
        }

        l1.sort(VideoMS.getDownPopularity());

        for (VideoMS v : l1) {
            if (!u.getFavoriteMovies().contains(v.getTitleV())
                    && !u.getHistory().containsKey(v.getTitleV())) {
                return mess + v.getTitleV();
            }
        }
        for (int i = 0; i < l2.size(); i++) {
            if (!u.getFavoriteMovies().contains(l2.get(i).getTitleV())
                    && !u.getHistory().containsKey(l2.get(i).getTitleV())) {
                return mess + l2.get(i).getTitleV();
            }
        }
        return "FavoriteRecommendation cannot be applied!";
    }
}
