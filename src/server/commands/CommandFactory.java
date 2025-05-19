//package server.commands;
//
//
//import src.*;
//
//public class CommandFactory {
//    public static Command getCommand(String commandName) {
//        return switch (commandName.toLowerCase()) {
//            case "info" -> new Info();
//            case "show" -> new Show();
//            case "add" -> new AddData();
//            case "update_id" -> new UpdateIdData();
//            case "remove_by_id" -> new RemoveByIdData();
//            case "clear" -> new Clear();
//            case "save" -> new SaveData();
//            case "execute_script" -> new ExecuteScript();
//            case "add_if_max" -> new AddIfMaxData();
//            case "remove_lower" -> new RemoveLowerData();
//            case "history" -> new History();
//            case "remove_any_by_oscars_count" -> new RemoveAnyByOscarsCountData();
//            case "count_by_oscars_count" -> new CountByOscarsCountData();
//            case "filter_less_than_genre" -> new FilterLessThanGenreData();
//            default -> throw new IllegalArgumentException("Unknown command: " + commandName);
//        };
//    }
//}