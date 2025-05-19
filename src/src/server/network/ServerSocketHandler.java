package src.server.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.function.Consumer;

public class ServerSocketHandler {
    private final ServerSocketChannel serverSocketChannel;
    private final Selector selector;

    public ServerSocketHandler(int port) throws IOException {
        this.selector = Selector.open();
        this.serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("Сервер ожидает подключений...");
    }

    public void startListening(Consumer<SocketChannel> requestHandler) throws IOException {
        while (true) {
            if (selector.select(1000) == 0) continue;

            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();

            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                if (key.isAcceptable()) {
                    acceptConnection(key, requestHandler);
                } else if (key.isReadable()) {
                    handleRead(key, requestHandler);
                }
                keys.remove();
            }
        }
    }

    private void acceptConnection(SelectionKey key, Consumer<SocketChannel> requestHandler) throws IOException {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        SocketChannel clientChannel = serverChannel.accept();
        if (clientChannel != null) {
            clientChannel.configureBlocking(false);
            clientChannel.register(selector, SelectionKey.OP_READ);
            System.out.println("Клиент подключён.");
        }
    }

    private void handleRead(SelectionKey key, Consumer<SocketChannel> requestHandler) {
        SocketChannel clientChannel = (SocketChannel) key.channel();
        requestHandler.accept(clientChannel);
    }
}