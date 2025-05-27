package apis;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.JsonUtils;
import reports.ExtentTestManager;

public class UpdateUserTest extends BaseTest {

    /**
     * Test to verify that an existing user's details can be successfully updated.
     * This test modifies the last name in the payload and sends a PUT request to update the user.
     */
    @Test(retryAnalyzer = base.RetryAnalyzer.class)
    public void updateUserPositive() {
        // Create entry in Extent report
        ExtentTestManager.createTest("UpdateUser Positive Test");

        // Fetch valid user data and cast it to ObjectNode to allow modification
        ObjectNode user = (ObjectNode) JsonUtils.getUser("user");

        // Update one or more fields in the request body
        user.put("lastName", "UpdatedLastName");

        // Extract the username to use in the endpoint
        String username = user.get("username").asText();

        // Send PUT request to update the user
        APIResponse response = request.put(BASE_URL + "/user/" + username,
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setData(user.toString()));

        // Assert the update was successful
        Assert.assertEquals(response.status(), 200);
    }

    /**
     * Test to verify that updating a user with invalid data or username fails as expected.
     * This test simulates failure by sending an invalid payload to a non-existent user.
     */
    @Test(retryAnalyzer = base.RetryAnalyzer.class)
    public void updateUserNegative() {
        // Create entry in Extent report
        ExtentTestManager.createTest("UpdateUser Negative Test");

        // Load invalid user data from JSON
        JsonNode user = JsonUtils.getUser("invalidUser");

        // Send PUT request with invalid data
        APIResponse response = request.put(BASE_URL + "/user/invalidUser",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setData(user.toString()));

        // Expect a client-side or server-side error
        Assert.assertTrue(response.status() >= 400);
    }
}