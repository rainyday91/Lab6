package src.client;

import src.client.utils.CommandReader;
import src.client.utils.InputReader;
import src.shared.model.Response;

import java.util.List;
import java.util.Scanner;

//import static sun.security.util.Debug.Help;

public class Client {
    private final CommandSender commandSender;
    private final CommandReader commandReader;
    private final InputReader movieDataReader;
    private final Scanner scanner;

    public Client(String host, int port) {
        this.scanner = new Scanner(System.in);
        this.commandSender = new CommandSender(host, port);
        this.commandReader = new CommandReader(scanner);
        this.movieDataReader = new InputReader(scanner);
    }

    public void start() {

        while (true) {
            try {
                Object[] commandData = commandReader.readCommandWithArgs();
                String command = (String) commandData[0];

                if ("exit".equals(command)) {
                    System.out.println("Closing client...");
                    return;
                }

                if ("help".equals(command)) {
//                    Help();
                    continue;
                }

                // Если команда "add", читаем данные о фильме
                if ("add".equalsIgnoreCase(command)) {
                    System.out.println("Введите данные для нового фильма:");
                    String movieData = movieDataReader.readMovieData(); // получаем строку с данными
                    Response response = commandSender.sendCommand(command, movieData);
                    handleResponse(command, response);
                    continue;
                }

                // Для остальных команд передаём второй аргумент, если есть
                Object argument = commandData.length > 1 ? commandData[1] : null;
                Response response = commandSender.sendCommand(command, argument);
                handleResponse(command, response);

            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        }
    }

    private void handleResponse(String command, Response response) {
        System.out.println(response.getMessage());

        switch (command) {
            case "show":
            case "filter_less_than_genre":
                if (response.getCollection() != null) {
                    response.getCollection().forEach(System.out::println);
                }
                break;

            case "history":
                List<String> history = commandReader.getCommandHistory();
                System.out.println("Последние команды (" + history.size() + "):");
                history.forEach(System.out::println);
                break;

            default:
                // Вывод только сообщения
        }
}}