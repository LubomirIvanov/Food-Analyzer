
import command.CommandProcessor;
import commandTypes.DescriptionCommand;
import constants.*;
import database.PseudoDatabase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

public class DescriptionCommandTest {
    private static PseudoDatabase pseudoDatabase;
    private static CommandProcessor commandProcessor;
    private static DescriptionCommand descriptionCommand;
    private static String raffaelloDescriptionString;
    private static String raffaelloDescriptionLocalString;

    @BeforeClass
    public static void initialize() {
        pseudoDatabase = PseudoDatabase.getInstance();
        commandProcessor = new CommandProcessor();
        descriptionCommand = new DescriptionCommand();

        File raffaelloDescription = new File("src/foodInfos/raffaelloDescription");
        File raffaelloDescriptionLocal = new File("src/foodInfos/raffaelloDescriptionLocal");


        try (BufferedReader descriptionReader = new BufferedReader(new InputStreamReader(new FileInputStream(raffaelloDescription)));
             BufferedReader descriptionLocalReader = new BufferedReader(new InputStreamReader(new FileInputStream(raffaelloDescriptionLocal)));) {

            raffaelloDescriptionString = descriptionReader
                    .lines()
                    .collect(Collectors.joining(System.lineSeparator()));

            raffaelloDescriptionLocalString = descriptionLocalReader
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
    public void testDescriptionCommandShouldReturnRightResult() throws URISyntaxException {
        String result = descriptionCommand
                .execute(DescriptionCommandTestConstants.DESCRIPTION_VALID_COMMAND);

        Assert.assertEquals(raffaelloDescriptionString, result);
    }

    @Test
    public void testDescriptionLocalCommandShouldReturnRightResult() throws URISyntaxException {
        commandProcessor
                .processCommand(DescriptionCommandTestConstants.DESCRIPTION_VALID_COMMAND);
        String result = descriptionCommand
                .execute(DescriptionCommandTestConstants.DESCRIPTION_VALID_COMMAND);

        Assert.assertEquals(raffaelloDescriptionLocalString, result);
    }


    @Test
    public void testBadRequestShouldReturnProperMessage() throws URISyntaxException {
        String result = descriptionCommand
                .execute(DescriptionCommandTestConstants.DESCRIPTION_BAD_REQUEST);

        Assert.assertEquals(DescriptionCommandTestConstants.BAD_REQUEST_MESSAGE, result);
    }

    @Test
    public void testNoProductsFoundShouldReturnProperMessage() throws URISyntaxException {
        String result = descriptionCommand
                .execute(DescriptionCommandTestConstants.DESCRIPTION_NO_PRODUCT_FOUND_REQUEST);

        Assert.assertEquals(DescriptionCommandTestConstants.NO_PRODUCTS_FOUND_MESSAGE, result);
    }

}
