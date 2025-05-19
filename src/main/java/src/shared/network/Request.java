package src.shared.network;



import java.io.Serial;
import java.io.Serializable;

public class Request implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String commandName;
    private final Object argument;

    public Request(String commandName, Object argument) {
        this.commandName = commandName;
        this.argument = argument;
    }

    public String getCommandName() {
        return commandName;
    }

    public Object getArgument() {
        return argument;
    }
    // Getters
}