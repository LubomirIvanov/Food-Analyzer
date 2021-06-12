package constants;

public class BarcodeCommandTestConstants {

    public static final String BARCODE_INFO_ONLY_VALID_COMMAND = "get-food-by-barcode --code=009800146130";
    public static final String BARCODE_NON_EXISTING_COMMAND = "get-food-by-barcode --code=009800146131";

    public static final String BOTH_PARAMETERS_EXIST_BARCODE_COMMAND =
            "get-food-by-barcode --img=D:\\Photos\\BarcodeImage.jpg --code=009800146130";
    public static final String BOTH_PARAMETERS_EXIST_SECOND_BARCODE_COMMAND =
            "get-food-by-barcode --code=009800146130 --img=D:\\Photos\\BarcodeImage.jpg";
    public static final String BOTH_PARAMETERS_EXIST_BARCODE_PARAMETER_ONLY_BARCODE_COMMAND =
            "get-food-by-barcode --img= --code=009800146130";
    public static final String BOTH_PARAMETERS_EXIST_BARCODE_PARAMETER_ONLY_SECOND__BARCODE_COMMAND =
            "get-food-by-barcode --code=009800146130 --img= ";

    public static final String BARCODE = "009800146130";

    public static final String BARCODE_INVALID_PARAMETERS = "get-food-by-barcode --code=009800146130 1 1";

    public static final String GENERAL_VALID_COMMAND = "get-food raffaello treat";

}
