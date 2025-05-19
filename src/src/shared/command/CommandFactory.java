package src.shared.command;

import src.shared.model.Movie;

import java.io.Serial;
import java.io.Serializable;
import java.util.TreeSet;

public class CommandFactory implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String commandName;
    private Movie movieArgument;
    private String argument;
    private TreeSet<Movie> collectionArgument;

    public CommandFactory(TreeSet<Movie> collectionArgument) {
        this.collectionArgument = collectionArgument;
    }

    public TreeSet<Movie> getCollectionArgument() {
        return collectionArgument;
    }

    public CommandFactory(String commandName, String argument) {
        this.commandName = commandName;
        this.argument = argument;
        this.collectionArgument = null;
    }

    public CommandFactory(String commandName, Movie movieArgument) {
        this.commandName = commandName;
        this.movieArgument = movieArgument;
    }

    public String getCommandName() {
        return commandName;
    }

    public Movie getMovieArgument() {
        return movieArgument;
    }

    public String getArgument() {
        return argument;
    }
}



