package src.server.model;

import java.io.Serializable;
import java.time.ZonedDateTime;

public class Movie implements Comparable<Movie>, Serializable {

    private Long id; // автоматически генерируется сервером
    private String name;
    private Coordinates coordinates;
    private ZonedDateTime creationDate;
    private int oscarsCount;
    private Integer budget;
    private MovieGenre genre;
    private MpaaRating mpaaRating;
    private Person operator;


    public Movie(Long id, String name, Coordinates coordinates, ZonedDateTime creationDate, int oscarsCount, Integer budget, MovieGenre genre, MpaaRating mpaaRating, Person operator) {
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.oscarsCount = oscarsCount;
        this.budget = budget;
        this.genre = genre;
        this.mpaaRating = mpaaRating;
        this.operator = operator;
    }

    // Геттеры
    public String getName() { return name; }
    public Coordinates getCoordinates() { return coordinates; }
    public ZonedDateTime getCreationDate() { return creationDate; }
    public int getOscarsCount() { return oscarsCount; }
    public Integer getBudget() { return budget; }
    public MovieGenre getGenre() { return genre; }
    public MpaaRating getMpaaRating() { return mpaaRating; }
    public Person getOperator() { return operator; }

    @Override
    public int compareTo(Movie other) {
        return this.id.compareTo(other.id);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", oscarsCount=" + oscarsCount +
                ", budget=" + budget +
                ", genre=" + genre +
                ", mpaaRating=" + mpaaRating +
                ", operator=" + operator +
                '}';
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}