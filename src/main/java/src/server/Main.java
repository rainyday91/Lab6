package src.server;
import src.server.handler.CommandHandler;
import src.shared.command.CommandFactory;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
public class Main {
    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress("localhost", 8080));
        serverChannel.configureBlocking(false);
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        CommandHandler commandHandler = new CommandHandler();

        System.out.println("Сервер запущен...");

        while (true) {
            selector.select();
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();

            while (keys.hasNext()) {
                SelectionKey key = keys.next();

                if (key.isAcceptable()) {
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
                    SocketChannel clientChannel = serverSocketChannel.accept();
                    if (clientChannel != null) {
                        clientChannel.configureBlocking(false);
                        clientChannel.register(selector, SelectionKey.OP_READ);
                        System.out.println("Клиент подключился");
                    }
                }

                if (key.isReadable() && !key.isAcceptable()) {
                    SocketChannel clientChannel = (SocketChannel) key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(8192);
                    int bytesRead;

                    try {
                        bytesRead = clientChannel.read(buffer);
                    } catch (IOException e) {
                        System.out.println("Клиент отключился");
                        key.cancel();
                        clientChannel.close();
                        continue;
                    }

                    if (bytesRead > 0) {
                        buffer.flip();
                        byte[] data = new byte[buffer.limit()];
                        buffer.get(data);

                        try {
                            CommandFactory received = deserialize(data);
                            System.out.println("Получено: " + received.getArgument());
                            commandHandler.handleClientRequest(received);

                            // Формируем ответ
                            CommandFactory response = new CommandFactory("Echo: " + received.getArgument(), "");
                            byte[] responseData = serialize(response);

                            // Отправляем ответ
                            clientChannel.write(ByteBuffer.wrap(responseData));
                            System.out.println("Ответ отправлен");

                        } catch (Exception e) {
                            e.printStackTrace();
                            System.out.println("Ошибка десериализации");
                        }
                    } else if (bytesRead == -1) {
                        System.out.println("Клиент закрыл соединение");
                        key.cancel();
                        clientChannel.close();
                    }
                }

                keys.remove();
            }
        }
    }

    private static byte[] serialize(CommandFactory command) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(command);
            return bos.toByteArray();
        }
    }

    private static CommandFactory deserialize(byte[] data) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(data);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            return (CommandFactory) ois.readObject();
        }
    }
}
