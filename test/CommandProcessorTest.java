import command.CommandProcessor;
import constants.CommandProcessorTestConstants;
import database.PseudoDatabase;
import decoder.ImageDecoder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

public class CommandProcessorTest {

    private static CommandProcessor commandProcessor;


    @BeforeClass
    public static void initialize() {
        commandProcessor = new CommandProcessor();
    }

    @Test
    public void testNullInputShouldResultInErrorMessage() throws URISyntaxException {
        String result = commandProcessor
                .processCommand(null);

        Assert.assertEquals(CommandProcessorTestConstants.NULL_INPUT_MESSAGE, result);
    }

    @Test
    public void testWrongCommandShouldResultInErrorMessage() throws URISyntaxException {
        String result = commandProcessor
                .processCommand(CommandProcessorTestConstants.WRONG_COMMAND);

        Assert.assertEquals(CommandProcessorTestConstants.WRONG_COMMAND_MESSAGE, result);
    }

    @Test
    public void testNullParametersShouldResultInErrorMessage() throws URISyntaxException {
        String result = commandProcessor
                .processCommand(CommandProcessorTestConstants.NULL_PARAMETERS_COMMAND);

        Assert.assertEquals(CommandProcessorTestConstants.WRONG_COMMAND_MESSAGE, result);
    }
}
