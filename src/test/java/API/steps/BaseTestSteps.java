package API.steps;

import io.restassured.response.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class BaseTestSteps {
    public static JSONObject getJSONObject(String fileName) {
        try {
            return new JSONObject(new String(Files.readAllBytes(Paths.get("src/test/resources/requests/" + fileName))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getJsonPath(Response response, String path) {
        return response.jsonPath().get(path).toString();
    }
}
