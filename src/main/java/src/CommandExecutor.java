//package src;
//import src.server.model.MovieCollectionManager;
//import src.shared.model.Movie;
//import src.shared.network.Request;
//import src.shared.model.Response;
//import java.util.TreeSet;
//
//public class CommandExecutor {
//
//    public static Response execute(Request request) {
//        String command = request.getCommandName();
//        Object arg = request.getArgument();
//
//        switch (command) {
//            case "add":
//                return executeAdd((String) arg);
//            case "update_id":
//                return executeUpdate((String) arg);
//
//            case "info":
//                executeInfo();
//                return new Response("Информация о коллекции выведена");
//
//            case "show":
//                executeShow();
//                return new Response("Элементы коллекции выведены", MovieCollectionManager.getCollection());
//
//            default:
//                return new Response("Неизвестная команда: " + command);
//        }
//    }
//
//    private static Response executeAdd(String data) {
//        try {
//            AddData addData = new AddData();
//            Movie movie = addData.addFromData(data);
//            if (movie != null) {
//                MovieCollectionManager.addMovie(movie);
//                return new Response("Фильм успешно добавлен!");
//            }
//            return new Response("Ошибка при добавлении фильма");
//        } catch (Exception e) {
//            return new Response("Ошибка при добавлении фильма: " + e.getMessage());
//        }
//    }
//
//    private static void executeInfo() {
//        System.out.println("Тип коллекции: TreeSet");
//        System.out.println("Дата инициализации: " + MovieCollectionManager.getInitializationDate());
//        System.out.println("Количество элементов: " + MovieCollectionManager.getCollection().size());
//    }
//
//    // В CommandExecutor.java
//    private static Response executeShow() {
//        TreeSet<Movie> collection = MovieCollectionManager.getCollection();
//        return new Response("Элементы коллекции:", collection);
//    }
//    private static Response executeUpdate(String data) {
//        try {
//            if (data == null || data.isEmpty()) {
//                return new Response("Не переданы данные для обновления.");
//            }
//
//            String[] parts = data.split("\n", 2);
//            long id = Long.parseLong(parts[0]);
//            String movieData = parts.length > 1 ? parts[1] : "";
//
//            AddData addData = new AddData();
//            Movie updatedMovie = addData.addFromData(movieData);
//            updatedMovie.setId(id);
//
//            MovieCollectionManager.updateMovie(id, updatedMovie);
//            return new Response("Фильм с ID " + id + " успешно обновлён");
//
//        } catch (Exception e) {
//            return new Response("Ошибка при обновлении фильма: " + e.getMessage());
//        }
//    }
//
//}