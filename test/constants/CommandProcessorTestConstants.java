package constants;

public class CommandProcessorTestConstants {
    public static final String NULL_INPUT_MESSAGE = "Wrong input";
    public static final String WRONG_COMMAND_MESSAGE = "No such command";
    public static final String NON_EXISTING_BARCODE_MESSAGE = "No item with such barcode found";

    public static final String WRONG_COMMAND = "get-food-hh aa";
    public static final String NULL_PARAMETERS_COMMAND = "get-food ";
    public static final String GENERAL_VALID_COMMAND = "get-food raffaello treat";
    public static final String BARCODE_VALID_COMMAND = "get-food-by-barcode --code=009800146130";
    public static final String DESCRIPTION_VALID_COMMAND = "get-food-report 415269";

    public static final String BARCODE_BY_IMAGE_COMMAND = "get-food-by-barcode --img=src/images/raffaelloBarcode.gif";
    public static final String NON_EXISTING_BARCODE_BY_IMAGE_COMMAND = "get-food-by-barcode --img=src/images/nonExistingBarcode.gif";
    public static final String NON_EXISTING_IMAGE_COMMAND = "get-food-by-barcode --img=src/images/nonExisting";

}
