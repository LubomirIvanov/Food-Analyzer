package commandTypes;

import command.Command;
import constants.CommandFactoryConstants;
import database.PseudoDatabase;
import dto.Food;

public class BarcodeCommand implements Command {

    private final static String EQUALS = "=";
    private final static String EMPTY_SPACE = " ";
    private final static int ZERO = 0;
    private final static int ONE = 1;
    private final static int TWO = 2;
    private final static int THREE = 3;
    private PseudoDatabase pseudoDatabase = PseudoDatabase.getInstance();

    public BarcodeCommand() {
    }

    @Override
    public String getParameters(String commandLine) {
        String[] tokens = commandLine.split(EMPTY_SPACE);
        //"get-food-by-barcode --code=009800146130"
        if (tokens.length == TWO) {
            return tokens[1].split(EQUALS)[ONE];
        } else if (tokens.length == THREE) {

            if (containsParameter(tokens, ONE) && containsParameter(tokens, TWO)) {
                //"get-food-by-barcode --code=009800146130 --img=D:\\Photos\\BarcodeImage.jpg"
                if (tokens[ONE].split(EQUALS)[ZERO].equals(CommandFactoryConstants.CODE_COMMAND)) {
                    return tokens[ONE].split(EQUALS)[ONE];
                }
                //"get-food-by-barcode --img=D:\\Photos\\BarcodeImage.jpg --code=009800146130"
                else {
                    return tokens[TWO].split(EQUALS)[ONE];
                }
            }
            //"get-food-by-barcode --code=009800146130 --img="
            else if (containsParameter(tokens, ONE) && !containsParameter(tokens, TWO)) {
                return tokens[ONE].split(EQUALS)[ONE];
            }
            //"get-food-by-barcode --img= --code=009800146130"
            else if (!containsParameter(tokens, ONE) && containsParameter(tokens, TWO)) {
                return tokens[TWO].split(EQUALS)[ONE];
            }
        }

        return null;
    }

    @Override
    public String execute(String commandLine) {
        String parameters = getParameters(commandLine);
        if (pseudoDatabase.checkIfExistsInBarcodeMap(parameters)) {
            return printBarcodeLocal(pseudoDatabase.getBarcodeToQueryMap().get(parameters)).trim();
        }
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


