package factory;

import command.Command;
import commandTypes.BarcodeCommand;
import commandTypes.DescriptionCommand;
import commandTypes.GeneralCommand;
import commandTypes.ImageCommand;
import constants.CommandFactoryConstants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CommandFactory {

    private static final String EMPTY_SPACE = " ";
    private static final int TWO = 2;
    private static final int ZERO = 0;
    private static List<String> barcodePatterns = new ArrayList<>(Arrays.asList(
            CommandFactoryConstants.FIRST_BARCODE_PATTERN,
            CommandFactoryConstants.SECOND_BARCODE_PATTERN,
            CommandFactoryConstants.THIRD_BARCODE_PATTERN,
            CommandFactoryConstants.FOURTH_BARCODE_PATTERN
    ));
    private static List<String> IMGPatterns = new ArrayList<>(Arrays.asList(
            CommandFactoryConstants.FIRST_IMG_PATTERN,
            CommandFactoryConstants.SECOND_IMG_PATTERN
    ));

    public static Command getCommandType(String commandLine) {
        String[] tokens = commandLine.split(EMPTY_SPACE);
        if (tokens.length < TWO) {
            return null;
        }

        String command = tokens[ZERO];
        if (command.equals(CommandFactoryConstants.GENERAL_COMMAND)) {
            return new GeneralCommand();
        } else if (command.equals(CommandFactoryConstants.DESCRIPTION_COMMAND)) {
            return new DescriptionCommand();
        }

        String commandParameter = Arrays.stream(tokens).skip(1).collect(Collectors.joining(EMPTY_SPACE));

        if (tokens.length == TWO) {

            if (commandParameter.matches(CommandFactoryConstants.BARCODE_COMMAND)) {
                return new BarcodeCommand();
            } else if (commandParameter.matches(CommandFactoryConstants.IMAGE_COMMAND)) {
                return new ImageCommand();
            }
        } else {
            for (String pattern : barcodePatterns) {
                if (commandParameter.matches(pattern)) {
                    return new BarcodeCommand();
                }
            }

            for (String pattern : IMGPatterns) {
                if (commandParameter.matches(pattern)) {
                    return new ImageCommand();
                }
            }
        }

        return null;
    }
}
