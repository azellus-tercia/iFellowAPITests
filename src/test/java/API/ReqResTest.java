package API;

import API.hooks.ApiHooks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static API.steps.ReqResSteps.*;

public class ReqResTest extends ApiHooks {
    @Test
    @DisplayName("Задание 2. Работа с API по reqres.in.")
    public void Test_ReqRes() {
        createUser();

        checkResponseIsValid();
    }
}
