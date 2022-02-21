package fileio;

import java.util.List;

/**
 * The class contains information about input
 * <p>
 * DO NOT MODIFY
 */
public final class Input {
    /**
     * List of actors
     */
    private final List<ActorInputData> actorsData;
    /**
     * List of users
     */
    private final List<UserInputData> usersData;
    /**
     * List of commands
     */
    private final List<ActionInputData> commandsData;
    /**
     * List of movies
     */
    private final List<fileio.MovieInputData> moviesData;
    /**
     * List of serials aka tv shows
     */
    private final List<fileio.SerialInputData> serialsData;

    public Input() {
        this.actorsData = null;
        this.usersData = null;
        this.commandsData = null;
        this.moviesData = null;
        this.serialsData = null;
    }

    public Input(final List<ActorInputData> actors, final List<fileio.UserInputData> users,
                 final List<ActionInputData> commands,
                 final List<fileio.MovieInputData> movies,
                 final List<fileio.SerialInputData> serials) {
        this.actorsData = actors;
        this.usersData = users;
        this.commandsData = commands;
        this.moviesData = movies;
        this.serialsData = serials;
    }

    public List<ActorInputData> getActors() {
        return actorsData;
    }

    public List<fileio.UserInputData> getUsers() {
        return usersData;
    }

    public List<ActionInputData> getCommands() {
        return commandsData;
    }

    public List<fileio.MovieInputData> getMovies() {
        return moviesData;
    }

    public List<fileio.SerialInputData> getSerials() {
        return serialsData;
    }
}
