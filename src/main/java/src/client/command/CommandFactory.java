package src.client.command;

import src.client.model.Movie;

import java.io.Serializable;

public class CommandFactory implements Serializable {
    private static final long serialVersionUID = 1L;
    private String commandName;
    private Movie movieArgument;
    private String argument;

    public CommandFactory(String commandName, String argument) {
        this.commandName = commandName;
        this.argument = argument;
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



