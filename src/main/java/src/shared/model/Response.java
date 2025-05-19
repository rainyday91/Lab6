package src.shared.model;

import java.io.Serializable;
import java.util.TreeSet;

/**
 * Класс Response представляет ответ сервера на запрос клиента.
 * Содержит текстовое сообщение и, при необходимости, коллекцию объектов Movie.
 */

public class Response implements Serializable {
    private boolean success;
    private String message;
    private TreeSet<Movie> collection;

    public Response(boolean success, String message) {
        this(success, message, null);
    }

    public Response(boolean success, String message, TreeSet<Movie> collection) {
        this.success = success;
        this.message = message;
        this.collection = collection;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public boolean hasCollection() {
        return collection != null && !collection.isEmpty();
    }

    public TreeSet<Movie> getCollection() {
        return collection;
    }
}