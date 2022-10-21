package API;

import API.hooks.ApiHooks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static API.steps.JiraSteps.*;

public class JiraApiTest extends ApiHooks {
    @Test
    @DisplayName("Создание задачи со статусом \"Ошибка\" в Jira и ее удаление с помощью базовой авторизации.")
    public void Test_CreateTaskBasicAuth() {
        createTaskBasicAuth();
        getTaskNumber();
        deleteTaskBasicAuth();
    }

    @Test
    @DisplayName("Создание задачи со статусом \"Ошибка\" в Jira и ее удаление с помощью OAuth2.0.")
    public void Test_CreateTaskOAuth2() {
        createTaskOAuth2();
        getTaskNumber();
        deleteTaskOAuth2();
    }

    @Test
    @DisplayName("Создание задачи со статусом \"Ошибка\" в Jira и ее удаление с помощью session ID.")
    public void Test_CreateTaskSessionId() {
        getResponseWithSessionId();
        getSessionId();
        createTaskSessionId();
        getTaskNumber();
        deleteTaskSessionId();
    }

    @Test
    @DisplayName("Получение количества созданных задач в Jira")
    public void Test_GetAllTasks() {
        getJiraAllTasks();
        checkNumberOfAllTasks();
    }
}
