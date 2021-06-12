package constants;

public class ImageCommandTestConstants {

    public static final String NON_EXISTING_BARCODE_MESSAGE = "No item with such barcode found";

    public static final String IMAGE_INFO_ONLY_VALID_COMMAND = "get-food-by-barcode --img=D:\\Photos\\BarcodeImage.jpg";

    public static final String BOTH_PARAMETERS_EXIST_IMG_PARAMETER_ONLY_BARCODE_COMMAND =
            "get-food-by-barcode --img=D:\\Photos\\BarcodeImage.jpg --code= ";

    public static final String BOTH_PARAMETERS_EXIST_IMG_PARAMETER_ONLY_SECOND_BARCODE_COMMAND =
            "get-food-by-barcode --code= --img=D:\\Photos\\BarcodeImage.jpg";


    public static final String IMGPath = "D:\\Photos\\BarcodeImage.jpg";


    public static final String BARCODE_INVALID_PARAMETERS = "get-food-by-barcode --code=009800146130 1 1";


    public static final String GENERAL_VALID_COMMAND = "get-food raffaello treat";

    public static final String BARCODE_BY_IMAGE_COMMAND = "get-food-by-barcode --img=src/images/raffaelloBarcode.gif";
    public static final String NON_EXISTING_BARCODE_BY_IMAGE_COMMAND = "get-food-by-barcode --img=src/images/nonExistingBarcode.gif";
    public static final String NON_EXISTING_IMAGE_COMMAND = "get-food-by-barcode --img=src/images/nonExisting";

}
