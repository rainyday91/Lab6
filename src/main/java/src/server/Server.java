//package src.server;
//import java.io.IOException;
//import java.net.InetSocketAddress;
//import java.nio.channels.ServerSocketChannel;
//import java.nio.channels.SocketChannel;
//
//public class Server {
//    private final int port;
//
//    public Server(int port) {
//        this.port = port;
//    }
//
//    public void start() {
//        try (ServerSocketChannel serverChannel = ServerSocketChannel.open()) {
//            serverChannel.bind(new InetSocketAddress(port));
//            serverChannel.configureBlocking(false); // неблокирующий режим
//
//            System.out.println("Server started on port " + port);
//
//            while (true) {
//                SocketChannel clientChannel = serverChannel.accept();
//
//                if (clientChannel != null) {
//                    System.out.println("Client connected: " + clientChannel.getRemoteAddress());
//                    new Thread(new ConnectionHandler(clientChannel)).start();
//                }
//            }
//        } catch (IOException e) {
//            System.err.println("Server error: " + e.getMessage());
//        }
//    }
//}