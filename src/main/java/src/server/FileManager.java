package src.server;

import org.json.JSONObject;
import src.shared.model.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TreeSet;
public class FileManager {
    private final String filePath;

    public FileManager(String filePath) {
        this.filePath = filePath;
    }

    public void saveCollection(TreeSet<src.server.model.Movie> collection) {
        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("[\n");

        for (src.server.model.Movie movie : collection) {
            jsonBuilder.append("  {\n");
            jsonBuilder.append("    \"id\": ").append(movie.getId()).append(",\n");
            jsonBuilder.append("    \"name\": \"").append(escapeJson(movie.getName())).append("\",\n");

            // Координаты
            jsonBuilder.append("    \"coordinates\": {\n");
            jsonBuilder.append("      \"x\": ").append(movie.getCoordinates().getX()).append(",\n");
            jsonBuilder.append("      \"y\": ").append(movie.getCoordinates().getY()).append("\n");
            jsonBuilder.append("    },\n");

            // Дата создания
            jsonBuilder.append("    \"creationDate\": \"").append(formatDate(movie.getCreationDate())).append("\",\n");

            // Оскары
            jsonBuilder.append("    \"oscarsCount\": ").append(movie.getOscarsCount()).append(",\n");

            // Бюджет
            jsonBuilder.append("    \"budget\": ").append(movie.getBudget() == null ? "null" : movie.getBudget()).append(",\n");

            // Жанр
            jsonBuilder.append("    \"genre\": \"").append(movie.getGenre()).append("\",\n");

            // MPAA рейтинг
            jsonBuilder.append("    \"mpaaRating\": ").append(movie.getMpaaRating() == null ? "null" : "\"" + movie.getMpaaRating() + "\"").append(",\n");

            // Персона оператор
            jsonBuilder.append("    \"operator\": {\n");
            jsonBuilder.append("      \"name\": \"").append(escapeJson(movie.getOperator().getName())).append("\",\n");
            jsonBuilder.append("      \"birthday\": \"").append(movie.getOperator().getBirthday()).append("\",\n");
            jsonBuilder.append("      \"height\": ").append(movie.getOperator().getHeight()).append(",\n");
            jsonBuilder.append("      \"weight\": ").append(movie.getOperator().getWeight()).append(",\n");
            jsonBuilder.append("      \"passportID\": \"").append(escapeJson(movie.getOperator().getPassportID())).append("\"\n");
            jsonBuilder.append("    }\n");
            jsonBuilder.append("  },\n");
        }

        if (!collection.isEmpty()) {
            jsonBuilder.deleteCharAt(jsonBuilder.length() - 2); // удаляем последнюю запятую
        }

        jsonBuilder.append("\n]");


        try (FileOutputStream fos = new FileOutputStream(filePath);
             OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8)) {

            osw.write(jsonBuilder.toString());
            System.out.println("Коллекция успешно сохранена в файл: " + filePath);

        } catch (IOException e) {
            System.err.println("Ошибка при сохранении коллекции в файл: " + e.getMessage());
        }
    }

    /**
     * Экранирует специальные символы в JSON-строке.
     */
    private String escapeJson(String input) {
        if (input == null) return "";
        return input.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\b", "\\b")
                .replace("\f", "\\f")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    /**
     * Форматирует дату в строку.
     */
    private String formatDate(ZonedDateTime date) {
        return date.format(DateTimeFormatter.ISO_ZONED_DATE_TIME);
    }

    /**
     * Загружает коллекцию из файла.
     */
    public TreeSet<Movie> loadCollection() throws IOException {
        File file = new File(filePath);
        if (!file.exists() || file.length() == 0) {
            return new TreeSet<>();
        }

        try (FileReader reader = new FileReader(file)) {
            StringBuilder jsonContent = new StringBuilder();
            int character;
            while ((character = reader.read()) != -1) {
                jsonContent.append((char) character);
            }

            String jsonString = jsonContent.toString().trim();
            if (jsonString.startsWith("[") && jsonString.endsWith("]")) {
                jsonString = jsonString.substring(1, jsonString.length() - 1).trim();
                String[] movieEntries = jsonString.split(",\\s*\\{");

                TreeSet<Movie> movies = new TreeSet<>();

                for (String entry : movieEntries) {
                    String movieJson = entry.startsWith("{") ? entry : "{" + entry;
                    if (!movieJson.endsWith("}")) movieJson += "}";
                    JSONObject jsonObject = new JSONObject(movieJson);

                    Long id = jsonObject.getLong("id");
                    String name = jsonObject.getString("name");

                    JSONObject coordObj = jsonObject.getJSONObject("coordinates");
                    double x = coordObj.getDouble("x");
                    long y = coordObj.getLong("y");
                    Coordinates coordinates = new Coordinates(x, y);

                    ZonedDateTime creationDate = ZonedDateTime.parse(jsonObject.getString("creationDate"));

                    int oscarsCount = jsonObject.getInt("oscarsCount");
                    Integer budget = jsonObject.has("budget") && !jsonObject.isNull("budget") ?
                            jsonObject.getInt("budget") : null;

                    MovieGenre genre = MovieGenre.valueOf(jsonObject.getString("genre"));
                    MpaaRating mpaaRating = jsonObject.has("mpaaRating") && !jsonObject.isNull("mpaaRating") ?
                            MpaaRating.valueOf(jsonObject.getString("mpaaRating")) : null;

                    JSONObject operatorObj = jsonObject.getJSONObject("operator");
                    String operatorName = operatorObj.getString("name");
                    LocalDate birthday = LocalDate.parse(operatorObj.getString("birthday"));
                    double height = operatorObj.getDouble("height");
                    long weight = operatorObj.getLong("weight");
                    String passportID = operatorObj.getString("passportID");
                    Person operator = new Person(operatorName, birthday, height, weight, passportID);

                    Movie movie = new Movie(id, name, coordinates, creationDate, oscarsCount, budget, genre, mpaaRating, operator);
                    movies.add(movie);
                }

                return movies;
            } else {
                throw new IOException("Неверный формат JSON в файле.");
            }
        } catch (Exception e) {
            throw new IOException("Ошибка при чтении файла: " + e.getMessage(), e);
        }
    }
}