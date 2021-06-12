import command.Command;
import commandTypes.BarcodeCommand;
import commandTypes.DescriptionCommand;
import commandTypes.GeneralCommand;
import commandTypes.ImageCommand;
import constants.CommandFactoryTestConstants;
import factory.CommandFactory;
import org.junit.Assert;
import org.junit.Test;

public class CommandFactoryTest {

    @Test
    public void testImageCommandShouldReturnRightCommandType() {

        Command result = CommandFactory.getCommandType(CommandFactoryTestConstants.BARCODE_BY_IMAGE_COMMAND);

        Assert.assertTrue(result instanceof ImageCommand);
    }

    @Test
    public void testImageCommandWithBothParametersShouldReturnRightCommandType() {
        Command result = CommandFactory.getCommandType(CommandFactoryTestConstants.BOTH_PARAMETERS_EXIST_IMG_PARAMETER_ONLY_BARCODE_COMMAND);

        Assert.assertTrue(result instanceof ImageCommand);
    }

    @Test
    public void testGeneralCommandShouldReturnRightCommandType() {
        Command result = CommandFactory.getCommandType(CommandFactoryTestConstants.GENERAL_VALID_COMMAND);

        Assert.assertTrue(result instanceof GeneralCommand);
    }

    @Test
    public void testBarcodeCommandShouldReturnRightCommandType() {
        Command result = CommandFactory.getCommandType(CommandFactoryTestConstants.BARCODE_VALID_COMMAND);

        Assert.assertTrue(result instanceof BarcodeCommand);
    }

    @Test
    public void testBarcodeCommandWithBothParametersShouldReturnRightCommandType() {
        Command result = CommandFactory.getCommandType(CommandFactoryTestConstants.BOTH_PARAMETERS_EXIST_BARCODE_COMMAND);

        Assert.assertTrue(result instanceof BarcodeCommand);
    }

    @Test
    public void testDescriptionCommandShouldReturnRightCommandType() {
        Command result = CommandFactory.getCommandType(CommandFactoryTestConstants.DESCRIPTION_VALID_COMMAND);

        Assert.assertTrue(result instanceof DescriptionCommand);
    }

    @Test
    public void testWrongCommandShouldResultInNull() {
        Command result = CommandFactory.getCommandType(CommandFactoryTestConstants.WRONG_COMMAND);
        Assert.assertNull(result);
    }
}
