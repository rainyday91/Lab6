package src.server.model;

import src.server.FileManager;
import src.shared.model.Movie;
import java.time.ZonedDateTime;
import java.util.TreeSet;

public class MovieCollectionManager {
    private static final TreeSet<Movie> collection = new TreeSet<>();
    private static Long nextId = 1L;
    private final FileManager fileManager;
    private static final ZonedDateTime initializationDate = ZonedDateTime.now();

    public MovieCollectionManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public static synchronized void addMovie(Movie movie) {
        if (movie == null) throw new IllegalArgumentException("Фильм не может быть null");

        if (movie.getId() == null || movie.getId() <= 0) {
            movie.setId(getNextId());
        } else {
            if (collection.stream().anyMatch(m -> m.getId().equals(movie.getId()))) {
                throw new IllegalArgumentException("Фильм с ID " + movie.getId() + " уже существует");
            }
            if (movie.getId() >= nextId) {
                nextId = movie.getId() + 1;
            }
        }

        if (movie.getCreationDate() == null) {
            movie.setCreationDate(ZonedDateTime.now());
        }

        collection.add(movie);
    }

    public static synchronized Long getNextId() {
        return nextId;
    }

    private static synchronized void updateNextId() {
        if (!collection.isEmpty()) {
            nextId = collection.last().getId() + 1;
        } else {
            nextId = 1L;
        }
    }

    public static synchronized void removeMovieById(long id) {
        Movie movie = findMovieById(id);
        if (movie != null) {
            collection.remove(movie);
            updateNextId();
        }
    }

    public static synchronized TreeSet<Movie> getCollection() {
        return new TreeSet<>(collection);
    }

    public static synchronized void setCollection(TreeSet<Movie> newCollection) {
        collection.clear();
        if (newCollection != null) {
            collection.addAll(newCollection);
        }
        updateNextId();
    }

    public static ZonedDateTime getInitializationDate() {
        return initializationDate;
    }

    private static Movie findMovieById(long id) {
        return collection.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public FileManager getFileManager() {
        return fileManager;
    }
}