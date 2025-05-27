package apis;

import com.fasterxml.jackson.databind.JsonNode;
import com.microsoft.playwright.APIResponse;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.JsonUtils;
import reports.ExtentTestManager;

public class GetUserByNameTest extends BaseTest {

    @Test(retryAnalyzer = base.RetryAnalyzer.class)
    public void getUserPositive() {
        ExtentTestManager.createTest("GetUserByName Positive Test");

        // Fetch valid user from userdata.json
        String username = JsonUtils.getUser("user").get("username").asText();

        //Perform GET API call
        APIResponse response = request.get(BASE_URL + "/user/" + username);

        //Assert response
        Assert.assertEquals(response.status(), 200);
    }

    @Test(retryAnalyzer = base.RetryAnalyzer.class)
    public void getUserNegative() {
        ExtentTestManager.createTest("GetUserByName Negative Test");

        // Convert JsonNode to String using .asText()
        String username = JsonUtils.getField("nonExistentUsername").asText();

        // perform GET API call for non-existent user
        APIResponse response = request.get(BASE_URL + "/user/" + username);

        // Expecting 404 Not Found
        Assert.assertEquals(response.status(), 404);
    }
}
