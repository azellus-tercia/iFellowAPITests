package API.hooks;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import static utils.Configuration.getConfigurationValue;

public abstract class ApiHooks {
    public static RequestSpecification jiraRequest;
    public static RequestSpecification rickAndMorty;
    public static RequestSpecification reqRes;

    @BeforeAll
    static void setUp() {
         jiraRequest = new RequestSpecBuilder()
                 .setBaseUri(getConfigurationValue("jiraUrlAPI"))
                 .setContentType(ContentType.JSON)
                 .setAccept(ContentType.JSON)
                 .addFilter(new AllureRestAssured())
                 .addFilter(new RequestLoggingFilter())
                 .addFilter(new ResponseLoggingFilter())
                 .build();

         rickAndMorty = new RequestSpecBuilder()
                 .setBaseUri(getConfigurationValue("rickAndMortyUrl"))
                 .setContentType(ContentType.JSON)
                 .setAccept(ContentType.JSON)
                 .addFilter(new AllureRestAssured())
                 .addFilter(new RequestLoggingFilter())
                 .addFilter(new ResponseLoggingFilter())
                 .build();

         reqRes = new RequestSpecBuilder()
                 .setBaseUri(getConfigurationValue("reqResUrl"))
                 .setContentType(ContentType.JSON)
                 .setAccept(ContentType.JSON)
                 .addFilter(new AllureRestAssured())
                 .addFilter(new RequestLoggingFilter())
                 .addFilter(new ResponseLoggingFilter())
                 .build();
    }
}
