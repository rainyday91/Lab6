package src.shared.command;


import src.shared.model.Movie;

import java.io.Serializable;

public class AddCommand implements Serializable, Command {
    private final Movie movie;

    public AddCommand(Movie movie) {
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }
}