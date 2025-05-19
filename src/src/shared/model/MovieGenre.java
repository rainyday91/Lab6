package src.shared.model;

import java.io.Serializable;

public enum MovieGenre implements Serializable {
    DRAMA,
    THRILLER,
    HORROR,
    SCIENCE_FICTION;

    public int compareTo(src.client.model.MovieGenre genre) {
        return 0;
    }
}