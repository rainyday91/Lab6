package src.client.network;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.io.IOException;
import java.util.Arrays;

public class ClientSocketHandler {
    private SocketChannel socketChannel;

    public void connect(String host, int port) throws IOException {
        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress(host, port));
        while (!socketChannel.finishConnect()) {
            // ждём
        }
    }

    public void send(byte[] data) throws IOException {
        ByteBuffer buffer = ByteBuffer.wrap(data);
        socketChannel.write(buffer);
    }

    public byte[] receive(int bufferSize) throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(bufferSize);
        int bytesRead = socketChannel.read(buffer);
        if (bytesRead == -1) throw new IOException("Сервер закрыл соединение");
        return Arrays.copyOf(buffer.array(), bytesRead);
    }

    public void close() throws IOException {
        socketChannel.close();
    }
}