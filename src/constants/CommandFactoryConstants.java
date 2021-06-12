package constants;

public class CommandFactoryConstants {
    public static final String NO_SUCH_ITEM_WITH_BARCODE_MESSAGE = "No item with such barcode found";
    public static final String NO_PRODUCTS_FOUND_MESSAGE = "No products found with this name";

    public static final String GENERAL_COMMAND = "get-food";
    public static final String DESCRIPTION_COMMAND = "get-food-report";
    public static final String BARCODE_COMMAND = "--code=.+";
    public static final String IMAGE_COMMAND = "--img=.+";
    public static final String CODE_COMMAND = "--code";

    public static final String FIRST_BARCODE_PATTERN = "--code=.+ --img=.+";
    public static final String SECOND_BARCODE_PATTERN = "--code=.+ --img=";
    public static final String THIRD_BARCODE_PATTERN = "--img=.+ --code=.+";
    public static final String FOURTH_BARCODE_PATTERN = "--img= --code=.+";

    public static final String FIRST_IMG_PATTERN = "--img=.+ --code=";
    public static final String SECOND_IMG_PATTERN = "--code= --img=.+";

    public static final String SPACE_DELIMITER = "%20";
    public static final String NEXT_ELEMENT_MESSAGE = "Next Element";
    public static final String LOCAL_STORAGE_MESSAGE = "LOCAL STORAGE";
}
