//package src.server.network;
//import java.io.*;
//import java.nio.ByteBuffer;
//import java.nio.channels.ServerSocketChannel;
//import java.nio.channels.SocketChannel;
//import java.util.Arrays;
//
//public class ObjectSerializer {
//    public static byte[] serialize(Object obj) throws IOException {
//        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
//             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
//            oos.writeObject(obj);
//            return bos.toByteArray();
//        }
//    }
//
//    public static Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
//        try (ByteArrayInputStream bis = new ByteArrayInputStream(data);
//             ObjectInputStream ois = new ObjectInputStream(bis)) {
//            return ois.readObject();
//        }
//    }
//
//    public static byte[] receive(ServerSocketChannel channel) throws IOException {
//        ByteBuffer buffer = ByteBuffer.allocate(4096);
//        int bytesRead = channel.bind(buffer);
//        if (bytesRead == -1) throw new IOException("Соединение закрыто клиентом");
//        return Arrays.copyOf(buffer.array(), bytesRead);
//    }
//
//    public static void send(SocketChannel channel, byte[] data) throws IOException {
//        ByteBuffer buffer = ByteBuffer.wrap(data);
//        channel.write(buffer);
//    }
//}