//package src.server;
//import src.server.handler.CommandHandler;
//import src.server.network.ServerSocketHandler;
//
//public class Main {
//    public static void main(String[] args) {
//        int port = 8080;
//        System.out.println("Запуск сервера на порту " + port);
//        try {
//            ServerSocketHandler serverSocketHandler = new ServerSocketHandler(port);
//            CommandHandler commandHandler = new CommandHandler();
//            serverSocketHandler.startListening(commandHandler::handleClientRequest);
//        } catch (Exception e) {
//            System.err.println("Ошибка запуска сервера: " + e.getMessage());
//        }
//    }
//}