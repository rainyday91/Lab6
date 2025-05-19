package src.server.handler;
import src.shared.command.CommandFactory;
import src.shared.model.Movie;
import src.server.FileManager;
import src.server.model.MovieCollectionManager;

import java.io.IOException;
import java.util.TreeSet;

public class CommandHandler {
    private final MovieCollectionManager collectionManager;

    public CommandHandler() {
        this.collectionManager = new MovieCollectionManager(new FileManager("movies.json"));
        loadCollectionFromFile();
    }

    private void loadCollectionFromFile() {
        try {
            TreeSet<Movie> loaded = collectionManager.getFileManager().loadCollection();
            if (loaded != null && !loaded.isEmpty()) {
                MovieCollectionManager.setCollection(loaded);
                System.out.println("Коллекция успешно загружена из файла.");
            } else {
                System.out.println("Файл пуст или не найден. Создана новая коллекция.");
            }
        } catch (IOException e) {
            System.err.println("Ошибка при загрузке коллекции: " + e.getMessage());
        }
    }

    private void executeSimpleCommand(CommandFactory commandFactory) {
        String command = commandFactory.getCommandName();
        String argument = commandFactory.getArgument();


        try {
            switch (command) {
                case "info":

                    break;
                case "show":
                    if (!MovieCollectionManager.getCollection().isEmpty()) {
                        for (Movie movie : MovieCollectionManager.getCollection()) {
                            System.out.println(movie);
                        }
                    } else {
                        System.out.println("Your collection is empty!Try adding some elements!");
                    }
                    break;
                case "clear":

                    MovieCollectionManager.getCollection().clear(); // Очищаем коллекцию
                    System.out.println("Коллекция успешно очищена.");
                    break;

                case "exit":
                    System.exit(0);
                    break;

                default:
                    throw new IllegalArgumentException("Неизвестная команда: " + command);
            }
        } catch (Exception e) {
            System.err.println("Ошибка выполнения команды '" + command + "': " + e.getMessage());
            if (argument != null) {
                System.out.println("Переданные аргументы: " + argument);
            }
        }
    }


    public void handleClientRequest(CommandFactory commandFactory) {
        String command = commandFactory.getCommandName();


        try {
            switch (command) {
                case "add":
                    Movie movie = commandFactory.getMovieArgument();
                    MovieCollectionManager.addMovie(movie);
                    break;
//                case "update_id":
//                    new UpdateIdData().execute(cmdArgument);
//                    break;
//                case "remove_by_id":
//                    new RemoveByIdData().execute(cmdArgument);
//                    break;
//                case "add_if_max":
//                    new AddIfMaxData().execute(cmdArgument);
//                    break;
//                case "remove_lower":
//                    new RemoveLowerData().execute(cmdArgument);
//                    break;
//                case "remove_any_by_oscars_count":
//                    new RemoveAnyByOscarsCountData().execute(cmdArgument);
//                    break;
//                case "filter_less_than_genre":
//                    new FilterLessThanGenreData().execute(cmdArgument);
//                    break;
//                case "count_by_oscars_count":
//                    new CountByOscarsCountData().execute(cmdArgument);
//                    break;
//                case "execute_script":
//                    if (cmdArgument == null) throw new IllegalArgumentException("Не указан файл скрипта");
//                    executeScript(cmdArgument);
//                    break;
//                case "info":
//
//                    break;
//                case "show":
//                    if (!MovieCollectionManager.getCollection().isEmpty()) {
//                        for (Movie movie : MovieCollectionManager.getCollection()) {
//                            System.out.println(movie);
//                        }
//                    } else {
//                        System.out.println("Your collection is empty!Try adding some elements!");
//                    }
//                    break;
//                case "clear":
//
//                    MovieCollectionManager.getCollection().clear(); // Очищаем коллекцию
//                    System.out.println("Коллекция успешно очищена.");
//                    break;
//                case "save":
//
//                    break;
//                case "help":
//                    printHelp();
//                    break;
//                case "exit":
//                    System.exit(0);
//                    break;
//                default:
//                    throw new IllegalArgumentException("Неизвестная команда: " + command);
//            }
//        } catch (Exception e) {
//            System.err.println("Ошибка выполнения '" + command + "': " + e.getMessage());
//            if (cmdArgument != null) {
//                System.out.println("Переданные аргументы:");
//                System.out.println(cmdArgument);
//            }
//        }
            }


//    public void handleClientRequest(CommandFactory commandFactory) {
//        try {
//
//
//            if (Objects.equals(commandFactory.getCommandName(), "add")) {
//                Movie movie = commandFactory.getMovieArgument();
//                MovieCollectionManager.addMovie(movie);
//                ResponseSender.send(clientChannel, new Response(true, "Фильм добавлен: " + movie.getName()));
//
//
//        } catch (IOException | ClassNotFoundException e) {
//            System.err.println("Ошибка при обработке запроса клиента: " + e.getMessage());
//        }
//    }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}