package command;

import constants.CommandProcessorConstants;
import factory.CommandFactory;
import http.RequestSender;

import java.net.URISyntaxException;

public class CommandProcessor {

    /**
     * Where all the magic happens...
     * Reads a command and tries to process it to eventually print it on the console
     * If something goes wrong, an error message is returned
     */
    public String processCommand(String commandLine) throws URISyntaxException {
        if (commandLine == null) {
            return CommandProcessorConstants.WRONG_INPUT_MESSAGE;
        }

        Command command = CommandFactory.getCommandType(commandLine);
        if (isCommandTypeInvalid(command)) {
            return CommandProcessorConstants.WRONG_COMMAND_MESSAGE;
        }

        String result = command.execute(commandLine);

        return result;
    }

    /**
     * Checks if the commandType is not null
     */
    private boolean isCommandTypeInvalid(Command commandType) {
        return commandType == null;
    }

}