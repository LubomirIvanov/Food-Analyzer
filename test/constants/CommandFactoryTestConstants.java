package constants;

public class CommandFactoryTestConstants {
    public static final String BARCODE_BY_IMAGE_COMMAND = "get-food-by-barcode --img=src/images/raffaelloBarcode.gif";
    public static final String BOTH_PARAMETERS_EXIST_IMG_PARAMETER_ONLY_BARCODE_COMMAND = "get-food-by-barcode --img=D:\\Photos\\BarcodeImage.jpg --code= ";
    public static final String GENERAL_VALID_COMMAND = "get-food raffaello treat";
    public static final String BARCODE_VALID_COMMAND = "get-food-by-barcode --code=009800146130";
    public static final String BOTH_PARAMETERS_EXIST_BARCODE_COMMAND = "get-food-by-barcode --img=D:\\Photos\\BarcodeImage.jpg --code=009800146130";
    public static final String DESCRIPTION_VALID_COMMAND = "get-food-report 415269";
    public static final String WRONG_COMMAND = "get-food-hh aa";
}
