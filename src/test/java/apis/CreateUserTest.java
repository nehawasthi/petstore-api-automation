package apis;

import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.JsonUtils;
import reports.ExtentTestManager;

public class CreateUserTest extends BaseTest {

    /**
     * Test to verify that a new user can be created successfully with valid input.
     * It fetches test data from userdata.json, sends a POST request, and validates the response status code.
     */
    @Test(retryAnalyzer = base.RetryAnalyzer.class)
    public void createUserPositive() {
        // Attach test name to ExtentReport
        ExtentTestManager.createTest("CreateUser Positive Test");

        // Load valid user data from test data JSON
        JsonNode user = JsonUtils.getUser("user");

        // Send POST request to create the user
        APIResponse response = request.post(BASE_URL + "/user",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setData(user.toString()));

        // Assert the response status code is 200 (success)
        Assert.assertEquals(response.status(), 200);
    }

    /**
     * Test to verify that the system returns appropriate error when invalid user data is submitted.
     * It expects a client-side (4xx) or server-side (5xx) error response.
     */
    @Test(retryAnalyzer = base.RetryAnalyzer.class)
    public void createUserNegative() {
        // Attach test name to ExtentReport
        ExtentTestManager.createTest("CreateUser Negative Test");

        // Load invalid user data from test data JSON
        JsonNode user = JsonUtils.getUser("invalidUser");

        // Send POST request with malformed or incomplete user data
        APIResponse response = request.post(BASE_URL + "/user",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setData(user.toString()));

        // Assert that the response is an error (status code 400 or above)
        Assert.assertTrue(response.status() >= 400);
    }
}