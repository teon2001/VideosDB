package main;

import checker.Checkstyle;
import checker.Checker;
import common.Constants;
import entertainment.Season;
import fileio.SerialInputData;
import fileio.MovieInputData;
import fileio.UserInputData;
import fileio.ActorInputData;
import fileio.ActionInputData;
import fileio.Writer;

import main.commands.Favorite;
import main.commands.Rating;
import main.commands.View;

import main.query.ForVideoLongestS;
import main.query.ForActorsAverage;
import main.query.ForActorsAwards;
import main.query.ForVideoFavoriteM;
import main.query.ForVideoLongestM;
import main.query.ForVideoFavoriteS;
import main.query.ForVideoMostM;
import main.query.ForVideoMostS;
import main.query.ForVideoRatingM;
import main.query.ForVideoRatingS;
import main.query.ForActorsFilterDescription;
import main.query.ForUserActiv;

import main.recommendation.RecoSearch;
import main.recommendation.RecoStandard;
import main.recommendation.RecoFavorite;
import main.recommendation.RecoPopular;
import main.recommendation.RecoBestUnseen;

import org.json.simple.JSONArray;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The entry point to this homework. It runs the checker that tests your implentation.
 */
public final class Main {
    /**
     * for coding style
     */
    private Main() {
    }

    /**
     * Call the main checker and the coding style checker
     *
     * @param args from command line
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void main(final String[] args) throws IOException {
        File directory = new File(Constants.TESTS_PATH);
        Path path = Paths.get(Constants.RESULT_PATH);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }

        File outputDirectory = new File(Constants.RESULT_PATH);

        Checker checker = new Checker();
        checker.deleteFiles(outputDirectory.listFiles());

        for (File file : Objects.requireNonNull(directory.listFiles())) {

            String filepath = Constants.OUT_PATH + file.getName();
            File out = new File(filepath);
            boolean isCreated = out.createNewFile();
            if (isCreated) {
                action(file.getAbsolutePath(), filepath);
            }
        }

        checker.iterateFiles(Constants.RESULT_PATH, Constants.REF_PATH, Constants.TESTS_PATH);
        Checkstyle test = new Checkstyle();
        test.testCheckstyle();
    }

    /**
     * @param filePath1 for input file
     * @param filePath2 for output file
     * @throws IOException in case of exceptions to reading / writing
     */
    public static void action(final String filePath1,
                              final String filePath2) throws IOException {
        fileio.InputLoader inputLoader = new fileio.InputLoader(filePath1);
        fileio.Input input = inputLoader.readData();
        fileio.Writer fileWriter = new Writer(filePath2);
        JSONArray arrayResult = new JSONArray();

        //TODO add here the entry point to your implementation
        //accesez entitatile:
        List<ActorInputData> actors = input.getActors();
        List<UserInputData> users = input.getUsers();
        List<ActionInputData> actions = input.getCommands();
        List<MovieInputData> movies = input.getMovies();
        List<SerialInputData> series = input.getSerials();
        String mess;
        //pargurg fiecare comanda si execut:
        for (ActionInputData iter : actions) {
            switch (iter.getActionType()) {
                case "command":
                    switch (iter.getType()) {
                        case "favorite":
                            Favorite f = new Favorite();
                            mess = f.commandFavorite(iter.getUsername(), users, iter.getTitle());
                            arrayResult.add(fileWriter.writeFile(iter.getActionId(), null, mess));
                            break;
                        case "view":
                            View v = new View();
                            mess = v.commandView(iter.getUsername(), users, iter.getTitle());
                            arrayResult.add(fileWriter.writeFile(iter.getActionId(), null, mess));
                            break;
                        case "rating":
                            switch (iter.getSeasonNumber()) {
                                case 0 -> {
                                    //film
                                    Rating rM = new Rating();
                                    mess = rM.commandRatingMovie(iter.getUsername(), users,
                                            iter.getTitle(), iter.getGrade(), movies);
                                    arrayResult.add(fileWriter.writeFile(iter.getActionId(),
                                            null, mess));
                                }
                                default -> {
                                    //serial
                                    Double grade = iter.getGrade();
                                    Integer season = iter.getSeasonNumber();
                                    String title = iter.getTitle();
                                    SerialInputData ss = null;
                                    for (SerialInputData iter2 : series) {
                                        if (title.equals(iter2.getTitle())) {
                                            ss = iter2;
                                        }
                                    }
                                    ArrayList<Season> sezoane = ss.getSeasons();
                                    Season sezonul = null;
                                    for (Season iter3 : sezoane) {
                                        if (iter3.getCurrentSeason() == season) {
                                            sezonul = iter3;
                                        }
                                    }
                                    Rating rS = new Rating();
                                    mess = rS.commandRatingSeries(iter.getUsername(),
                                            users, iter.getTitle(), series, grade, sezonul);
                                    arrayResult.add(fileWriter.writeFile(iter.getActionId(),
                                            null, mess));
                                }
                            }
                            break;
                        default:
                            break;
                    }
                    break;
                case "query":
                    switch (iter.getObjectType()) {
                        case "shows":
                            switch (iter.getCriteria()) {
                                case "ratings" -> {
                                    String sortSType = iter.getSortType();
                                    Integer no = iter.getNumber();
                                    List<List<String>> filters = iter.getFilters();
                                    ForVideoRatingS fVRS = new ForVideoRatingS();
                                    mess = fVRS.queryVideoRatingS(sortSType, filters,
                                            series, no);
                                    arrayResult.add(fileWriter.writeFile(iter.getActionId(),
                                            null, mess));
                                    break;
                                }
                                case "favorite" -> {
                                    String sortSSType = iter.getSortType();
                                    Integer no = iter.getNumber();
                                    List<List<String>> filters = iter.getFilters();
                                    ForVideoFavoriteS fVRS = new ForVideoFavoriteS();
                                    mess = fVRS.queryVideoFavoriteS(series, users,
                                            filters, sortSSType, no);
                                    arrayResult.add(fileWriter.writeFile(iter.getActionId(),
                                            null, mess));
                                    break;
                                }
                                case "longest" -> {
                                    String sortSSType = iter.getSortType();
                                    Integer no = iter.getNumber();
                                    List<List<String>> filters = iter.getFilters();
                                    ForVideoLongestS fVLS = new ForVideoLongestS();
                                    mess = fVLS.queryVideoLongestS(series, users,
                                            filters, sortSSType, no);
                                    arrayResult.add(fileWriter.writeFile(iter.getActionId(),
                                            null, mess));
                                    break;
                                }
                                case "most_viewed" -> {
                                    String sortSSType = iter.getSortType();
                                    Integer no = iter.getNumber();
                                    List<List<String>> filters = iter.getFilters();
                                    ForVideoMostS fVMS = new ForVideoMostS();
                                    mess = fVMS.queryVideoMostS(series, users, filters,
                                            sortSSType, no);
                                    arrayResult.add(fileWriter.writeFile(iter.getActionId(),
                                            null, mess));
                                    break;
                                }
                                default -> {
                                }
                            }
                            break;
                        case "movies":
                            switch (iter.getCriteria()) {
                                case "ratings" -> {
                                    String sortMType2 = iter.getSortType();
                                    Integer no2 = iter.getNumber();
                                    List<List<String>> filters2 = iter.getFilters();
                                    ForVideoRatingM fVRM = new ForVideoRatingM();
                                    mess = fVRM.queryVideoRatingM(sortMType2, filters2,
                                            movies, no2);
                                    arrayResult.add(fileWriter.writeFile(iter.getActionId(),
                                            null, mess));
                                    break;
                                }
                                case "favorite" -> {
                                    String sortMMType = iter.getSortType();
                                    Integer no = iter.getNumber();
                                    List<List<String>> filters = iter.getFilters();
                                    ForVideoFavoriteM fVRM = new ForVideoFavoriteM();
                                    mess = fVRM.queryVideoFavoriteM(movies, users, filters,
                                            sortMMType, no);
                                    arrayResult.add(fileWriter.writeFile(iter.getActionId(),
                                            null, mess));
                                    break;
                                }
                                case "longest" -> {
                                    String sortMMType = iter.getSortType();
                                    Integer no = iter.getNumber();
                                    List<List<String>> filters = iter.getFilters();
                                    ForVideoLongestM fVLM = new ForVideoLongestM();
                                    mess = fVLM.queryVideoLongestM(movies, users, filters,
                                            sortMMType, no);
                                    arrayResult.add(fileWriter.writeFile(iter.getActionId(),
                                            null, mess));
                                    break;
                                }
                                case "most_viewed" -> {
                                    String sortMMType = iter.getSortType();
                                    Integer no = iter.getNumber();
                                    List<List<String>> filters = iter.getFilters();
                                    ForVideoMostM fVMM = new ForVideoMostM();
                                    mess = fVMM.queryVideoMostM(movies, users, filters,
                                            sortMMType, no);
                                    arrayResult.add(fileWriter.writeFile(iter.getActionId(),
                                            null, mess));
                                    break;
                                }
                                default -> { }
                            }
                            break;
                        case "actors":
                            switch (iter.getCriteria()) {
                                case "average" -> {
                                    int noActors = iter.getNumber();
                                    String sortType = iter.getSortType();
                                    ForActorsAverage fA = new ForActorsAverage();
                                    mess = fA.queryActors(movies, series, actors,
                                            noActors, sortType);
                                    arrayResult.add(fileWriter.writeFile(iter.getActionId(),
                                            null, mess));

                                    break;
                                }
                                case "awards" -> {
                                    String sortType = iter.getSortType();
                                    List<List<String>> filters3 = iter.getFilters();
                                    ForActorsAwards fAA = new ForActorsAwards();
                                    mess = fAA.queryActorsAwards(actors, filters3,
                                            sortType);
                                    arrayResult.add(fileWriter.writeFile(iter.getActionId(),
                                            null, mess));
                                    break;
                                }
                                case "filter_description" -> {
                                    List<List<String>> filters3 = iter.getFilters();
                                    String sortType = iter.getSortType();
                                    ForActorsFilterDescription fAFD =
                                            new ForActorsFilterDescription();
                                    mess = fAFD.queryFilterDescription(sortType, filters3,
                                            actors);
                                    arrayResult.add(fileWriter.writeFile(iter.getActionId(),
                                            null, mess));
                                    break;
                                }
                                default -> { }
                            }
                            break;
                        case "users":
                            String sortUType = iter.getSortType();
                            Integer no = iter.getNumber();
                            ForUserActiv fUA = new ForUserActiv();
                            mess = fUA.queryUsersActivi(users, sortUType, no,
                                    movies, series);
                            arrayResult.add(fileWriter.writeFile(iter.getActionId(),
                                    null, mess));
                            break;
                        default:
                            break;
                    }
                    break;
                case "recommendation":
                    switch (iter.getType()) {
                        case "standard":
                            String name = iter.getUsername();
                            RecoStandard rS = new RecoStandard();
                            mess = rS.recommendationStdr(name, users, movies, series);
                            arrayResult.add(fileWriter.writeFile(iter.getActionId(), null, mess));
                            break;
                        case "best_unseen":
                            String name1 = iter.getUsername();
                            RecoBestUnseen rBU = new RecoBestUnseen();
                            mess = rBU.recommendationBUnseen(name1, users, movies, series);
                            arrayResult.add(fileWriter.writeFile(iter.getActionId(), null, mess));
                            break;
                        case "popular":
                            String name2 = iter.getUsername();
                            RecoPopular rP = new RecoPopular();
                            mess = rP.recommendationPopular(name2, users, movies, series);
                            arrayResult.add(fileWriter.writeFile(iter.getActionId(), null, mess));
                            break;
                        case "favorite":
                            String name3 = iter.getUsername();
                            RecoFavorite rF = new RecoFavorite();
                            mess = rF.recommendationFavorite(name3, users, movies, series);
                            arrayResult.add(fileWriter.writeFile(iter.getActionId(), null, mess));
                            break;
                        case "search":
                            String name4 = iter.getUsername();
                            String genre = iter.getGenre();
                            RecoSearch recoS = new RecoSearch();
                            mess = recoS.recommendationSearch(name4, users, movies, series, genre);
                            arrayResult.add(fileWriter.writeFile(iter.getActionId(), null, mess));
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    break;
            }

        }
        fileWriter.closeJSON(arrayResult);
    }
}
