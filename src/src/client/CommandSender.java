package src.client;


import src.shared.network.Request;
import src.shared.model.Response;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class CommandSender {
    private final String host;
    private final int port;

    public CommandSender(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Response sendCommand(String command, Object argument) throws IOException {
        try (SocketChannel socketChannel = SocketChannel.open()) {
            socketChannel.connect(new InetSocketAddress(host, port));
            socketChannel.configureBlocking(true);

            // Сериализуем Request
            byte[] requestData = serialize(new Request(command, argument));

            // Отправляем размер + данные
            writeWithSize(socketChannel, requestData);

            // Читаем ответ
            byte[] responseData = readWithSize(socketChannel);
            return (Response) deserialize(responseData);
        }
    }

    private void writeWithSize(SocketChannel channel, byte[] data) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(4 + data.length);
        buffer.putInt(data.length);
        buffer.put(data);
        buffer.flip();
        while (buffer.hasRemaining()) {
            channel.write(buffer);
        }
    }

    private byte[] readWithSize(SocketChannel channel) throws IOException {
        ByteBuffer sizeBuffer = ByteBuffer.allocate(4);
        int bytesRead = channel.read(sizeBuffer);
        if (bytesRead == -1) throw new EOFException("Connection closed");

        sizeBuffer.flip();
        int objectSize = sizeBuffer.getInt();

        if (objectSize < 0 || objectSize > 10 * 1024 * 1024) { // защита от ошибок
            throw new IOException(" CommandSender Invalid object size: " + objectSize);
        }

        ByteBuffer objectBuffer = ByteBuffer.allocate(objectSize);
        while (objectBuffer.hasRemaining()) {
            bytesRead = channel.read(objectBuffer);
            if (bytesRead == -1) throw new IOException("CommandSender Unexpected end of stream");
        }

        return objectBuffer.array();
    }

    private byte[] serialize(Serializable obj) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(obj);
        }
        return bos.toByteArray();
    }

    private Object deserialize(byte[] bytes) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes))) {
            return ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("Class not found during deserialization", e);
        }
    }
}