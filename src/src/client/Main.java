package src.client;

import src.client.input_commands.Add;

import src.shared.command.CommandFactory;
import src.shared.model.Movie;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8080));
        System.out.println("Подключено к серверу...");
        CommandFactory commandFactory = null;

        Scanner scanner = new Scanner(System.in);
        Movie movieArgument = null;

        String input = null;
        while (true) {
            System.out.print("Введите команду (или exit для выхода): ");
            input = scanner.nextLine();
            switch (input) {

                case "help":

                    System.out.println("""
                            Приветствую в моей наиполезнейшей программе!!!! Вот список доступных команд:
                            info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
                            show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении
                            add : добавить новый элемент в коллекцию: вводите данные по порядку, который будет подсказывать программа
                            update_id : обновить значение элемента коллекции, id которого равен заданному. Порядок ввода данных также будет подсказывать программа
                            remove_by_id : удалить элемент из коллекции по его id
                            clear : очистить коллекцию
                            save : сохранить коллекцию в файл
                            execute_script : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
                            exit : завершить программу (без сохранения в файл)
                            add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции
                            remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный
                            history : вывести последние 15 команд (без их аргументов)
                            remove_any_by_oscars_count oscarsCount : удалить из коллекции один элемент, значение поля oscarsCount которого эквивалентно заданному
                            count_by_oscars_count oscarsCount : вывести количество элементов, значение поля oscarsCount которых равно заданному
                            filter_less_than_genre genre : вывести элементы, значение поля genre которых меньше заданного""");
                    break;
//                case "info":
//                    hist.add("info");
//                    Info info = new Info();
//                    info.info();
//                    break;

                case "add":
                        Add add = new Add();
                        movieArgument = add.add();
                        break;



//                case "save":
//                    hist.add("save");
//                    System.out.println("Введите полный путь, куда требуется сохранить коллекцию: ");
//                    String path = (new Scanner(System.in)).nextLine();
//                    fileManager.saveCollection(path, MovieCollectionManager.getCollection());
//                    break;
//                case "show":
//                    hist.add("show");
//                    if (!MovieCollectionManager.getCollection().isEmpty()) {
//                        for (Movie movie : MovieCollectionManager.getCollection()) {
//                            System.out.println(movie);
//                        }
//                    } else {
//                        System.out.println("Your collection is empty!Try adding some elements!");
//                    }
//                    break;
//
//                case "exit":
//                    hist.add("exit");
//                    System.out.println("Завершение программы.");
//                    return;
//                case "update_id":
//                    hist.add("update id");
//                    UpdateId updateId = new UpdateId();
//                    updateId.updateId();
//                    break;
//                case "remove_by_id":
//                    hist.add("remove_by_id");
//                    RemoveById removeById = new RemoveById();
//                    removeById.removeById();
//                    break;
//                case "clear":
//                    hist.add("clear");
//                    MovieCollectionManager.getCollection().clear(); // Очищаем коллекцию
//                    System.out.println("Коллекция успешно очищена.");
//                    break;
//                case "execute_script":
//                    hist.add("execute_script");
//                    System.out.println("Enter filename with the script you want to be completed: ");
//                    String fileName = scanner.nextLine();
//                    ExecuteScript executeScript = new ExecuteScript();
//                    executeScript.executeScript(fileName);
//                    break;
//                case "add_if_max":
//                    hist.add("add_if_max");
//                    AddIfMax addIfMax = new AddIfMax();
//                    addIfMax.addIfMax();
//                    break;
//                case "remove_lower":
//                    hist.add("remove_lower");
//                    add = new Add();
//                    aMovie = add.add();
//                    RemoveLower removeLower = new RemoveLower(aMovie);
//                    removeLower.removeLower();
//                    break;
//                case "history":
//                    System.out.println(hist);
//                    break;
//                case "remove_any_by_oscars_count":
//                    validInput = false;
//                    int oscarsCount = 0;
//                    while (!validInput) {
//                        System.out.print("Введите количество оскаров (должно быть больше 0): ");
//                        String input = scanner.nextLine();
//                        try {
//                            oscarsCount = Integer.parseInt(input);
//                            if (oscarsCount <= 0) {
//                                System.out.println("Ошибка: количество оскаров должно быть больше 0!");
//                            } else {
//                                validInput = true;
//                            }
//                        } catch (NumberFormatException e) {
//                            System.out.println("Ошибка: необходимо ввести целое число!");
//                        }
//                    }
//                    RemoveAnyByOscarsCount removeAnyByOscarsCount = new RemoveAnyByOscarsCount(oscarsCount);
//                    removeAnyByOscarsCount.removeAnyByOscarsCount();
//                    break;
//                case "count_by_oscars_count":
//                    validInput = false;
//                    oscarsCount = 0;
//                    while (!validInput) {
//                        System.out.print("Введите количество оскаров (должно быть больше 0): ");
//                        String input = scanner.nextLine();
//                        try {
//                            oscarsCount = Integer.parseInt(input);
//                            if (oscarsCount <= 0) {
//                                System.out.println("Ошибка: количество оскаров должно быть больше 0!");
//                            } else {
//                                validInput = true;
//                            }
//                        } catch (NumberFormatException e) {
//                            System.out.println("Ошибка: необходимо ввести целое число!");
//                        }
//                    }
//                    CountByOscarsCount countByOscarsCount = new CountByOscarsCount(oscarsCount);
//                    countByOscarsCount.execute();
//                    break;
//                case "filter_less_than_genre":
//                    MovieGenre genre = null;
//                    validInput = false;
//                    while (!validInput) {
//                        System.out.println("Доступные жанры: " + Arrays.toString(MovieGenre.values()));
//                        System.out.print("Введите жанр: ");
//                        String input = scanner.nextLine().trim().toUpperCase();
//
//                        try {
//                            genre = MovieGenre.valueOf(input);
//                            validInput = true;
//                        } catch (IllegalArgumentException e) {
//                            System.out.println("Ошибка: '" + input + "' не является допустимым жанром!");
//                            System.out.println("Пожалуйста, выберите один из: " + Arrays.toString(MovieGenre.values()));
//                        }
//                    }
//
//                    FilterLessThanGenre filterLessThanGenre = new FilterLessThanGenre(genre);
//                    filterLessThanGenre.execute();
//                    break;
//
//

                default:
                    System.out.println("Неизвестная команда. Введите 'help' для получения списка команд.");
                    break;
            }
            // Создаем объект команды
            commandFactory = new CommandFactory(input, movieArgument);

            // Отправляем на сервер
            byte[] data = serialize(commandFactory);
            socketChannel.write(ByteBuffer.wrap(data));
            System.out.println("Команда отправлена: " + commandFactory.getArgument());

            // Получаем ответ
            ByteBuffer buffer = ByteBuffer.allocate(8192);
            int bytesRead = 0;
            while (bytesRead == 0) {
                bytesRead = socketChannel.read(buffer);
            }

            buffer.flip();
            byte[] responseBytes = new byte[buffer.limit()];
            buffer.get(responseBytes);

            CommandFactory response = deserialize(responseBytes);
            System.out.println("Ответ от сервера: " + response.getArgument());

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