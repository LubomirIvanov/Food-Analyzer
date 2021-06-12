package http;

import com.google.gson.Gson;
import command.Command;
import constants.CommandProcessorConstants;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestSender {
    /*
     * Makes a HTTP request
     */
    private HttpClient client;

    public RequestSender() {
        client = HttpClient.newHttpClient();
    }

    private String getFoodSync(HttpRequest request) throws IOException, InterruptedException {
        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

    /**
     * Makes a request to the FoodDataCentral API to get the desired food information
     * then proceeds to executeCommand providing the acquired data
     * Returns an error message if a bad request occurs
     */
    public String requestInfo(Command command, String parameters) throws URISyntaxException {

        HttpRequest request = createHttpRequest(command, parameters);
        if (request == null) {
            return null;
        }

        String json = null;
        try {
            json = getFoodSync(request);
        } catch (IOException | InterruptedException e) {
            return e.getMessage();
        }

        return json;
    }


    /**
     * Creates an HttpRequest if the totalPath is valid
     * otherwise returns null
     */
    private HttpRequest createHttpRequest(Command command, String parameters) throws URISyntaxException {
        String foodSearch = String.format(command.getUri(), parameters);

        String totalPath = CommandProcessorConstants.SCHEME_HOST_PATH +
                foodSearch +
                CommandProcessorConstants.API_KEY;

        if (!isValidUri(totalPath)) {
            return null;
        }

        URI uri = new URI(totalPath);
        return HttpRequest.newBuilder(uri).build();
    }

    /**
     * Checks if an URI can be made using a given URL in the form of a string
     */

    private boolean isValidUri(String totalPath) {
        URI uri = null;
        try {
            uri = new URI(totalPath);
        } catch (URISyntaxException e) {
            return false;
        }
        return true;
    }


}
