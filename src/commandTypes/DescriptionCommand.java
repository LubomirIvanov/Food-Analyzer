package commandTypes;

import com.google.gson.Gson;
import command.Command;
import constants.CommandFactoryConstants;
import constants.CommandProcessorConstants;
import database.PseudoDatabase;
import dto.FoodDetails;
import http.RequestSender;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

public class DescriptionCommand implements Command {

    private final static String URI = "%s?api_key=";
    private final static String EMPTY_SPACE = " ";
    private PseudoDatabase pseudoDatabase = PseudoDatabase.getInstance();
    private RequestSender requestSender;

    public DescriptionCommand() {
        requestSender = new RequestSender();
    }

    @Override
    public String getParameters(String commandLine) {
        //"get-food-report 415269
        return commandLine.split(EMPTY_SPACE)[1];
    }

    @Override
    public String execute(String commandLine) throws URISyntaxException {
        String parameters = getParameters(commandLine);

        if (pseudoDatabase.checkIfExistsInDescriptionMap(parameters)) {
            return printDescriptionLocal(pseudoDatabase.getDescriptionFoodInfo().get(parameters)).trim();
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
     * Returns the description info about a product if it is already in the pseudoDatabase in the form of a String
     */
    private String printDescriptionLocal(FoodDetails details) {
        return details.toString() +
                System.lineSeparator() +
                CommandFactoryConstants.LOCAL_STORAGE_MESSAGE +
                System.lineSeparator();
    }

    /**
     * Returns the description info about a product in the form of String
     */
    public String executeAPI(String commandLine, String json) {

        String parameters = getParameters(commandLine);
        Gson gson = new Gson();

        FoodDetails result = gson.fromJson(json, FoodDetails.class);

        if (result.getIngredients() == null) {
            return CommandFactoryConstants.NO_PRODUCTS_FOUND_MESSAGE;
        }

        if (!pseudoDatabase.checkIfExistsInDescriptionMap(parameters)) {
            pseudoDatabase.getDescriptionFoodInfo().put(parameters, result);
        }

        return result.toString();
    }

    @Override
    public String getUri() {
        return URI;
    }
}
