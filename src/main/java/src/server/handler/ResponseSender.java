//package src.server.handler;
//import src.server.network.ObjectSerializer;
//import src.shared.model.Response;
//
//import java.nio.channels.ServerSocketChannel;
//import java.nio.channels.SocketChannel;
//import java.io.IOException;
//
//public class ResponseSender {
//    public static void send(ServerSocketChannel clientChannel, Response response) {
//        try {
//            byte[] data = ObjectSerializer.serialize(response);
//            ObjectSerializer.send(clientChannel, data);
//        } catch (IOException e) {
//            System.err.println("Ошибка при отправке ответа клиенту: " + e.getMessage());
//        }
//    }
//}