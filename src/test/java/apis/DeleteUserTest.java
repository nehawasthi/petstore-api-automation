package apis;

import com.microsoft.playwright.APIResponse;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.JsonUtils;
import reports.ExtentTestManager;

public class DeleteUserTest extends BaseTest {

    /**
     * Test to verify successful deletion of an existing user.
     * It uses a valid username loaded from test data and expects a 200 OK status in the response.
     */
    @Test(retryAnalyzer = base.RetryAnalyzer.class)
    public void deleteUserPositive() {
        // Attach this test to the Extent Report
        ExtentTestManager.createTest("DeleteUser Positive Test");

        // Retrieve the valid username from the test data
        String username = JsonUtils.getUser("user").get("username").asText();

        // Send DELETE request to remove the user
        APIResponse response = request.delete(BASE_URL + "/user/" + username);

        // Validate that the deletion was successful (status code 200)
        Assert.assertEquals(response.status(), 200);
    }

    /**
     * Test to verify that deleting a non-existent user returns a proper error.
     * It targets an invalid username and expects a 4xx or 5xx response.
     */
    @Test(retryAnalyzer = base.RetryAnalyzer.class)
    public void deleteUserNegative() {
        // Attach this test to the Extent Report
        ExtentTestManager.createTest("DeleteUser Negative Test");

        // Send DELETE request for a user that does not exist
        APIResponse response = request.delete(BASE_URL + "/user/invalidUser");

        // Assert that an error is returned (client or server-side)
        Assert.assertTrue(response.status() >= 400);
    }
}