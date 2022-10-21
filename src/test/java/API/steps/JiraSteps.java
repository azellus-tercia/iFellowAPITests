package API.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.json.JSONObject;

import static API.endpoints.EndPoints.*;
import static API.hooks.ApiHooks.*;
import static API.steps.Asserts.checkJsonFieldIsPresent;
import static API.specifications.RequestSpecifications.*;
import static utils.Configuration.getConfigurationValue;

public final class JiraSteps extends BaseTestSteps {
    private static Response response;

    private static String taskNumber;
    private static String sessionId;

    @Step("Сборка body запроса для получения session ID")
    public static JSONObject createBodyForSessionId() {
        JSONObject bodySessionId = new JSONObject();
        bodySessionId.put("username", getConfigurationValue("username"));
        bodySessionId.put("password", getConfigurationValue("password"));

        return bodySessionId;
    }

    @Step("Сборка body запроса для создания новой задачи в Jira с добавлением поля \"description\"")
    public static JSONObject createBodyForNewTask() {
        return getJSONObject("createBug.json")
                .put("description", getConfigurationValue("description"));
    }

    @Step("Получаем значение поля {path} из JSON ответа")
    private static String getValueOfField(String path) {
        return getJsonPath(response, path);
    }

    @Then("Получение номера созданной задачи из ответа")
    public static void getTaskNumber() {
        taskNumber = getValueOfField("id");
    }

    @And("Получение session ID из ответа")
    public static void getSessionId() {
        sessionId = getValueOfField("session.value");
    }

    @When("Создание задачи со статусом \"Ошибка\" в Jira с помощью базовой авторизации")
    @Step("Отправка POST запроса на создание задачи со статусом \"Ошибка\" в Jira с помощью базовой авторизации")
    public static void createTaskBasicAuth() {
        response = jiraBasicAuth(jiraRequest)
                .body(createBodyForNewTask().toString())
                .post(JIRA_CREATE_ISSUE);
    }

    @And("Удаление созданной задачи в Jira с помощью базовой авторизации")
    @Step("Отправка DELETE запроса на удаление задачи в Jira с помощью базовой авторизации")
    public static void deleteTaskBasicAuth() {
        jiraBasicAuth(jiraRequest).delete(JIRA_CREATE_ISSUE + "/" + taskNumber);
    }

    @When("Создание задачи со статусом \"Ошибка\" в Jira с помощью OAuth2.0")
    @Step("Отправка POST запроса на создание задачи со статусом \"Ошибка\" в Jira с помощью OAuth2.0")
    public static void createTaskOAuth2() {
        response = jiraOAuth2(jiraRequest)
                .body(createBodyForNewTask().toString())
                .post(JIRA_CREATE_ISSUE);
    }

    @And("Удаление созданной задачи в Jira с помощью OAuth2.0")
    @Step("Отправка DELETE запроса на удаление задачи в Jira с помощью OAuth2.0")
    public static void deleteTaskOAuth2() {
        jiraOAuth2(jiraRequest).delete(JIRA_CREATE_ISSUE + "/" + taskNumber);
    }

    @Then("Создание задачи со статусом \"Ошибка\" в Jira с помощью session ID")
    @Step("Отправка POST запроса на создание задачи со статусом \"Ошибка\" в Jira с помощью session ID")
    public static void createTaskSessionId() {
        response = jiraSessionID(jiraRequest, sessionId)
                .body(createBodyForNewTask().toString())
                .post(JIRA_CREATE_ISSUE);
    }

    @And("Удаление созданной задачи в Jira с помощью session ID")
    @Step("Отправка DELETE запроса на удаление задачи в Jira с помощью session ID")
    public static void deleteTaskSessionId() {
        jiraOAuth2(jiraRequest).delete(JIRA_CREATE_ISSUE + "/" + taskNumber);
    }

    @When("Создание запроса для получения session ID в Jira")
    @Step("Отправка POST запроса для получения session ID")
    public static void getResponseWithSessionId() {
        response = specWithoutAuth(jiraRequest)
                .body(createBodyForSessionId().toString())
                .post(JIRA_GET_SESSION_ID);
    }

    @When("Создание запроса для получения списка всех задач в Jira")
    @Step("Отправка GET запроса в Jira для получения списка всех задач")
    public static void getJiraAllTasks() {
        response = jiraOAuth2(jiraRequest).get(JIRA_GET_ALL_TASKS);
    }

    @Then("Получение количества созданных задач из ответа")
    public static void checkNumberOfAllTasks() {
        checkJsonFieldIsPresent(response, "total");
    }
}
