package src.client.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InputReader {

    private final Scanner scanner;

    public InputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Запрашивает у пользователя данные о фильме и возвращает их в виде строки
     * с разделением по \n. Эти данные можно передавать на сервер.
     */
    public String readMovieData() {
        StringBuilder dataBuilder = new StringBuilder();

        // 1. Название фильма
        String name = promptRequired("Название фильма:", "Название не может быть пустым");
        dataBuilder.append(name).append("\n");

        // 2. Координата X (double)
        String x = promptDouble("Координата X:");
        dataBuilder.append(x).append("\n");

        // 3. Координата Y (long)
        String y = promptLongWithMax("Координата Y (<= 178):", 178);
        dataBuilder.append(y).append("\n");

        // 4. Количество оскаров (> 0)
        String oscars = promptIntWithMin("Количество оскаров (>= 1):", 1);
        dataBuilder.append(oscars).append("\n");

        // 5. Бюджет (Integer или пустое)
        System.out.print("Бюджет (оставьте пустым для null): ");
        String budget = scanner.nextLine().trim();
        dataBuilder.append(budget).append("\n");

        // 6. Жанр (ACTION, COMEDY, DRAMA, HORROR, SCIENCE_FICTION)
        String genre = promptEnum("Жанр (ACTION, COMEDY, DRAMA, HORROR, SCIENCE_FICTION):",
                Arrays.asList("ACTION", "COMEDY", "DRAMA", "HORROR", "SCIENCE_FICTION"));
        dataBuilder.append(genre).append("\n");

        // 7. MPAA Rating (G, PG, PG_13, R, NC_17)
        System.out.print("MPAA рейтинг (G, PG, PG_13, R, NC_17, оставьте пустым для null): ");
        String rating = scanner.nextLine().trim().toUpperCase();
        dataBuilder.append(rating.isEmpty() ? "" : rating).append("\n");

        // 8. Имя оператора
        String operatorName = promptRequired("Имя оператора:", "Имя оператора не может быть пустым");
        dataBuilder.append(operatorName).append("\n");

        // 9. Дата рождения оператора (YYYY-MM-DD)
        String birthday = promptDate("Дата рождения оператора (YYYY-MM-DD):");
        dataBuilder.append(birthday).append("\n");

        // 10. Рост оператора (> 0)
        String height = promptDoubleWithMin("Рост оператора (> 0):", 0.0);
        dataBuilder.append(height).append("\n");

        // 11. Вес оператора (> 0)
        String weight = promptIntWithMin("Вес оператора (> 0):", 1);
        dataBuilder.append(weight).append("\n");

        // 12. Паспортные данные
        String passportID = promptRequired("Паспортные данные оператора:", "Паспортные данные не могут быть пустыми");
        dataBuilder.append(passportID).append("\n");

        return dataBuilder.toString();
    }

    // === Вспомогательные методы ===

    private String promptRequired(String prompt, String errorMsg) {
        while (true) {
            System.out.print(prompt + " ");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println(errorMsg);
        }
    }

    private String promptDouble(String prompt) {
        while (true) {
            System.out.print(prompt + " ");
            String input = scanner.nextLine().trim();
            try {
                Double.parseDouble(input);
                return input;
            } catch (NumberFormatException e) {
                System.out.println("Введите корректное число.");
            }
        }
    }

    private String promptDoubleWithMin(String prompt, double min) {
        while (true) {
            System.out.print(prompt + " ");
            String input = scanner.nextLine().trim();
            try {
                double value = Double.parseDouble(input);
                if (value > min) return input;
                System.out.println("Значение должно быть больше " + min);
            } catch (NumberFormatException e) {
                System.out.println("Введите корректное число.");
            }
        }
    }

    private String promptLongWithMax(String prompt, long max) {
        while (true) {
            System.out.print(prompt + " ");
            String input = scanner.nextLine().trim();
            try {
                long value = Long.parseLong(input);
                if (value <= max) return input;
                System.out.println("Значение должно быть ≤ " + max);
            } catch (NumberFormatException e) {
                System.out.println("Введите корректное число.");
            }
        }
    }

    private String promptIntWithMin(String prompt, int min) {
        while (true) {
            System.out.print(prompt + " ");
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value >= min) return input;
                System.out.println("Значение должно быть ≥ " + min);
            } catch (NumberFormatException e) {
                System.out.println("Введите корректное число.");
            }
        }
    }

    private String promptDate(String prompt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        while (true) {
            System.out.print(prompt + " ");
            String input = scanner.nextLine().trim();
            try {
                LocalDate.parse(input, formatter);
                return input;
            } catch (DateTimeParseException e) {
                System.out.println("Введите дату в формате YYYY-MM-DD.");
            }
        }
    }

    private String promptEnum(String prompt, List<String> allowedValues) {
        while (true) {
            System.out.print(prompt + " ");
            String input = scanner.nextLine().trim().toUpperCase();
            if (allowedValues.contains(input)) {
                return input;
            }
            System.out.println("Допустимые значения: " + String.join(", ", allowedValues));
        }
    }
}