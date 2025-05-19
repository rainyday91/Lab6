//package src.server;
//import src.shared.network.Request;
//import src.shared.model.Response;
//import java.io.*;
//import java.nio.ByteBuffer;
//import java.nio.channels.SocketChannel;
//public class ConnectionHandler implements Runnable {
//    private final SocketChannel clientChannel;
//
//    public ConnectionHandler(SocketChannel clientChannel) {
//        this.clientChannel = clientChannel;
//    }
//
//    @Override
//    public void run() {
//        try {
//            handleConnection();
//        } catch (IOException e) {
//            System.err.println("Error handling connection: " + e.getMessage());
//        }
//    }
//
//    private void handleConnection() throws IOException {
//        if (clientChannel == null || !clientChannel.isOpen()) {
//            throw new IOException("Client channel is closed or null");
//        }
//
//        try {
//            // Чтение запроса
//            byte[] requestData = readWithSize(clientChannel);
//            Request request = (Request) deserialize(requestData);
//
//            // Обработка запроса через CommandExecutor
//            Response response = CommandExecutor.execute(request);
//
//            // Сериализация и отправка ответа
//            byte[] responseData = serialize(response);
//            writeWithSize(clientChannel, responseData);
//
//        } finally {
//            try {
//                clientChannel.close();
//            } catch (IOException ignored) {}
//        }
//    }
//
//    // === Вспомогательные методы для сериализации и передачи данных ===
//
//    private byte[] readWithSize(SocketChannel channel) throws IOException {
//        ByteBuffer sizeBuffer = ByteBuffer.allocate(4);
//        int bytesRead = channel.read(sizeBuffer);
//        if (bytesRead == -1) throw new EOFException("Connection closed");
//
//        sizeBuffer.flip();
//        int objectSize = sizeBuffer.getInt();
//
//        if (objectSize < 0 || objectSize > 10 * 1024 * 1024) {
//            throw new IOException("Invalid object size: " + objectSize);
//        }
//
//        ByteBuffer objectBuffer = ByteBuffer.allocate(objectSize);
//        while (objectBuffer.hasRemaining()) {
//            bytesRead = channel.read(objectBuffer);
//            if (bytesRead == -1) throw new IOException("Unexpected end of stream");
//        }
//
//        return objectBuffer.array();
//    }
//
//    private void writeWithSize(SocketChannel channel, byte[] data) throws IOException {
//        ByteBuffer buffer = ByteBuffer.allocate(4 + data.length);
//        buffer.putInt(data.length);
//        buffer.put(data);
//        buffer.flip();
//        while (buffer.hasRemaining()) {
//            channel.write(buffer);
//        }
//    }
//
//    private byte[] serialize(Serializable obj) throws IOException {
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
//            oos.writeObject(obj);
//        }
//        return bos.toByteArray();
//    }
//
//    private Object deserialize(byte[] bytes) throws IOException {
//        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
//            return ois.readObject();
//        } catch (ClassNotFoundException e) {
//            throw new IOException("Class not found during deserialization", e);
//        }
//    }
//}