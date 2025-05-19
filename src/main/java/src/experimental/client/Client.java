package src.experimental.client;

import src.experimental.entity.Message;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 12345;

        try (Socket socket = new Socket(host, port);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Подключено к серверу " + host + ":" + port);

            System.out.print("Введите ваше имя: ");
            String name = scanner.nextLine();

            System.out.print("Введите сообщение: ");
            String text = scanner.nextLine();

            // Создаем и отправляем сообщение
            Message message = new Message(name, text);
            oos.writeObject(message);
            oos.flush();
            System.out.println("Отправлено серверу: " + message);

            // Получаем ответ от сервера
            Message response = (Message) ois.readObject();
            System.out.println("Получено от сервера: " + response);

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ошибка клиента: " + e.getMessage());
        }
    }
}
