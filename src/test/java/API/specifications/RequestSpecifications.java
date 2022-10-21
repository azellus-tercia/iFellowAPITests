package API.specifications;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import static utils.Configuration.getConfigurationValue;

public final class RequestSpecifications {
    public static RequestSpecification jiraBasicAuth(RequestSpecification specification) {
        return RestAssured
                .given()
                .spec(specification)
                .auth()
                .preemptive()
                .basic(getConfigurationValue("username"), getConfigurationValue("password"));
    }

    public static RequestSpecification jiraOAuth2(RequestSpecification specification) {
        return RestAssured
                .given()
                .spec(specification)
                .auth()
                .oauth2(getConfigurationValue("token"));
    }

    public static RequestSpecification jiraSessionID(RequestSpecification specification, String sessionID) {
        return RestAssured
                .given()
                .spec(specification)
                .sessionId(sessionID);
    }

    public static RequestSpecification specWithoutAuth(RequestSpecification specification) {
        return RestAssured
                .given()
                .spec(specification);
    }
}
