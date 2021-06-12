import command.CommandProcessor;
import commandTypes.ImageCommand;
import constants.ImageCommandTestConstants;
import database.PseudoDatabase;
import decoder.ImageDecoder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

public class ImageCommandTest {

    private static CommandProcessor commandProcessor;
    private static PseudoDatabase pseudoDatabase;
    private static String raffaelloString;
    private static ImageCommand imageCommand;

    @BeforeClass
    public static void initialize() {
        commandProcessor = new CommandProcessor();
        pseudoDatabase = PseudoDatabase.getInstance();
        imageCommand = new ImageCommand();

        File raffaello = new File("src/foodInfos/raffaello");

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(raffaello)));) {
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
    public void testGetParametersBothParametersExistOnlyIMGIsValid() {
        String result = imageCommand
                .getParameters(
                        ImageCommandTestConstants.BOTH_PARAMETERS_EXIST_IMG_PARAMETER_ONLY_BARCODE_COMMAND
                );
        Assert.assertEquals(ImageCommandTestConstants.IMGPath, result);
    }

    @Test
    public void testGetParametersBothParametersExistOnlyIMGIsValidSecond() {
        String result = imageCommand
                .getParameters(
                        ImageCommandTestConstants.BOTH_PARAMETERS_EXIST_IMG_PARAMETER_ONLY_SECOND_BARCODE_COMMAND
                );
        Assert.assertEquals(ImageCommandTestConstants.IMGPath, result);
    }

    @Test
    public void testBarcodeCommandByImageShouldReturnRightResult() throws URISyntaxException {
        commandProcessor
                .processCommand(ImageCommandTestConstants.GENERAL_VALID_COMMAND);
        String result = imageCommand
                .execute(ImageCommandTestConstants.BARCODE_BY_IMAGE_COMMAND);

        Assert.assertEquals(raffaelloString, result);
    }

    @Test
    public void testBarcodeCommandByImageShouldReturnErrorMessage() throws URISyntaxException {
        commandProcessor
                .processCommand(ImageCommandTestConstants.GENERAL_VALID_COMMAND);
        String result = imageCommand
                .execute(ImageCommandTestConstants.NON_EXISTING_BARCODE_BY_IMAGE_COMMAND);

        Assert.assertEquals(ImageCommandTestConstants.NON_EXISTING_BARCODE_MESSAGE, result);
    }

    @Test
    public void testBarcodeCommandByImageWhichDoesntExistShouldReturnErrorMessage() throws URISyntaxException {

        String result = imageCommand
                .execute(ImageCommandTestConstants.NON_EXISTING_IMAGE_COMMAND);

        Assert.assertEquals(ImageDecoder.ERROR_MESSAGE, result);
    }

    @Test
    public void testGetParametersOnlyIMGExists() {
        String result = imageCommand
                .getParameters(
                        ImageCommandTestConstants.IMAGE_INFO_ONLY_VALID_COMMAND
                );
        Assert.assertEquals(ImageCommandTestConstants.IMGPath, result);
    }

    @Test
    public void testWrongParametersShouldResultInNull() throws URISyntaxException {
        String result = imageCommand
                .getParameters(
                        ImageCommandTestConstants.BARCODE_INVALID_PARAMETERS
                );

        Assert.assertNull(result);
    }

}
