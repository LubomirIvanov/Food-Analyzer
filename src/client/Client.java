package client;

import constants.ServerConstants;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client {

    private static ByteBuffer buffer = ByteBuffer.allocateDirect(ServerConstants.BUFFER_SIZE);
    private static String CHARSET = "UTF-8";

    public static void main(String[] args) {

        try (SocketChannel socketChannel = SocketChannel.open();

             Scanner scanner = new Scanner(System.in)) {
            socketChannel.connect(new InetSocketAddress(ServerConstants.SERVER_HOST, ServerConstants.SERVER_PORT));

            System.out.println(ServerConstants.SERVER_CONNECT_MESSAGE);

            while (true) {
                String message = scanner.nextLine(); // read a line from the console

                if (message.equals(ServerConstants.CLOSING_COMMAND)) {
                    break;
                }
                if (message.isEmpty()) {
                    continue;
                }

                buffer.clear(); // switch to writing mode
                buffer.put(message.getBytes()); // buffer fill
                buffer.flip(); // switch to reading mode
                socketChannel.write(buffer); // buffer drain

                buffer.clear(); // switch to writing mode
                socketChannel.read(buffer); // buffer fill
                buffer.flip(); // switch to reading mode

                byte[] byteArray = new byte[buffer.remaining()];
                buffer.get(byteArray);
                String reply = new String(byteArray, CHARSET); // buffer drain

                System.out.println(reply);
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(ServerConstants.STOPPING_THREAD_MESSAGE);
        }
    }
}