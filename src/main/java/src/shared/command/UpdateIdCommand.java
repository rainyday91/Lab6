package src.shared.command;

import src.shared.model.Movie;
import java.io.Serializable;

public class UpdateIdCommand implements Serializable {
    private final Long id;
    private final Movie updatedMovie;

    public UpdateIdCommand(Long id, Movie updatedMovie) {
        this.id = id;
        this.updatedMovie = updatedMovie;
    }

    public Long getId() {
        return id;
    }

    public Movie getUpdatedMovie() {
        return updatedMovie;
    }
}