package main.recommendation;

import fileio.MovieInputData;
import fileio.SerialInputData;
import fileio.UserInputData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecoPopular {
    /**
     * intoarce entitatea user
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
     * verifica daca e serial
     */
    public int isSerial(final String title, final List<SerialInputData> series) {
        for (SerialInputData iter : series) {
            if (iter.getTitle().equals(title)) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * verifica daca e film
     */
    public int isMovie(final String title, final List<MovieInputData> movies) {
        for (MovieInputData iter : movies) {
            if (iter.getTitle().equals(title)) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * intorce entitatea serial
     */
    public SerialInputData getSerial(final String title, final List<SerialInputData> series) {
        for (SerialInputData iter : series) {
            if (iter.getTitle().equals(title)) {
                return iter;
            }
        }
        return null;
    }

    /**
     * intorce entitatea film
     */
    public MovieInputData getMovie(final String title, final List<MovieInputData> movies) {
        for (MovieInputData iter : movies) {
            if (iter.getTitle().equals(title)) {
                return iter;
            }
        }
        return null;
    }

    /**
     * creeaza list cu toate genurile
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
            video.setRatingV(m.getAverageRating());
            video.setGenreV(m.getGenres());
            list.add(video);
        }
        for (SerialInputData s : series) {
            VideoMS video = new VideoMS();
            video.setTitleV(s.getTitle());
            video.setRatingV(s.getAverageRating());
            video.setGenreV((s.getGenres()));
            list.add(video);
        }
        return list;
    }
    /**
     * Numar de vizualizari per gen in total
     */
    public int noViewsPerGenre(final List<UserInputData> users,
                               final String genre,
                               final List<MovieInputData> movies,
                               final List<SerialInputData> series) {
        int nr = 0;
        for (UserInputData u : users) {
            for (Map.Entry<String, Integer> entry : u.getHistory().entrySet()) {
                if (isMovie(entry.getKey(), movies) == 1) {
                    MovieInputData m = getMovie(entry.getKey(), movies);
                    if (m.getGenres().contains(genre)) {
                        nr = nr + entry.getValue();
                    }
                } else if (isSerial(entry.getKey(), series) == 1) {
                    SerialInputData s = getSerial(entry.getKey(), series);
                    if (s.getGenres().contains(genre)) {
                        nr = nr + entry.getValue();
                    }
                }
            }
        }
        return nr;
    }

    /**
     * genereaza o lista cu genurile populare
     */
    public List<MyGenres> popularGenres(final List<UserInputData> users,
                                        final List<MovieInputData> movies,
                                        final List<SerialInputData> series) {
        List<String> allGenres = getAllGenres();
        List<MyGenres> popularity = new ArrayList<>();

        for (String genre : allGenres) {
            int numberViewPerGenre = noViewsPerGenre(users, genre, movies, series);
            MyGenres mG = new MyGenres();
            mG.setGenre(genre);
            mG.setNoViewsOfVideos(numberViewPerGenre);
            popularity.add(mG);
        }
        popularity.sort(MyGenres.getDown());
        return popularity;
    }
    /**
     * intoarce toate video.urile dintr un gen
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
     * @param name
     * @param users
     * @param movies
     * @param series
     * @return
     */
    public String recommendationPopular(final String name, final List<UserInputData> users,
                                        final List<MovieInputData> movies,
                                        final List<SerialInputData> series) {
        String mess = "PopularRecommendation result: ";
        UserInputData u = getUser(name, users);
        if (!u.getSubscriptionType().equals("PREMIUM")) {
            return "PopularRecommendation cannot be applied!";
        }

        List<MyGenres> popularityGenres = popularGenres(users, movies, series);
        for (MyGenres g : popularityGenres) {
            List<VideoMS> allVideos = allVideosFromAGenre(movies, series, g.getGenre());
            Map<String, Integer> map = u.getHistory();
            for (VideoMS v : allVideos) {
                if (!map.containsKey(v.getTitleV())) {
                    return mess + v.getTitleV();
                }
            }
        }
        return "PopularRecommendation cannot be applied!";
    }
}
