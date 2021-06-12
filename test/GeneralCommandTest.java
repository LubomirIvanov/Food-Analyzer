
import command.CommandProcessor;
import commandTypes.BarcodeCommand;
import commandTypes.GeneralCommand;
import constants.*;
import database.PseudoDatabase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

public class GeneralCommandTest {

    private static CommandProcessor commandProcessor;
    private static PseudoDatabase pseudoDatabase;
    private static String raffaelloString;
    private static GeneralCommand generalCommand;
    private static String raffaelloLocalString;

    @BeforeClass
    public static void initialize() {
        commandProcessor = new CommandProcessor();
        pseudoDatabase = PseudoDatabase.getInstance();
        generalCommand = new GeneralCommand();

        File raffaelloLocal = new File("src/foodInfos/raffaelloLocal");
        File raffaello = new File("src/foodInfos/raffaello");

        try (BufferedReader localReader = new BufferedReader(new InputStreamReader(new FileInputStream(raffaelloLocal)));
             BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(raffaello)))) {

            raffaelloLocalString = localReader
                    .lines()
                    .collect(Collectors.joining(System.lineSeparator()));
            raffaelloString = reader
                    .lines()
                    .collect(Collectors.joining(System.lineSeparator()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Before
    public void clearDatabase() {
        pseudoDatabase.clear();
    }

    @Test
    public void testGeneralCommandShouldReturnRightResult() throws URISyntaxException {
        String result = generalCommand
                .execute(GeneralCommandTestConstants.GENERAL_VALID_COMMAND);
        Assert.assertEquals(raffaelloString, result);
    }

    @Test
    public void testGeneralCommandShouldReturnRightResultForLocal() throws URISyntaxException {
        commandProcessor
                .processCommand(GeneralCommandTestConstants.GENERAL_VALID_COMMAND);
        String result = generalCommand
                .execute(GeneralCommandTestConstants.GENERAL_VALID_COMMAND);

        Assert.assertEquals(raffaelloLocalString, result);
    }

    @Test
    public void testGetParametersGeneralCommand() {
        String result = generalCommand
                .getParameters(
                        GeneralCommandTestConstants.GENERAL_VALID_COMMAND
                );
        Assert.assertEquals(GeneralCommandTestConstants.GENERAL_INFO_PARAMETER, result);
    }

    @Test
    public void testBadRequestShouldReturnProperMessage() throws URISyntaxException {
        String result = generalCommand
                .execute(GeneralCommandTestConstants.GENERAL_BAD_REQUEST);

        Assert.assertEquals(GeneralCommandTestConstants.BAD_REQUEST_MESSAGE, result);
    }

    @Test
    public void testBadRequestSecondShouldReturnProperMessage() throws URISyntaxException {
        String result = generalCommand
                .execute(GeneralCommandTestConstants.GENERAL_BAD_REQUEST_SECOND);

        Assert.assertEquals(CommandProcessorConstants.BAD_REQUEST_MESSAGE, result);
    }

    @Test
    public void testNoProductsFoundShouldReturnProperMessage() throws URISyntaxException {
        String result = generalCommand
                .execute(GeneralCommandTestConstants.GENERAL_NO_PRODUCT_FOUND_REQUEST);

        Assert.assertEquals(GeneralCommandTestConstants.NO_PRODUCTS_FOUND_MESSAGE, result);
    }


}
