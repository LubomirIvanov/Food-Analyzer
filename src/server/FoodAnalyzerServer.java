package server;

import command.CommandProcessor;
import constants.ServerConstants;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class FoodAnalyzerServer {

    private final static int LOWER_BOUND = 0;

    private ByteBuffer buffer;
    private CommandProcessor commandProcessor;

    private FoodAnalyzerServer() {
        buffer = ByteBuffer.allocate(ServerConstants.BUFFER_SIZE);
        commandProcessor = new CommandProcessor();
    }

    public static void main(String[] args) {
        new FoodAnalyzerServer().startServer();
    }


    private void startServer() {
        try (ServerSocketChannel channel = ServerSocketChannel.open()) {

            channel.bind(new InetSocketAddress(ServerConstants.SERVER_HOST, ServerConstants.SERVER_PORT));
            channel.configureBlocking(false);

            System.out.println(ServerConstants.SERVER_READY_MESSAGE);
            Selector selector = Selector.open();
            channel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                int keys = selector.select();
                if (keys <= LOWER_BOUND) {
                    continue;
                }
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectedKeys.iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isReadable()) {
                        handleCommand(key);
                    } else if (key.isAcceptable()) {
                        connectClient(key, selector);
                    }
                    iterator.remove();
                }
            }
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private void connectClient(SelectionKey key, Selector selector) {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();

        SocketChannel socketChannel = null;
        try {
            socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void handleCommand(SelectionKey key) throws URISyntaxException {
        SocketChannel channel = (SocketChannel) key.channel();

        buffer.clear();
        try {
            channel.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }


        buffer.flip();
        String command = new String(buffer.array(), LOWER_BOUND, buffer.limit());
        String response = commandProcessor.processCommand(command);

        buffer.clear();
        buffer.put(response.getBytes());
        buffer.flip();

        try {
            channel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
