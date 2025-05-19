//package src.client;
//
//import src.client.command.AddCommand;
//import src.shared.model.Movie;
//import src.shared.command.CommandFactory;
//import src.shared.command.Add;
//import src.client.network.ClientSocketHandler;
//import src.client.network.ObjectSerializer;
//import src.shared.model.Response;
//
//import java.io.IOException;
//import java.util.Scanner;
//
//public class Main {
//    private static final String SERVER_HOST = "localhost";
//    private static final int SERVER_PORT = 8080;
//
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        ClientSocketHandler socketHandler = new ClientSocketHandler();
//        CommandFactory commandFactory = null;
//        ResponsePrinter responsePrinter = new ResponsePrinter();
//        Movie movieArgument = null;
//
//        System.out.println("Клиент запущен. Подключение к серверу...");
//
//        try {
//            socketHandler.connect(SERVER_HOST, SERVER_PORT);
//            System.out.println("Подключён к серверу по адресу " + SERVER_HOST + ":" + SERVER_PORT);
//
//            while (true) {
//                System.out.print("\nВведите команду (add / exit): ");
//                String input = scanner.nextLine().trim().toLowerCase();
//
//                if ("exit".equals(input)) {
//                    System.out.println("Завершение работы клиента.");
//                    break;
//                } else if ("add".equals(input)) {
//                    try {
//                        Add add = new Add();
//                        movieArgument = add.add();
//                        commandFactory = new CommandFactory("add", movieArgument);
//                        byte[] serializedData = ObjectSerializer.serialize(commandFactory);
//                        socketHandler.send(serializedData);
//
//                        // Получаем ответ от сервера
//                        byte[] responseBytes = socketHandler.receive(4096);
//                        Response response = (Response) ObjectDeserializer.deserialize(responseBytes);
//                        responsePrinter.print(response);
//                    } catch (Exception e) {
//                        System.err.println("Ошибка при выполнении команды: " + e.getMessage());
//                    }
//                } else {
//                    System.out.println("Неизвестная команда. Доступные команды: add, exit");
//                }
//            }
//
//        } catch (IOException e) {
//            System.err.println("Ошибка подключения к серверу: " + e.getMessage());
//        } finally {
//            try {
//                socketHandler.close();
//            } catch (IOException e) {
//                System.err.println("Ошибка при закрытии соединения: " + e.getMessage());
//            }
//        }
//    }
//}