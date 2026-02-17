package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class VerifyLeadGenerationViaAPI {
    private String token;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://v0-lead-manager-app.vercel.app/api";

        // Extracting Auth Token
        token = given()
                .contentType(ContentType.JSON)
                .body("{\"email\":\"admin@company.com\", \"password\":\"Admin@123\"}")
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .extract().path("token");
    }

    @Test
    public void testCreateLeadViaApi() {
        // Generating dynamic name and email using timestamp
        long timestamp = System.currentTimeMillis();
        String dynamicName = "Osmos_API_" + timestamp;
        String dynamicEmail = "api_" + timestamp + "@osmos.com";

        // Constructing dynamic JSON body
        String body = String.format(
                "{\"name\":\"%s\", \"email\":\"%s\", \"priority\":\"Medium\", \"status\":\"New\"}",
                dynamicName, dynamicEmail
        );

        given()
                .header("Authorization", "Bearer " + token)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/leads")
                .then()
                .log().ifValidationFails() // Fail hone par logs dikhayega
                .statusCode(anyOf(is(200), is(201)))
                .body("success", is(true))
                .body("lead.name", is(dynamicName)); // Response mein check bhi kar lega ki sahi naam bana ya nahi
    }
}