package commandTypes;

import com.google.gson.Gson;
import command.Command;
import constants.CommandFactoryConstants;
import constants.CommandProcessorConstants;
import database.PseudoDatabase;
import dto.FoodInfo;
import http.RequestSender;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class GeneralCommand implements Command {

    private final static String URI = "search?generalSearchInput=%s&requireAllWords=true&api_key=";
    private final static String EMPTY_SPACE = " ";
    private final static int ZERO = 0;
    private final static int ONE = 1;
    private PseudoDatabase pseudoDatabase = PseudoDatabase.getInstance();
    private RequestSender requestSender;

    public GeneralCommand() {
        requestSender = new RequestSender();
    }

    @Override
    public String getParameters(String commandLine) {
        String[] tokens = commandLine.split(EMPTY_SPACE);

        //"get-food raffaello treat"
        return Arrays
                .stream(tokens)
                .skip(ONE)
                .collect(Collectors.joining(CommandFactoryConstants.SPACE_DELIMITER));
    }

    @Override
    public String execute(String commandLine) throws URISyntaxException {
        String parameters = getParameters(commandLine);

        if (pseudoDatabase.checkIfExistsInGeneralMap(parameters)) {
            return printGeneralInfoLocal(pseudoDatabase.getGeneralFoodInfo().get(parameters)).trim();
        } else {

            String json = requestSender.requestInfo(this, parameters);
            if (json == null || json.isEmpty() || json.equals(CommandProcessorConstants.BAD_REQUEST_MESSAGE)) {
                return CommandProcessorConstants.BAD_REQUEST_MESSAGE;
            }
            String result = executeAPI(commandLine, json);

            String finalResult = Objects.requireNonNullElse(result, CommandProcessorConstants.WRONG_URI_MESSAGE);
            return finalResult.trim();
        }
    }

    /**
     * Returns the generalInfo about a product if it is already in the pseudoDatabase in the form of a String
     */
    private String printGeneralInfoLocal(FoodInfo foodInfo) {
        StringBuilder sb = new StringBuilder();
        for (var x : foodInfo.getFoods()) {
            sb.append(CommandFactoryConstants.NEXT_ELEMENT_MESSAGE);
            sb.append(System.lineSeparator());
            sb.append(x.toString());
            sb.append(System.lineSeparator());
            sb.append(CommandFactoryConstants.LOCAL_STORAGE_MESSAGE);
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public String executeAPI(String commandLine, String json) {

        String parameters = getParameters(commandLine);
        Gson gson = new Gson();
        FoodInfo result = gson.fromJson(json, FoodInfo.class);

        if (result.getFoods() == null) {
            return CommandProcessorConstants.BAD_REQUEST_MESSAGE;
        }
        if (result.getFoods().length == ZERO) {
            return CommandFactoryConstants.NO_PRODUCTS_FOUND_MESSAGE;
        }

        if (!pseudoDatabase.checkIfExistsInGeneralMap(parameters)) {
            pseudoDatabase.getGeneralFoodInfo().put(parameters, result);
        }

        StringBuilder sb = new StringBuilder();

        for (var x : result.getFoods()) {
            sb.append(CommandFactoryConstants.NEXT_ELEMENT_MESSAGE);
            sb.append(System.lineSeparator());
            sb.append(x.toString());
            sb.append(System.lineSeparator());

            if (x.getGtinUpc() != null) {
                if (!pseudoDatabase.checkIfExistsInBarcodeMap(x.getGtinUpc())) {
                    pseudoDatabase.getBarcodeToQueryMap().put(x.getGtinUpc(), x);
                }
            }
        }
        return sb.toString();
    }

    @Override
    public String getUri() {
        return URI;
    }
}
