package src.shared.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Comparator;

/**
 * Компаратор для сортировки фильмов по ID.
 * Реализует Serializable, чтобы можно было передавать TreeSet<Movie> между клиентом и сервером.
 */
public class MovieIdComparator implements Comparator<Movie>, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Override
    public int compare(Movie o1, Movie o2) {
        return o1.getId().compareTo(o2.getId());
    }
}