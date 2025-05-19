package src.client.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandReader {
    private final Scanner scanner;
    private final List<String> commandHistory = new ArrayList<>();
    private static final int MAX_HISTORY = 10;

    public CommandReader(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Считывает команду и её аргумент (если есть)
     * @return Массив: [команда, аргумент] или [команда]
     */
    public Object[] readCommandWithArgs() {
        System.out.print("> ");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            return new Object[]{"", null};
        }

        // Добавляем команду в историю
        addToHistory(input);

        String[] parts = input.split("\\s+", 2); // Разделяем на команду и аргумент
        if (parts.length == 1) {
            return new Object[]{parts[0], null};
        } else {
            return new Object[]{parts[0], parts[1]};
        }
    }

    /**
     * Добавляет команду в историю
     */
    private void addToHistory(String command) {
        commandHistory.add(command);
        if (commandHistory.size() > MAX_HISTORY) {
            commandHistory.remove(0);
        }
    }

    /**
     * Получить историю команд
     */
    public List<String> getCommandHistory() {
        return new ArrayList<>(commandHistory);
    }
}
