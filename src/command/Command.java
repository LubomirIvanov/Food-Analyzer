package command;

import java.net.URISyntaxException;

public interface Command {
    String getParameters(String commandLine);

    String execute(String commandLine) throws URISyntaxException;

    default String executeAPI(String commandLine, String json) {
        return null;
    }

    default String getUri() {
        return "";
    }
}
