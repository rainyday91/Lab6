//package src.shared.command;
//import src.server.model.MovieCollectionManager;
//import src.shared.model.Movie;
//
//import java.util.TreeSet;
//
//public class ShowCommand implements Command {
//    public void execute(String argument) {
//        TreeSet<Movie> collection = MovieCollectionManager.getCollection();
//        if (collection.isEmpty()) {
//            System.out.println("Коллекция пуста.");
//        } else {
//            System.out.println("Элементы коллекции:");
//            for (Movie movie : collection) {
//                System.out.println(movie);
//            }
//        }
//    }
//}