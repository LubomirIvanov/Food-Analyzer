import command.CommandProcessor;
import commandTypes.BarcodeCommand;
import constants.BarcodeCommandTestConstants;
import constants.CommandFactoryConstants;
import database.PseudoDatabase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.util.stream.Collectors;

public class BarcodeCommandTest {

    private static CommandProcessor commandProcessor;
    private static PseudoDatabase pseudoDatabase;
    private static String raffaelloString;
    private static BarcodeCommand barcodeCommand;

    @BeforeClass
    public static void initialize() {
        commandProcessor = new CommandProcessor();
        pseudoDatabase = PseudoDatabase.getInstance();
        barcodeCommand = new BarcodeCommand();

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
    public void testGetParametersBothParametersExistOnlyBarcodeIsValid() {

        String result = barcodeCommand
                .getParameters(
                        BarcodeCommandTestConstants.BOTH_PARAMETERS_EXIST_BARCODE_PARAMETER_ONLY_BARCODE_COMMAND
                );
        Assert.assertEquals(BarcodeCommandTestConstants.BARCODE, result);
    }

    @Test
    public void testGetParametersBothParametersExistOnlyBarcodeIsValidSecond() {
        String result = barcodeCommand
                .getParameters(
                        BarcodeCommandTestConstants.BOTH_PARAMETERS_EXIST_BARCODE_PARAMETER_ONLY_SECOND__BARCODE_COMMAND

                );
        Assert.assertEquals(BarcodeCommandTestConstants.BARCODE, result);
    }

    @Test
    public void testGetParametersOnlyBarcodeExists() {
        String result = barcodeCommand
                .getParameters(
                        BarcodeCommandTestConstants.BARCODE_INFO_ONLY_VALID_COMMAND

                );
        Assert.assertEquals(BarcodeCommandTestConstants.BARCODE, result);
    }

    @Test
    public void testGetParametersBothImgAndCode() {
        String result = barcodeCommand
                .getParameters(
                        BarcodeCommandTestConstants.BOTH_PARAMETERS_EXIST_BARCODE_COMMAND
                );
        Assert.assertEquals(BarcodeCommandTestConstants.BARCODE, result);
    }

    @Test
    public void testGetParametersBothImgAndCodeSecond() {
        String result = barcodeCommand
                .getParameters(
                        BarcodeCommandTestConstants.BOTH_PARAMETERS_EXIST_SECOND_BARCODE_COMMAND
                );
        Assert.assertEquals(BarcodeCommandTestConstants.BARCODE, result);
    }

    @Test
    public void testWrongParametersShouldResultInNull() throws URISyntaxException {
        String result = barcodeCommand
                .getParameters(
                        BarcodeCommandTestConstants.BARCODE_INVALID_PARAMETERS
                );

        Assert.assertEquals(null, result);
    }

    @Test
    public void testBarcodeCommandWithValidBarcodeShouldReturnRightResult() throws URISyntaxException {
        commandProcessor
                .processCommand(BarcodeCommandTestConstants.GENERAL_VALID_COMMAND);

        String result = barcodeCommand
                .execute(BarcodeCommandTestConstants.BARCODE_INFO_ONLY_VALID_COMMAND);

        Assert.assertEquals(raffaelloString, result);
    }

    @Test
    public void testBarcodeCommandWithNonExistingBarcodeShouldReturnRightResult() throws URISyntaxException {
        String result = barcodeCommand
                .execute(BarcodeCommandTestConstants.BARCODE_NON_EXISTING_COMMAND);

        Assert.assertEquals(CommandFactoryConstants.NO_SUCH_ITEM_WITH_BARCODE_MESSAGE, result);
    }
}
