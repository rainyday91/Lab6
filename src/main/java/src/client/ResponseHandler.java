package src.client;

import src.shared.model.Movie;
import src.shared.model.Response;

import java.util.TreeSet;

public class ResponseHandler {
    public void handle(Response response) {
        System.out.println(response.getMessage());

        TreeSet<Movie> collection = response.getCollection();
        if (collection != null && !collection.isEmpty()) {
            System.out.println("\nCurrent collection:");
            collection.forEach(System.out::println);
        }
    }
}
