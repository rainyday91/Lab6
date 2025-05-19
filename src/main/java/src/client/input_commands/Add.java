package src.client.input_commands;

import src.client.command.Command;
import src.shared.model.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;




/**
 *
 */
public class Add implements Command {
    public Add() {
    }

    /**
     *
     * @return
     */
    public synchronized Movie add() {
        Scanner scanner = new Scanner(System.in);

        try {
            Long id = 2L;
            String name = null;
            while (name == null || name.isEmpty()) {
                System.out.print("Введите название фильма: ");
                name = scanner.nextLine();
                if (name == null || name.isEmpty()) {
                    System.out.println("Название фильма не может быть пустым.");

                }

            }


            double x = 0;
            boolean validInput = false;

            while (!validInput) {
                System.out.print("Введите координату x: ");
                String input = scanner.nextLine();

                try {
                    x = Double.parseDouble(input);
                    validInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: необходимо ввести десятичную дробь!");
                }
            }
            long y = 0;
            validInput = false;

            while (!validInput) {
                System.out.print("Введите координату y: ");
                String input = scanner.nextLine();

                try {
                    y = Long.parseLong(input);

                    if (y > 178) {
                        System.out.println("y должна быть не больше 178.");
                    } else {
                        validInput = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: вы ввели не целое число или число, большее 178! ");
                }
            }
            Coordinates coordinates = new Coordinates(x, y);
            ZonedDateTime creationDate = ZonedDateTime.now();
            int oscarsCount = 0;
            validInput = false;
            while (!validInput) {
                System.out.print("Введите количество оскаров (должно быть больше 0): ");
                String input = scanner.nextLine();
                try {
                    oscarsCount = Integer.parseInt(input);
                    if (oscarsCount <= 0) {
                        System.out.println("Ошибка: количество оскаров должно быть больше 0!");
                    } else {
                        validInput = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: необходимо ввести целое число!");
                }
            }
            Integer budget = null;
            validInput = false;
            while (!validInput) {
                System.out.print("Введите бюджет (должно быть больше 0 или оставьте пустым): ");
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    budget = null;
                    validInput = true;
                } else {
                    try {
                        int value = Integer.parseInt(input);
                        if (value > 0) {
                            budget = value;
                            validInput = true;
                        } else {
                            System.out.println("Ошибка: бюджет должен быть больше 0!");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: введите целое число или оставьте пустым!");
                    }
                }
            }
            MovieGenre genre = null;
            validInput = false;
            while (!validInput) {
                System.out.println("Доступные жанры: " + Arrays.toString(MovieGenre.values()));
                System.out.print("Введите жанр: ");
                String input = scanner.nextLine().trim().toUpperCase();

                try {
                    genre = MovieGenre.valueOf(input);
                    validInput = true;
                } catch (IllegalArgumentException e) {
                    System.out.println("Ошибка: '" + input + "' не является допустимым жанром!");
                    System.out.println("Пожалуйста, выберите один из: " + Arrays.toString(MovieGenre.values()));
                }
            }

            // Ввод mpaaRating
            MpaaRating mpaaRating = null;
            validInput = false;

            while (!validInput) {
                System.out.println("Доступные рейтинги: " + Arrays.toString(MpaaRating.values()) + " (или оставьте пустым)");
                System.out.print("Введите рейтинг MPAA: ");
                String input = scanner.nextLine().trim().toUpperCase();

                if (input.isEmpty()) {
                    validInput = true;
                } else {
                    try {
                        mpaaRating = MpaaRating.valueOf(input);
                        validInput = true;
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка: '" + input + "' не является допустимым рейтингом!");
                    }
                }
            }
            String operatorName = null;
            validInput = false;

            while (!validInput) {
                System.out.print("Введите имя оператора (не может быть пустым): ");
                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("Ошибка: имя оператора не может быть пустым!");
                } else {
                    operatorName = input;
                    validInput = true;
                }
            }
            LocalDate birthday = null;
            validInput = false;

            while (!validInput) {
                System.out.print("Введите дату рождения в формате ГОД-МЕСЯЦ-ДАТА: ");
                String input = scanner.nextLine();

                try {
                    birthday = LocalDate.parse(input);


                    if (birthday.isAfter(LocalDate.now())) {
                        System.out.println("Ошибка: дата рождения не может быть в будущем!");
                    } else {
                        validInput = true;
                    }
                } catch (DateTimeParseException e) {
                    System.out.println("Error: неверный формат даты! Вот верный: ДД.ММ.ГГГГ");
                }
            }
            double height = 0;
            validInput = false;
            while (!validInput) {
                System.out.print("Введите рост оператора: ");
                String input = scanner.nextLine();
                try {
                    height = Double.parseDouble(input);
                    if (height <= 0) {
                        System.out.println("Ошибка: рост оператора должна быть больше 0!");
                    } else {
                        validInput = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: необходимо ввести целое число!");
                }
            }
            long weight = 0;
            validInput = false;
            while (!validInput) {
                System.out.print("Введите вес оператора: ");
                String input = scanner.nextLine();
                try {
                    weight = Long.parseLong(input);
                    if (weight <= 0) {
                        System.out.println("Ошибка: вес оператора должна быть больше 0!");
                    } else {
                        validInput = true;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: необходимо ввести целое число!");
                }
            }
            String passportID = null;
            while (passportID == null || passportID.isEmpty()) {
                System.out.print("Введите паспортные данные оператора: ");
                passportID = scanner.nextLine();
                if (passportID == null || passportID.isEmpty()) {
                    System.out.println("Паспортные данные не могут быть пустыми.");

                }

            }
            Person operator = new Person(operatorName, birthday, height, weight, passportID);

            return new Movie(null, name, coordinates, creationDate, oscarsCount, budget, genre, mpaaRating, operator);


        } catch (Exception e) {
            System.err.println("Ошибка при добавлении фильма: " + e.getMessage());

        }
        return null;
    }
}
