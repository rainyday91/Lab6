package src.client;

import src.shared.model.Response;

public class ResponsePrinter {

    public void print(Response response) {
        if (response == null) {
            System.out.println("ResponsePrinter Пустой ответ от сервера.");
            return;
        }

        if (response.isSuccess()) {
            System.out.println(" ResponsePrinter✅ Успех: " + response.getMessage());
        } else {
            System.out.println("ResponsePrinterError❌ Ошибка: " + response.getMessage());
        }

        if (response.hasCollection()) {
            System.out.println("\nТекущая коллекция:");
            response.getCollection().forEach(System.out::println);
        }
    }
}
