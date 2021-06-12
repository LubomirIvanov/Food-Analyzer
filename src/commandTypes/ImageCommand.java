package commandTypes;

import command.Command;
import constants.CommandFactoryConstants;
import database.PseudoDatabase;
import decoder.ImageDecoder;
import dto.Food;

public class ImageCommand implements Command {

    private PseudoDatabase pseudoDatabase = PseudoDatabase.getInstance();
    private ImageDecoder decoder;
    private final static String EQUALS = "=";
    private final static String EMPTY_SPACE = " ";
    private final static int TWO = 2;

    public ImageCommand() {
        decoder = new ImageDecoder();
    }

    @Override
    public String getParameters(String commandLine) {
        String[] tokens = commandLine.split(EMPTY_SPACE);
        //get-food-by-barcode --img=src/images/nonExistingBarcode.gif";
        if (tokens.length == 2) {
            return tokens[1].split(EQUALS)[1];
        } else if (tokens.length == 3) {
            //get-food-by-barcode --img=src/images/nonExistingBarcode.gif" --code=;
            if (containsParameter(tokens, 1) && !containsParameter(tokens, 2)) {
                return tokens[1].split(EQUALS)[1];
            }
            //get-food-by-barcode --code= --img=src/images/nonExistingBarcode.gif";
            else if (!containsParameter(tokens, 1) && containsParameter(tokens, 2)) {
                return tokens[2].split(EQUALS)[1];
            }
        }
        //will not ever reach because this is caught in commandFactory
        return null;
    }

    @Override
    public String execute(String commandLine) {
        String parameters = getParameters(commandLine);
        String gtinUpc = decoder.decodeImage(parameters);

        // Decoder.decodeImage returns either gtinUpc or an error message
        if (gtinUpc.equals(ImageDecoder.ERROR_MESSAGE)) {
            return ImageDecoder.ERROR_MESSAGE;
        } else if (pseudoDatabase.checkIfExistsInBarcodeMap(gtinUpc)) {
            return printBarcodeLocal(pseudoDatabase.getBarcodeToQueryMap().get(gtinUpc)).trim();

        }
        // If an item with the given gtinUpc is not in the database, return and error message
        return CommandFactoryConstants.NO_SUCH_ITEM_WITH_BARCODE_MESSAGE;
    }

    private boolean containsParameter(String[] tokens, int position) {
        return tokens[position].split(EQUALS).length == TWO;
    }

    private String printBarcodeLocal(Food food) {
        return CommandFactoryConstants.NEXT_ELEMENT_MESSAGE +
                System.lineSeparator() +
                food.toString();
    }
}
