//package src.server;
//
//
//import src.Command;
//import src.server.model.MovieCollectionManager;
//import src.shared.model.Movie;
//import src.shared.network.Request;
//import src.shared.model.Response;
//
//import java.util.TreeSet;
//
//public class CommandProcessor {
//
//    public CommandProcessor() {
//
//    }
//
//    public Response processCommand(Request request) {
//        try {
//            Command command = server.commands.CommandFactory.getCommand(request.getCommandName());
//            String result = command.execute(request.getArgument());
//            TreeSet<Movie> collection = MovieCollectionManager.getCollection();
//            return new Response(result, MovieCollectionManager.getCollection());
//        } catch (Exception e) {
//            return new Response("Error: " + e.getMessage(), (TreeSet<Movie>) null);
//        }
//    }
//}